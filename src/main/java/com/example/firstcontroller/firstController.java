package com.example.firstcontroller;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class firstController {
    @RequestMapping(value = "hello")
    public Object Hallo(){
        return "Hello I'm your server";
    }

    @RequestMapping(value = "random")
    public Object Random(){
        return "Hello your number is "+new Random().nextInt(50);
    }

    @RequestMapping(value = "player")
    public Object Player(){
        return new Player("Lionel","Messi",30);
    }

    List<Player> listOfPlayers = new ArrayList<>();
   @PostConstruct
    public void AddToList(){
        listOfPlayers.add(new Player("Lionel","Messi",50));
        listOfPlayers.add(new Player("Andres","Iniesta",15));
        listOfPlayers.add(new Player("Bruno","Fernande",20));
    }

    @RequestMapping(value = "player-chosen")
    public Object PlayerChosen(int index){
        return this.listOfPlayers.get(index);
    }

    @RequestMapping(value = "players")
    public Object Players(){
        return this.listOfPlayers;
    }

    @RequestMapping(value = "get-chosen-player/{index}")
     public Object GetPlayer(@PathVariable int index){
     return this.listOfPlayers.get(index);
    }



}

