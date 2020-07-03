package mk.ukim.finki.emt.carraces.route_management.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

@Embeddable
@Getter
@EqualsAndHashCode
public class RouteId extends DomainObjectId {

    private RouteId() {
        super("");
    }

    public RouteId(@NonNull String id) {
        super(id);
    }
}
