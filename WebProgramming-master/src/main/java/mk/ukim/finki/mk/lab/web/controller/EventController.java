package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.service.EventService;
import mk.ukim.finki.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("events", eventService.listAll());
        return "listEvents";
    }

    @PostMapping
    public String getFilteredEventsPage(HttpServletRequest req, Model model) {
        String searchText = req.getParameter("search-text");
        String searchRating = req.getParameter("rating-text");
        int parsedRating = -1;
        if(!searchRating.isEmpty())
        {
            parsedRating = Integer.parseInt(searchRating);
        }

        List<Event> events;
        if(!searchText.isEmpty() && !searchRating.isEmpty())
        {
            events = eventService.searchEvents(searchText, parsedRating);
        }
        else if (!searchText.isEmpty())
        {
            events = eventService.searchEvents(searchText, -1);
        }
        else
        {
            events = eventService.searchEvents(null, parsedRating);
        }

        model.addAttribute("events", events);
        return "listEvents";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        Optional<Event> event = eventService.findById(id);
        if(event.isPresent())
        {
            model.addAttribute("event", event.get());
            model.addAttribute("allLocations", locationService.findAll());
            return "add-event";
        }
        return "redirect:/events";
    }

    @PostMapping("/edit/{id}")
    public String updateEvent(@PathVariable Long id,
                              @RequestParam String name,
                              @RequestParam String desc,
                              @RequestParam double popularity,
                              @RequestParam Long locationId) {
        Optional<Event> event = eventService.findById(id);
        Optional<Location> location = locationService.findById(locationId);
        if(event.isPresent() && location.isPresent())
        {
            Event e = event.get();
            e.setName(name);
            e.setDescription(desc);
            e.setLocation(location.get());
            eventService.deleteById(id);
            eventService.save(e);
        }
        else if(location.isPresent() && id == 0)
        {
            Event e = new Event(name, desc, popularity, location.get());
            eventService.save(e);
        }
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return "redirect:/events";
    }

    @GetMapping("/add")
    public String saveEvent(Model model) {
        model.addAttribute("allLocations", locationService.findAll());
        return "add-event";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        Optional<Event> event = eventService.findById(id);
        if(event.isPresent())
        {
            model.addAttribute("event", event.get());
            return "add-event";
        }
        model.addAttribute("hasError", true);
        model.addAttribute("events", eventService.listAll());
        return "listEvents";
    }
}
