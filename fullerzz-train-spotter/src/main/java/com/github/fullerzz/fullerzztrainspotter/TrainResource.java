package com.github.fullerzz.fullerzztrainspotter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class TrainResource {

    @Autowired
    private TrainsHardcodedService trainManagementService;

    @GetMapping("/trains")
    public List<Train> getAllTrains() {
        return trainManagementService.findAll();
    }

    @GetMapping("/trains/{trainId}")
    public Train getTrainInfo(@PathVariable int trainId) {
        return trainManagementService.findTrain(trainId);
    }
}
