package com.example.firstcontroller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

    @CrossOrigin(origins = "http://localhost:3000")
    @RestController
    public class ClientsController {
        private List<Client> clients = new ArrayList<>();

        @PostConstruct
        public void addClients(){
            clients.add(new Client("Hagai Haimi","0527676799", "chagai@ gmail.com"));
            clients.add(new Client("David Didi","052767679", "david"));
            clients.add(new Client("Mishel Cohen","0537485678", "mishel78"));
            clients.add(new Client("Michel Levi","05276767", "michel@gmail.com"));
        }

        @RequestMapping(value = "get-all-clients", method = RequestMethod.GET)
        public List<Client> getAllClients() {
            return clients;
        }


        @RequestMapping(value = "send", method = RequestMethod.POST)
        public List<Client> send(boolean onlyValidEmail, boolean onlyValidPhone) {
            List<Client> returnList = new ArrayList<>();
            for (Client client : this.clients) {
                if (onlyValidPhone && !onlyValidEmail) {
                    if (client.getPhone().length() > 9 && client.getPhone().charAt(0) == '0'){
                        returnList.add(client);
                        System.out.println("Hello "+client.getName());
                    }
                }
                if (onlyValidEmail && !onlyValidPhone) {
                    if (client.getEmail().contains("@")) {
                        returnList.add(client);
                        System.out.println("Hello "+client.getName());
                    }
                }
                if (onlyValidEmail && onlyValidPhone) {
                    if (client.getEmail().contains("@") && client.getPhone().length() > 9 && client.getPhone().charAt(0) == '0'){
                        returnList.add(client);
                        System.out.println("Hello "+client.getName());
                    }
                }
            }
            if (returnList.isEmpty()) returnList = clients;
            return returnList;
        }



    }





