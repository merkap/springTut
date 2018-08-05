package org.example.springTut.log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    public void init() throws IOException {
        this.file = new File(fileName);
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), Charset.defaultCharset(), true);
        } catch (IOException ignored) {

        }
    }
}
