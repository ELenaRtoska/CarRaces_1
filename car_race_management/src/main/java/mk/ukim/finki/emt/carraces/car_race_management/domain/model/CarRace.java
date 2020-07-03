package mk.ukim.finki.emt.carraces.car_race_management.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name="carRace")
public class CarRace extends AbstractEntity<CarRaceId> {

    @Version
    private Long version;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverride(name="id",column = @Column(name="route_id",nullable = false))
    private RouteId routeId;




    public CarRace() {

    }

    public CarRace(CarRaceId carRaceId, String name) {
        super(carRaceId);
        this.name = name;
    }


    public CarRace(String name) {
        this.name = name;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }




}
