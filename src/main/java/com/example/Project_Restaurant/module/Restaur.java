package com.example.Project_Restaurant.module;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Restaur {
    @Id
    private int bid;
    private String name;
    private  String email;
    private String orders;
    private String tamt;
    private String tid;

    public Restaur(int bid, String name, String email,String orders, String tamt, String tid){
        this.bid=bid;
        this.name=name;
        this.email=email;
        this.orders=orders;
        this.tamt=tamt;
        this.tid=tid;
    }
    Restaur(){}

}

