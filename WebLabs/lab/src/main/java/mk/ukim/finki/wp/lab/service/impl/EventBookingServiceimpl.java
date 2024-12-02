package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.EventBookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceimpl implements EventBookingService {


    private final EventBookingRepositoryJPA eventBookingRepository;
    private final EventRepositoryJPA eventRepository;

    public EventBookingServiceimpl(EventBookingRepositoryJPA eventBookingRepository, EventRepositoryJPA eventRepository) {
        this.eventBookingRepository = eventBookingRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventId, String attendeeName, String attendeeAddress, int numberOfTickets) {
//        Event event = eventRepository.findById(eventName).orElseThrow(() -> new IllegalArgumentException("Event with name " + eventName + " not found"));
//
//        EventBooking eventBooking = new EventBooking(attendeeName, attendeeAddress, (long) numberOfTickets, event);
//        return eventBookingRepository.save(eventBooking);
        return null;
    }

    @Override
    public EventBooking placeBooking(Long eventId, String attendeeName, String attendeeAddress, int numberOfTickets) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event with id " + eventId + " not found"));

        EventBooking eventBooking = new EventBooking(attendeeName, attendeeAddress, (long) numberOfTickets, event);
        return eventBookingRepository.save(eventBooking);
    }

    @Override
    public void addBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        //eventBookingRepository.addEvent(new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets));

    }

    @Override
    public List<EventBooking> listAll() {
        return eventBookingRepository.findAll();
    }

    @Override
    public List<EventBooking> searchEventsByEventName(String text) {
        return eventBookingRepository.findByEvent_NameContainingIgnoreCase(text);
    }

    @Override
    public List<EventBooking> searchEventsByAtendeeName(String name) {
        return eventBookingRepository.findByAttendeeNameContainingIgnoreCase(name);
    }

    @Override
    public List<EventBooking> searchEventsByNumTickets(int tickets) {
        return eventBookingRepository.findByNumberOfTickets(tickets);
    }

    @Override
    public List<EventBooking> searchEvent(String eventName, String attendeeName, int tickets) {
        return eventBookingRepository.findByEventNameAndAttendeeNameAndNumberOfTickets(eventName, attendeeName, tickets);
    }
}