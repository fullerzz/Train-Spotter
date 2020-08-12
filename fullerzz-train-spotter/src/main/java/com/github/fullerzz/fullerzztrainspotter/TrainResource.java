package com.github.fullerzz.fullerzztrainspotter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method=RequestMethod.POST, value="/trains/addSighting")
    public void addSighting(@RequestBody Event event) {
        trainManagementService.addSighting(event);
    }
}
