package mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;

//ova repository ni e potrebno samo za da zapisuvame i da citame podatoci vo/od bazata t.e tabelata
public interface ProcessedRemoteEventRepository extends JpaRepository<mk.ukim.finki.emt.carraces.shared_kernel.infra.eventlog.ProcessedRemoteEvent,String> {
}
