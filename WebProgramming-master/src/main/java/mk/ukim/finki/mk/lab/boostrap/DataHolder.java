package mk.ukim.finki.mk.lab.boostrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.repository.jpa.EventRepository;
import mk.ukim.finki.mk.lab.repository.jpa.LocationRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = null;
    public static List<Location> locations = null;

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public DataHolder(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void init() {
        events = new ArrayList<>();
        locations = new ArrayList<>();

        if (this.locationRepository.count() == 0) {
            locations = List.of(
                    new Location("Skopje Event Center", "Address Line 1", "300", "Some location description 1"),
                    new Location("Makedonska Filharmonija", "Address Line 1", "250", "Some location description 2"),
                    new Location("Arena Boris Trajkovski", "Address Line 1", "3000", "Some location description 3"),
                    new Location("MKC Skopje", "Address Line 1", "500", "Some location description 4"),
                    new Location("National Arena Tose Proeski", "Address Line 1", "33000", "Some location description 5")
            );
            this.locationRepository.saveAll(locations);
        }
            if (this.eventRepository.count() == 0) {
                events = new ArrayList<>(List.of(
                        new Event("event1", "description for event1", 5.0, locations.get(0)),
                        new Event("event2", "description for event2", 4.0, locations.get(0)),
                        new Event("event3", "description for event3", 3.8, locations.get(0)),
                        new Event("event4", "description for event4", 2.7, locations.get(0)),
                        new Event("event5", "description for event5", 4.8, locations.get(0)),
                        new Event("event6", "description for event6", 2.0, locations.get(0)),
                        new Event("event7", "description for event7", 1.5, locations.get(0)),
                        new Event("event8", "description for event8", 4.0, locations.get(0)),
                        new Event("event9", "description for event9", 3.2, locations.get(0)),
                        new Event("event10", "description for event10", 4.7, locations.get(0))
                ));
                this.eventRepository.saveAll(events);
            }

    }

}
