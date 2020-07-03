package mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog;


import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainEvent;

import java.util.Optional;

public interface RemoteEventTranslator {

    boolean supports(StoredDomainEvent storedDomainEvent);

    Optional<DomainEvent> translate(StoredDomainEvent remoteEvent);
}
