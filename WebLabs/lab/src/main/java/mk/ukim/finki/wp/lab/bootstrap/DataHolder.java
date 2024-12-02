package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Event> events = new ArrayList<>();
    public static List<EventBooking> eventBookingList = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();


    @PostConstruct
    public void init() {
        locations.add(new Location("Main Hall", "123 Main St", "500", "Large event space"));
        locations.add(new Location("Conference Room", "456 Elm St", "100", "Meeting room for conferences"));
        locations.add(new Location("Outdoor Venue", "789 Oak St", "1000", "Outdoor space for festivals"));
        locations.add(new Location("Theater", "101 Pine St", "300", "Modern theater with AV equipment"));
        locations.add(new Location("Exhibition Center", "202 Maple St", "600", "Exhibition space for art and expos"));

        // Initialize events with locations
        events.add(new Event("Music Festival", "Annual outdoor music festival", 1, locations.get(2)));
        events.add(new Event("Art Expo", "Modern art exhibition", 1.0, locations.get(4)));
        events.add(new Event("Science Fair", "Science projects and innovation", 1.0, locations.get(0)));
        events.add(new Event("Tech Conference", "Latest in tech and gadgets", 1.0, locations.get(1)));
        events.add(new Event("Food Fest", "Enjoy cuisines from around the world", 1.0, locations.get(2)));
        events.add(new Event("Film Screening", "Indie films showcase", 1.0, locations.get(3)));
        events.add(new Event("Book Fair", "Meet authors and publishers", 1.0, locations.get(4)));
        events.add(new Event("Marathon", "Annual charity run", 1.0, locations.get(2)));
        events.add(new Event("Dance Workshop", "Learn from professional dancers", 1.0, locations.get(0)));
        events.add(new Event("Comedy Night", "Stand-up comedy from top comedians", 1.0, locations.get(3)));
    }


}