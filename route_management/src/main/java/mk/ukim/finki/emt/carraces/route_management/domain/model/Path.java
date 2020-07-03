package mk.ukim.finki.emt.carraces.route_management.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.carraces.shared_kernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name="paths")
@Getter
public class Path extends AbstractEntity<PathId> {


    @Column(name = "path_name", nullable = false)
    private String path_name;

    @Column(name = "path_length", nullable = false)
    private float path_length;

    @Column(name = "path_elevation", nullable = false)
    private float path_elevation;

    @Column(name = "path_level", nullable = false)
    private int path_level;


    private Path() {
    }

    Path(@NonNull String path_name, @NonNull float path_length, @NonNull float path_elevation, @NonNull int path_level) {
        super(DomainObjectId.randomId(PathId.class));
        setPath_name(path_name);
        setPath_elevation(path_elevation);
        setPath_level(path_level);
        if (path_length < 0) {
            throw new IllegalArgumentException("Path length cannot be negative");
        }
        this.path_length = path_length;
    }


    public void setPath_name(String path_name) {
        this.path_name = path_name;
    }

    public void setPath_elevation(float path_elevation) {
        this.path_elevation = path_elevation;
    }

    public void setPath_level(int path_level) {
        this.path_level = path_level;
    }

    public void setPath_length(int path_length) {
        if (path_length < 0.0) {
            throw new IllegalArgumentException("Path length cannot be negative");
        }
        this.path_length = path_length;
    }

    @NonNull
    @JsonProperty("path_length")
    public float path_length() {
        return path_length;
    }



    //public Money subtotal() {
      //  return itemPrice.multiply(quantity);
  //  }

}
