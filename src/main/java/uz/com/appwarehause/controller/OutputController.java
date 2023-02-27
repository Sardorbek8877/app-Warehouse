package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Output;
import uz.com.appwarehause.payload.OutputDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    //ADD OUTPUT
    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }

    //GET OUTPUTS
    @GetMapping
    public List<Output> getOutputs(){
        return outputService.getOutputs();
    }

    //GET OUTPUT BY ID
    @GetMapping("/{id}")
    public Output getOutputById(@PathVariable Integer id){
        return outputService.getOutputById(id);
    }

    //DELETE OUTPUT
    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        return outputService.deleteOutput(id);
    }

    //EDIT OUTPUT
    public Result editOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto){
        return outputService.editOutput(id, outputDto);
    }
}
