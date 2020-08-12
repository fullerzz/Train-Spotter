package com.github.fullerzz.fullerzztrainspotter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class EventManager {
    private HashMap<Integer, Train> trains;

    public EventManager(){
        trains = new HashMap<Integer, Train>();
    }

    public void loadFromDB() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:train.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SIGHTINGS;" );

            while (rs.next()) {
                int train_num = rs.getInt("train_num");
                String time_seen = rs.getString("time_seen");
                String direction  = rs.getString("direction");

                Train temp = getTrain(train_num);
                if (temp == null) {
                    temp = new Train(train_num);
                    addTrain(temp);
                }
                Event sighting = new Event(time_seen, direction, train_num);
                temp.addSighting(sighting);

                System.out.println("Train Number = " + train_num);
                System.out.println("Time seen = " + time_seen);
                System.out.println("Direction = " + direction);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void addNewSighting(int train_num, String datetime, String direction) {
        Event event = new Event(datetime, direction, train_num);
        Train temp = getTrain(train_num);
        if (temp == null) {
            temp = new Train(train_num);
        }
        temp.addSighting(event);
        addTrain(temp);
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:train.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO SIGHTINGS (train_num, time_seen, direction) " +
                    "VALUES (" + train_num + ", '" + datetime + "', '" + direction + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public Train getTrain(int train_num) {
        return trains.get(train_num);
    }

    public void addTrain(Train train) {
        trains.put(train.getNumber(), train);
    }

}