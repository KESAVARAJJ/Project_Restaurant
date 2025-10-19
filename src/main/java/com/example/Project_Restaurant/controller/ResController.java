package com.example.Project_Restaurant.controller;

import com.example.Project_Restaurant.module.Restaur;
import com.example.Project_Restaurant.service.RestServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
public class ResController {

//    ResController(){
//        System.out.println("hi");
//    }

    @Autowired
    RestServ restServ;


    @GetMapping("restaurant")
    public List<Restaur>getorder(){
        return restServ.getorder();
    }

    @GetMapping("restaurant/{bid}")
    public Restaur getorder(@PathVariable int bid){
        return restServ.getorder(bid);
    }

//    @PostMapping("restaurant")
//    public String addOrder(@RequestBody Restaur restaur){
//        restServ.addOrder(restaur);
//        return "Data added";
//    }

    @PostMapping("restaurant")
    public ResponseEntity<String> addOrder(@Param("bid")int bid, @Param("name") String name, @Param("email") String email, @Param("orders") String orders, @Param("tamt") String tamt, @Param("tid")String tid){
        String msg = restServ.addOrder(bid,name,email,orders,tamt,tid);
        if(msg.equals("Added")){
            return new ResponseEntity<>(msg, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }




}
