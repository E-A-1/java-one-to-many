package com.example.springboot.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.springboot.application.entitiy.User;
import com.example.springboot.application.repository.UserCustomRepo;
import com.example.springboot.application.repository.UserRepo;

@Configuration
public class AppService {

    @Autowired
	UserRepo userRepo;

    @Autowired
	UserCustomRepo userCustomRepo;

    @EventListener({ ContextRefreshedEvent.class })
    public void testRepo(){
        try {
            Pageable pageable=PageRequest.of(0, 10);
            userCustomRepo.getAllData(0, 5); 
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);
        }
      
    }
}
