package org.example.springTut;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Event event;

    public void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger, Event event) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.event = event;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        App app = ((App) applicationContext.getBean("app"));
//        app.logEvent("event for user 1");
        ((App) applicationContext.getBean("app")).logEvent("event for user 1");
        ((App) applicationContext.getBean("app")).logEvent("event for user 1");
        ((App) applicationContext.getBean("app")).logEvent("event for user 1");
        ((App) applicationContext.getBean("app")).logEvent("event for user 1");

    }
}
