package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.impl.EventServiceimpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "eventListServlet", urlPatterns = "/servlet/eventlist")
public class EventListServlet extends HttpServlet {


    private final SpringTemplateEngine springTemplateEngine;
    private final EventService eventService;

    public EventListServlet(SpringTemplateEngine springTemplateEngine, EventService eventService) {
        this.springTemplateEngine = springTemplateEngine;
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String searchText = req.getParameter("searchText");
        String minRatingStr = req.getParameter("minRating");

        if (searchText != null && searchText.isEmpty()) {
            searchText = null;
        }
        if (minRatingStr != null && minRatingStr.isEmpty()) {
            minRatingStr = null;
        }


        if (searchText != null) {
            if (minRatingStr != null) {
                context.setVariable("events", eventService.searchEventsByTextAndScore(searchText, Double.parseDouble(minRatingStr)));
            } else {
                context.setVariable("events", eventService.searchEvents(searchText));
            }
        } else if (minRatingStr != null) {
            context.setVariable("events", eventService.searchEventsByScore(Double.parseDouble(minRatingStr)));

        } else {
            context.setVariable("events", eventService.listAll());
        }

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("eventName");
        String numTickets = req.getParameter("numTickets");

        req.getSession().setAttribute("eventName", eventName);
        req.getSession().setAttribute("numTickets", numTickets);


        resp.sendRedirect("/servlet/eventBooking");

    }
}