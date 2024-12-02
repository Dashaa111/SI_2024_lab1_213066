package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@WebServlet(name = "eventBookingServlet", urlPatterns = "/servlet/eventBooking")
public class EventBookingServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final EventBookingService eventBookingService;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine, EventBookingService eventBookingService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventBookingService = eventBookingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = (String) req.getSession().getAttribute("eventName");
        String numTicketsString = (String) req.getSession().getAttribute("numTickets");

        if (eventName == null || numTicketsString == null || eventName.isEmpty() || numTicketsString.isEmpty()) {
            resp.sendRedirect("/");
            return;
        }

        int numTickets = Integer.parseInt(numTicketsString);
        String attendeeName = "Petko Petkov"; // Hardcoded for this example
        String attendeeAddress = req.getRemoteAddr();

        EventBooking booking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numTickets);

        req.getSession().setAttribute("bookingObj", booking);

        eventBookingService.addBooking(eventName, attendeeName, attendeeAddress, numTickets);


        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("booking",booking);
        context.setVariable("clientIp",attendeeAddress);
        context.setVariable("eventBookings",eventBookingService.listAll());

        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getSession().removeAttribute("eventName");
        req.getSession().removeAttribute("numTickets");


        resp.sendRedirect("/servlet/listBookings");

    }
}