package mk.ukim.finki.wp.lab.model;



import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;
    @ManyToOne
    private Event event;

    public EventBooking(String attendeeName, String attendeeAddress, Long numberOfTickets, Event event) {
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
        this.event = event;
    }
}