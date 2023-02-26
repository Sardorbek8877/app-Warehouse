package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Input;
import uz.com.appwarehause.payload.InputDto;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    //ADD INPUT
    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }

    //GET INPUTS
    @GetMapping
    public List<Input> getInputs(){
        return inputService.getInputs();
    }

    //GET INPUT BY ID
    @GetMapping("/{id}")
    public Input getInputById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }

    //DELETE INPUT
    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }

    //EDIT INPUT
    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto){
        return inputService.editInput(id, inputDto);
    }

}
