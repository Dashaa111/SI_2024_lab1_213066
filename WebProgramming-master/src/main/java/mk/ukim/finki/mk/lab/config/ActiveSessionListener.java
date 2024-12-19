package mk.ukim.finki.mk.lab.config;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.Getter;

@WebListener
public class ActiveSessionListener implements HttpSessionListener {
    @Getter
    private static int sessionCounter = 1;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        synchronized (this) {
            sessionCounter++;
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        synchronized (this) {
            sessionCounter--;
        }
    }
}