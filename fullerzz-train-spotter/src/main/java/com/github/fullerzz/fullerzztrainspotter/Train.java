package com.github.fullerzz.fullerzztrainspotter;

import java.util.ArrayList;

public class Train {
    private int number;
    private int numSightings;
    private ArrayList<Event> sightings;

    public Train(int number) {
        this.number = number;
        numSightings = 0;
        sightings = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumSightings() {
        return numSightings;
    }

    public String getLastSighting() {
        int size = sightings.size();
        try {
            Event sighting = sightings.get(size - 1);
            String ret = sighting.getDatetime() + " heading " + sighting.getDirection();
            return ret;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void addSighting(Event sighting) {
        sightings.add(sighting);
        numSightings++;
    }

}