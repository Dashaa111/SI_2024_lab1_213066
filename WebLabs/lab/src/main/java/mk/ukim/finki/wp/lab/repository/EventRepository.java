package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {
    public List<Event> findAll() {
        return DataHolder.events;
    }

    public Optional<Event> findById(Long id) {
        return DataHolder.events.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream().filter(i -> i.getName().toLowerCase().contains(text.toLowerCase()) || i.getDescription().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public List<Event> searchEventsByTextAndScore(String text, double rating) {
        return DataHolder.events.stream().filter(i -> i.getName().toLowerCase().contains(text.toLowerCase()) && i.getPopularityScore() <= rating).collect(Collectors.toList());
    }


    public List<Event> searchEventsByScore(double rating) {
        return DataHolder.events.stream().filter(i -> i.getPopularityScore() <= rating).collect(Collectors.toList());
    }

    public void deleteById(Long id) {

        int a = 0;
        DataHolder.events.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Event> saveEvent(Long id, String name, String description, double popularityScore, Location location) {


        if (id != null) {
            Optional<Event> optionalEvent = findById(id);
            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                event.setName(name);
                event.setDescription(description);
                event.setPopularityScore(popularityScore);
                event.setLocation(location);
                return Optional.of(event);
            }


        }

        DataHolder.events.removeIf(i -> i.getName().equals(name));

        Event event = new Event(name, description, popularityScore, location);
        DataHolder.events.add(event);
        return Optional.of(event);

    }

    public void upVoteEvent(Long id){
        if (id != null) {
            Optional<Event> optionalEvent = findById(id);
            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                event.setPopularityScore(event.getPopularityScore()+1);
                event.setHasUpvote(true);
            }

        }
    }

}