package mk.ukim.finki.emt.carraces.route_management.domain.repository;

import mk.ukim.finki.emt.carraces.route_management.domain.model.Route;
import mk.ukim.finki.emt.carraces.route_management.domain.model.RouteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, RouteId> {
}