package mk.ukim.finki.emt.carraces.car_race_management.domain.repository;

import mk.ukim.finki.emt.carraces.car_race_management.domain.model.CarRace;
import mk.ukim.finki.emt.carraces.car_race_management.domain.model.CarRaceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRaceRepository extends JpaRepository<CarRace, CarRaceId> {
}