package org.example.springTut;

import org.example.springTut.log.Event;
import org.example.springTut.log.EventLogger;
import org.example.springTut.log.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private Map<EventType, EventLogger> loggers;
    private EventLogger defaultEventLogger;

    public App(Client client, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.loggers = loggers;
    }

    public void setDefaultEventLogger(EventLogger defaultEventLogger) {
        this.defaultEventLogger = defaultEventLogger;
    }

    public void logEvent(Event event, EventType type, String msg) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultEventLogger;
        }
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(message);
        logger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
//        App app = ((App) cxt.getBean("app"));
//        app.logEvent("event for user 1");
        ((App) cxt.getBean("app")).logEvent((Event) cxt.getBean("event"), EventType.INFO, "info event for user 1");
        ((App) cxt.getBean("app")).logEvent((Event) cxt.getBean("event"), null, "event for user 1");
        ((App) cxt.getBean("app")).logEvent((Event) cxt.getBean("event"), EventType.ERROR, "error event for user 1");
        ((App) cxt.getBean("app")).logEvent((Event) cxt.getBean("event"), null, "event for user 1");
        cxt.close();
    }
}
