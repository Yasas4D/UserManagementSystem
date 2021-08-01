package com.example.ums.service;

import com.example.ums.model.User;
import com.example.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoader {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void load() {
        List<User> usersList = getList();
        userRepository.saveAll(usersList);
    }

    public List<User> getList(){
        List<User> usersList = new ArrayList<>();
        usersList.add(new User("Yasas",25, "Galle"));
        usersList.add(new User("Kamal",42, "Kandy"));
        usersList.add(new User("Sahani",24, "Colombo"));
        return usersList;
    }
}
