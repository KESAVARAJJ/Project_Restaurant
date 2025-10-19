package com.example.Project_Restaurant.repository;

import com.example.Project_Restaurant.module.Restaur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestRepo extends JpaRepository<Restaur,Integer> {

}
