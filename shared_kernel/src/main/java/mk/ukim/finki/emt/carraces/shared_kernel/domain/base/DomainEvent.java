package mk.ukim.finki.emt.carraces.shared_kernel.domain.base;

import java.time.Instant;

public interface DomainEvent {

    //koga se slucil event-ot
    Instant occurredOn();

}
