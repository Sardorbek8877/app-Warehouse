package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Client;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client){
        boolean exist = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (exist)
            return new Result("Phone number already exist", false);

        clientRepository.save(client);
        return new Result("Client added", true);
    }

    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            return new Client();
        Client client = optionalClient.get();
        return client;
    }

    public Result deleteClient(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()){
            clientRepository.deleteById(id);
            return new Result("Client deleted", true);
        }
        return new Result("Client not found", false);
    }

    public Result editClient(Integer id, Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            return new Result("Client not found", false);
        Client editingClient = optionalClient.get();
        editingClient.setName(client.getName());
        editingClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editingClient);
        return new Result("Client edited", true);
    }

}
