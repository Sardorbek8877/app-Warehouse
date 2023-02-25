package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Measurement;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    //ADD MEASUREMENT
    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    //GET LIST MEASUREMENT
    @GetMapping
    public List<Measurement> getMeasurement(){
        return measurementService.getMeasurementService();
    }

    //GET MEASUREMENT BY ID
    @GetMapping("/{id}")
    public Measurement getMeasurementById(@PathVariable Integer id){
        Measurement measurement = measurementService.getMeasurementByIdService(id);
        return measurement;
    }

    //DELETE MEASUREMENT
    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id){
        Result result = measurementService.deleteMeasurement(id);
        return result;
    }

    //EDIT MEASUREMENT
    @PutMapping("/{id}")
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.editMeasurement(id, measurement);
    }
}
