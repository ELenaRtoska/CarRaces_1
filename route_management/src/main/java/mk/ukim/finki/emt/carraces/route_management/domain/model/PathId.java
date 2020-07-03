package mk.ukim.finki.emt.carraces.route_management.domain.model;

import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class PathId extends DomainObjectId {

    private String id;

    private PathId() {
        super("");
    }

    public PathId(String uuid) {
        super(uuid);
        this.id=uuid;
    }

}
