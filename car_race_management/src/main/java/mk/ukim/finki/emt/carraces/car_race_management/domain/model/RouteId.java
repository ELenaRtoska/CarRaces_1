package mk.ukim.finki.emt.carraces.car_race_management.domain.model;

import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class RouteId extends DomainObjectId {

    public RouteId(String id) {
        super(id);
    }
}
