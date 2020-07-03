package mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

//repository-vo e odgovorno za manipulacija na zapisite koi sto se naogaat vo StoredDomainEvent
public interface StoredDomainEventRepository extends JpaRepository<StoredDomainEvent, Long> {

    //najdi go maksimalniot id na event..za da se znae koj event bil isfrlen posleden
    @Query("select max(se.id) from StoredDomainEvent se")
    Long findHighestDomainEventId();

    @Query("select se from StoredDomainEvent se where se.id >= :low and se.id <= :high order by se.id")
    Stream<StoredDomainEvent> findEventsBetween(@Param("low") Long low, @Param("high") Long high);

}
