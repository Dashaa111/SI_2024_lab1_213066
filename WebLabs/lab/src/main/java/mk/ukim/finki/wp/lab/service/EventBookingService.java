package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.model.Event;

import java.util.List;

public interface EventBookingService {
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
    EventBooking placeBooking(Long eventId, String attendeeName, String attendeeAddress, int numberOfTickets);

    void addBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);

    List<EventBooking> listAll();

    List<EventBooking> searchEventsByEventName(String text);
    List<EventBooking> searchEventsByAtendeeName(String name);
    List<EventBooking> searchEventsByNumTickets(int tickets);
    List<EventBooking> searchEvent(String eventName,String atendeeName,int tickets);

}