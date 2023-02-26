package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Client;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    //ADD CLIENT
    @PostMapping
    public Result addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    //GET CLIENTS
    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }

    //GET CLIENT BY ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    //DELETE CLIENT
    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }

    //EDIT CLIENT
    @PutMapping("/{id}")
    public Result editClient(@PathVariable Integer id, @RequestBody Client client){
        return clientService.editClient(id, client);
    }
}
