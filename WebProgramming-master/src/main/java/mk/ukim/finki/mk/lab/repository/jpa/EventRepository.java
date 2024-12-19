package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    List<Event> findByDescriptionContainingAndPopularityScore(String description, double popularityScore);
    List<Event> findByDescriptionContaining(String description);
    List<Event> findByPopularityScore(double popularityScore);
    void deleteByLocation_Name(String name);
}
