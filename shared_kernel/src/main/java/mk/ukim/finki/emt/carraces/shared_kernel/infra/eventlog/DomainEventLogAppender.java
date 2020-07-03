package mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog;

import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class DomainEventLogAppender {

    private final DomainEventLogService domainEventLogService;

    public DomainEventLogAppender(DomainEventLogService domainEventLogService)
    {
        this.domainEventLogService=domainEventLogService;
    }

    //slusanje na odreden event
    @TransactionalEventListener
    public void onDomainEvent(DomainEvent domainEvent)
    {
        //koga ke go slusneme event-ot treba samo da go dodademe vo event log-ot
        domainEventLogService.append(domainEvent);
    }

}
