package com.example.Project_Restaurant.service;

import com.example.Project_Restaurant.module.Restaur;
import com.example.Project_Restaurant.repository.RestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class RestServ {

//    RestServ(){
//        System.out.println("hello");
//    }

    @Autowired
    RestRepo restRepo;




    public List<Restaur> getorder() {
        return restRepo.findAll();
    }

    public Restaur getorder(int bid) {
        return restRepo.findById(bid).orElse(null);
    }

//    public void addOrder(Restaur restaur) {
//        restRepo.save(restaur);
//    }

     public  String addOrder(int bid, String name, String email,String orders, String tamt, String tid){
        Restaur restaur= new Restaur(bid,name,email,orders,tamt,tid);
      if(restaur.getBid() != 0){
          restRepo.save(restaur);
          return "Added";
      } else {
          return "Failed to add";
      }

     }


}
