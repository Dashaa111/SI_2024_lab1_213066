package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, int rating);
    void deleteById(Long id);
    Optional<Event> findById(Long id);
    void save(Event event);
    void deleteByLocationName(String name);
}
