package org.example.springTut.log;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.print(event);
    }
}
