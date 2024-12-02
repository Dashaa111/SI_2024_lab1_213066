package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();

    Optional<Event> findById(Long id);
    List<Event> searchEvents(String text);

    List<Event> searchEventsByTextAndScore(String text, double rating);
    List<Event> searchEventsByScore(double rating);

    Optional<Event> saveEvent(Long id,String name, String description, double popularityScore, Long locationId);
    void deleteById(Long id);

    void hasIncremented(Long id);

    List<Event> findAllByLocation(Long locationId);


}