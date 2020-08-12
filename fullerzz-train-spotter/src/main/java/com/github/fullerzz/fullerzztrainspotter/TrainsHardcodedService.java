package com.github.fullerzz.fullerzztrainspotter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class TrainsHardcodedService {
    private static List<Train> trainsList = new ArrayList<>();
    private static HashMap<Integer, Train> trains = new HashMap<>();
    private static int trainId = 0;

    static {
        loadFromDB();
        for (Map.Entry<Integer, Train> entry : trains.entrySet()) {
            trainsList.add(entry.getValue());
        }
    }

    public static void loadFromDB() {
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

    public static Train getTrain(int train_num) {
        return trains.get(train_num);
    }

    public static void addTrain(Train train) {
        trains.put(train.getNumber(), train);
        trainsList.add(train);
    }

    public List<Train> findAll() {
        return trainsList;
    }

    public Train findTrain(int id) {
        try {
            return trains.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public void addSighting(Event event) {
        Train train = findTrain(event.getTrainNum());
        if (train == null) {
            train = new Train(event.getTrainNum());
            addTrain(train);
        }
        train.addSighting(event);

        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:train.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO SIGHTINGS (train_num, time_seen, direction) " +
                    "VALUES (" + event.getTrainNum() + ", '" + event.getDatetime() + "', '" + event.getDirection() + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
