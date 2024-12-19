package mk.ukim.finki.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.repository.jpa.EventRepository;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, int rating) {
            if(text != null && rating != -1)
        {
            return eventRepository.findByDescriptionContainingAndPopularityScore  (text, rating);
        }
        else if(text != null)
        {
            return eventRepository.findByDescriptionContaining(text);
        }
        else
        {
            return eventRepository.findByPopularityScore(rating);
        }
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    @Transactional
    public void deleteByLocationName(String name) {
        eventRepository.deleteByLocation_Name(name);
    }
}
