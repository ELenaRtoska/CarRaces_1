package mk.ukim.finki.emt.carraces.route_management.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CarRaceId extends DomainObjectId {

    private CarRaceId() {
        super(DomainObjectId.randomId(CarRaceId.class).toString());
    }

    @JsonCreator
    public CarRaceId(String id) {
        super(id);
    }
}
