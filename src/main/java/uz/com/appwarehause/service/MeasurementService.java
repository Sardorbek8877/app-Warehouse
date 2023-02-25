package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Measurement;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement){
        boolean existBuName = measurementRepository.existsByName(measurement.getName());
        if (existBuName)
            return new Result("Measurement already exist!", false);

        measurementRepository.save(measurement);
        return new Result("Measurement successfully added!", true);
    }

    public List<Measurement> getMeasurementService(){
        List<Measurement> measurementList = measurementRepository.findAll();
        return measurementList;
    }

    public Measurement getMeasurementByIdService(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()){
            Measurement measurement = optionalMeasurement.get();
            return measurement;
        }
        return new Measurement();
    }

    public Result deleteMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()){
            measurementRepository.deleteById(id);
            return new Result("Measurement successfully deleted!", true);
        }
        return new Result("Measurement not found!", false);
    }

    public Result editMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new Result("Measurement not found", false);

        Measurement editingMeasurement = optionalMeasurement.get();
        editingMeasurement.setName(measurement.getName());
        measurementRepository.save(editingMeasurement);
        return new Result("Measurement successfully edited!", true);
    }
}
