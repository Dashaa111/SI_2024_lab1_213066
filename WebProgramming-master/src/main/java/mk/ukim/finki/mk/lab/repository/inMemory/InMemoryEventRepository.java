/*
package mk.ukim.finki.mk.lab.repository.inMemory;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryEventRepository {
    private static List<Event> events = new ArrayList<>(List.of(
            new Event("event1", "description for event1", 5.0, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event2", "description for event2", 4.0, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event3", "description for event3", 3.8, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event4", "description for event4", 2.7, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event5", "description for event5", 4.8, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event6", "description for event6", 2.0, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event7", "description for event7", 1.5, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event8", "description for event8", 4.0, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event9", "description for event9", 3.2, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1")),
            new Event("event10", "description for event10", 4.7, new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1"))
    ));

    public List<Event> findAll() {
        return events;
    }

    public List<Event> searchEventsWithTextPopularityScores(String text, int rating) {
        return events.stream()
                .filter(e -> (e.getName().contains(text) || e.getDescription().contains(text)) && e.getPopularityScore() >= rating)
                .toList();
    }

    public List<Event> searchEvents(String text) {
        return events.stream()
                .filter(e -> e.getName().contains(text) || e.getDescription().contains(text))
                .toList();
    }

    public List<Event> searchEventsViaRating(int rating) {
        return events.stream()
                .filter(e -> e.getPopularityScore() > rating)
                .toList();
    }

    public void deleteById(Long id) {
        List<Event> modifiableEvents = new ArrayList<>(events);
        modifiableEvents.removeIf(event -> event.getId().equals(id));
        events = modifiableEvents;
    }

    public Optional<Event> findById(Long id) {
        return events.stream().filter(event -> event.getId().equals(id)).findFirst();
    }

    public void save(Event event) {
        events.add(event);
    }

    public void deleteByLocationName(String name) {
        List<Event> modifiableEvents = new ArrayList<>(events);
        modifiableEvents.removeIf(event -> event.getLocation().getName().equals(name));
        events = modifiableEvents;
    }
}
*/
