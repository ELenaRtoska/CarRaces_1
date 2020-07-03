package mk.ukim.finki.emt.carraces.car_race_management.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class PathId extends DomainObjectId {

    private String id;

    private PathId() {
        super(DomainObjectId.randomId(PathId.class).toString());
    }

    @JsonCreator
    public PathId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
