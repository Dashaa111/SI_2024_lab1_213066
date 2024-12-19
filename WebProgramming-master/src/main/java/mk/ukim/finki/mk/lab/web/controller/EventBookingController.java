package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
public class EventBookingController {

    @GetMapping
    public String getBooking() {
        return "bookingConfirmation";
    }

    @PostMapping
    public String postBooking(HttpServletRequest request, Model model) {
        model.addAttribute("clientIP", request.getRemoteAddr());
        model.addAttribute("eventName", request.getParameter("selectedEvent"));
        model.addAttribute("numTickets", request.getParameter("numTickets"));
        return "bookingConfirmation";
    }
}
