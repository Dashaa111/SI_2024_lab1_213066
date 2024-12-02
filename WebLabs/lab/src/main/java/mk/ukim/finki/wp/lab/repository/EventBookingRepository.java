package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventBookingRepository {
    public void addEvent(EventBooking eventBooking){
        DataHolder.eventBookingList.add(eventBooking);
    }

    public List<EventBooking> listAll() {
        return DataHolder.eventBookingList;
    }

//    public List<EventBooking> searchEventsByEventName(String text) {
//        return DataHolder.eventBookingList.stream().filter(i -> i.getEventName().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
//    }

    public List<EventBooking> searchEventsByAtendeeName(String name) {
        return DataHolder.eventBookingList.stream().filter(i -> i.getAttendeeName().toLowerCase().contains(name.toLowerCase()) ).collect(Collectors.toList());
    }


    public List<EventBooking> searchEventsByNumTickets(int tickets) {
        return DataHolder.eventBookingList.stream().filter( i->i.getNumberOfTickets() == tickets).collect(Collectors.toList());
    }

//    public List<EventBooking> searchEvent(String eventName,String atendeeName,int tickets) {
//        return DataHolder.eventBookingList.stream().filter( i->i.getEventName().toLowerCase().contains(eventName.toLowerCase()) && i.getAttendeeName().toLowerCase().contains(atendeeName.toLowerCase()) && i.getNumberOfTickets() == tickets).collect(Collectors.toList());
//    }





}