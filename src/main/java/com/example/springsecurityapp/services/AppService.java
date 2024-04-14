package com.example.springsecurityapp.services;

import com.example.springsecurityapp.entity.MyUser;
import com.example.springsecurityapp.models.Application;
import com.example.springsecurityapp.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AppService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    private List<Application> applications;

    @PostConstruct
    public void loadAppInDB(){
        Faker faker = new Faker();
        applications= IntStream.rangeClosed(1,100)
                .mapToObj(element->Application.builder()
                        .id(element)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build())
                .toList();
    }

    public List<Application> allApplications(){
        return applications;
    }

    public Application applicationById(int id){
        return applications.stream()
                .filter(app->app.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(MyUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
