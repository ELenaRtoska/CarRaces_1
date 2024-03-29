package mk.ukim.finki.emt.carraces.shared_kernel.port.rest;


import mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.DomainEventLogService;
import mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.StoredDomainEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event-log")
public class EventLogController {

    private final DomainEventLogService domainEventLogService;

    public EventLogController(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @GetMapping(path="/{lastProcessedId}")
    public ResponseEntity<List<StoredDomainEvent>> domainEvents(@PathVariable("lastProcessedId") long lastProcessedId) {
        var responseBuilder = ResponseEntity.ok();
        return responseBuilder.body(domainEventLogService.retrieveLog(lastProcessedId).collect(Collectors.toList()));
    }
}
