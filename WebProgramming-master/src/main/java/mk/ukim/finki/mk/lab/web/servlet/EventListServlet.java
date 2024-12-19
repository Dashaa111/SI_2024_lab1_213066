package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.config.ActiveSessionListener;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Event-List-Servlet", urlPatterns = "")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("events", eventService.listAll());

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("active", true);

        context.setVariable("activeSessions", ActiveSessionListener.getSessionCounter());

        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

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

        context.setVariable("events", events);
        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }
}
