package com.github.fullerzz.fullerzztrainspotter;

public class Event {
    private final String datetime; // 7/7/20 - 1:45pm example
    private final String dir;
    private final int trainNum;

    public Event(String datetime, String dir, int trainNum) {
        this.datetime = datetime;
        this.dir = dir;
        this.trainNum = trainNum;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getDirection() {
        return dir;
    }

    public int getTrainNum() {
        return trainNum;
    }
}