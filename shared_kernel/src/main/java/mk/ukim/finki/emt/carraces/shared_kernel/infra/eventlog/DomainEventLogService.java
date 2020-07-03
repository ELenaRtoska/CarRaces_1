package mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog;

import com.fasterxml.jackson.databind.ObjectMapper;

import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
public class DomainEventLogService {

    private final StoredDomainEventRepository storedDomainEventRepository;
    private final ObjectMapper objectMapper;

    DomainEventLogService(StoredDomainEventRepository storedDomainEventRepository,
                          ObjectMapper objectMapper) {
        this.storedDomainEventRepository = storedDomainEventRepository;
        this.objectMapper = objectMapper;
    }

    //dodava nov zapis(vo bazata) odnosno nov event koj nastanal vo aplikacijata...nekoj podocna ke go preceka toj event
    @Transactional(propagation = Propagation.MANDATORY)
    public void append(@NonNull DomainEvent domainEvent) {
        var storedEvent = new StoredDomainEvent(domainEvent, objectMapper);
        storedDomainEventRepository.saveAndFlush(storedEvent);
    }

    //so ovoj metod mozeme da gi povleceme event-ite vo daden vremenski rok na pr. poslednite 10sec
    public Stream<StoredDomainEvent> retrieveLog(long lastProcessedEventId) {
        long max = storedDomainEventRepository.findHighestDomainEventId();
        return storedDomainEventRepository.findEventsBetween(lastProcessedEventId,max);
    }
}
