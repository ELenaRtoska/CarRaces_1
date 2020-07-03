package mk.ukim.finki.emt.carraces.shared_kernel.domain.geo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Value object representing the name of a city.
 */
@Embeddable
public class City implements ValueObject {

    @Column(name="city_name")
    private final String name;

    //unused
    private City() {this.name="";}

    @JsonCreator
    public City(@NonNull String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    @JsonValue
    public String toString() {
        return name;
    }



}