package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.service.EventService;
import mk.ukim.finki.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;
    private final EventService eventService;

    public LocationController(LocationService locationService, EventService eventService) {
        this.locationService = locationService;
        this.eventService = eventService;
    }

    @GetMapping
    public String getLocations(Model model) {
        model.addAttribute("locations", locationService.findAll());
        return "listLocations";
    }

    @GetMapping("/add")
    public String addLocation() {
        return "add-location";
    }

    @PostMapping("/add")
    public String addNewLocation(@RequestParam String name,
                                 @RequestParam String desc,
                                 @RequestParam int capacity,
                                 @RequestParam String address) {
        String cap = String.valueOf(capacity);
        Location location = new Location(name, address, cap, desc);
        locationService.add(location);
        return "redirect:/locations";
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        Optional<Location> location = locationService.findById(id);
        if(location.isPresent())
        {
            eventService.deleteByLocationName(location.get().getName());
            locationService.deleteById(id);
        }
        return "redirect:/locations";
    }
}
