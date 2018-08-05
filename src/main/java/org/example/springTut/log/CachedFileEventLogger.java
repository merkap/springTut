package org.example.springTut.log;

import java.util.List;

public class CachedFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> events;

    public CachedFileEventLogger(String fileName, int cacheSize, List<Event> events) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.events = events;
    }

    @Override
    public void logEvent(Event event) {
        events.add(event);

        if (events.size() == cacheSize) {
            writeEventsFromCache();
        }
    }

    public void writeEventsFromCache() {
        for (Event e :
                events) {
            super.logEvent(e);
        }
        events.clear();
        System.out.println("clearing cache");
    }

    public void destroy() {
        if (!events.isEmpty()) {
            writeEventsFromCache();
        }
    }
}
