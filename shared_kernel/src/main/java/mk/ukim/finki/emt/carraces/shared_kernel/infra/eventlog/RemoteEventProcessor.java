package mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

@Service
public class RemoteEventProcessor {

    private final mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.ProcessedRemoteEventRepository processedRemoteEventRepository;
    private final Map<String, mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventLogService> remoteEventLogs;
    private final Map<String, mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventTranslator> remoteEventTranslators;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TransactionTemplate transactionTemplate;


    public RemoteEventProcessor(mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.ProcessedRemoteEventRepository processedRemoteEventRepository,
                                Map<String, mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventLogService> remoteEventLogs,
                                Map<String, mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventTranslator> remoteEventTranslators,
                                ApplicationEventPublisher applicationEventPublisher,
                                TransactionTemplate transactionTemplate) {
        this.processedRemoteEventRepository = processedRemoteEventRepository;
        this.remoteEventLogs = remoteEventLogs;
        this.remoteEventTranslators = remoteEventTranslators;
        this.applicationEventPublisher = applicationEventPublisher;
        this.transactionTemplate = transactionTemplate;
    }

    @Scheduled(fixedDelay = 20000)
    public void processEvents() {
        remoteEventLogs.values().forEach(this::processEvents);
    }

    private void processEvents(@NonNull mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventLogService remoteEventLogService) {
        var lastProcessedId = getLastProcessedId(remoteEventLogService);

        var log = remoteEventLogService.currentLog();

        processEvents(remoteEventLogService, lastProcessedId, log.events());

    }

    private long getLastProcessedId(@NonNull mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventLogService remoteEventLogService) {
        return processedRemoteEventRepository.findById(remoteEventLogService.source())
                .map(mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.ProcessedRemoteEvent::lastProcessedEventId)
                .orElse(0L);
    }

    private void processEvents(@NonNull mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventLogService remoteEventLogService, long lastProcessedId,
                               @NonNull List<StoredDomainEvent> events) {
        events.forEach(event -> {
            if (event.id() > lastProcessedId) {
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        publishEvent(event);
                        setLastProcessedId(remoteEventLogService, event.id());
                    }
                });
            }
        });
    }

    private void setLastProcessedId(@NonNull mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.RemoteEventLogService remoteEventLogService, long lastProcessedId) {
        processedRemoteEventRepository.saveAndFlush(new mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.ProcessedRemoteEvent(remoteEventLogService.source(), lastProcessedId));
    }

    private void publishEvent(@NonNull StoredDomainEvent event) {
        remoteEventTranslators.values().stream()
                .filter(translator -> translator.supports(event))
                .findFirst()
                .flatMap(translator -> translator.translate(event))
                .ifPresent(applicationEventPublisher::publishEvent);
    }
}
