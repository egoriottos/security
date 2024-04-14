package com.example.springsecurityapp.controllers;

import com.example.springsecurityapp.entity.MyUser;
import com.example.springsecurityapp.models.Application;
import com.example.springsecurityapp.services.AppService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {
    private AppService appService;
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to the unprotected page";
    }

    @GetMapping("/allApp")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> getAll(){
        return appService.allApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application applicationById(@PathVariable int id){
        return appService.applicationById(id);
    }

    @PostMapping("/new user")
    public String addUser(@RequestBody MyUser user){
        appService.addUser(user);
        return "user was created";
    }
}
