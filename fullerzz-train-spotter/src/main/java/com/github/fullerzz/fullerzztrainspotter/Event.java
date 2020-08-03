package com.github.fullerzz.fullerzztrainspotter;

public class Event {
    private final String datetime; // 7/7/20 - 1:45pm example
    private final String dir;

    public Event(String datetime, String dir) {
        this.datetime = datetime;
        this.dir = dir;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getDirection() {
        return dir;
    }
}