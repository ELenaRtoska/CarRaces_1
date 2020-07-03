package mk.ukim.finki.emt.carraces.route_management.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.geo.City;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "routes")
@Getter
public class Route extends AbstractEntity<RouteId> {

    @Version
    private Long version;

    @Column(name = "route_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private String route_name;

    @Column(name = "city", nullable = false)
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(name = "route_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private RouteState state;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Path> paths;

    private Route() {

    }

    public Route(@NonNull String route_name, @NonNull City city) {
        super(DomainObjectId.randomId(RouteId.class));
        this.paths = new HashSet<>();
        setRouteName(route_name);
        setCity(city);
        setState(RouteState.RECEIVED);
    }

    @NonNull
    @JsonProperty("state")
    public RouteState state() {
        return state;
    }

    private void setState(@NonNull RouteState state)
    {
        this.state = state;
    }

    public void setRouteName(String route_name)
    {
        this.route_name = route_name;
    }

    public void setCity(City city)
    {
        this.city = city;
    }


    public Stream<Path> getPaths() {
        return paths.stream();
    }

    //dodavanje na nova pateka na rutata
    public Path addPath(String path_name, float path_length, float path_elevation, int path_level) {
        var path = new Path(path_name,path_length,path_elevation,path_level);
        path.setPath_level(path_level);
        paths.add(path);
        return path;
    }




}
