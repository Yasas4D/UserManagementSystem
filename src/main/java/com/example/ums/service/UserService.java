package com.example.ums.service;

import com.example.ums.model.User;
import com.example.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable("allUserCache")
    public List<User> getAllUsers() {
        System.out.println("Getting All the users from DB! | Not Cached");
//        System.out.println("Cache manager....");
//        System.out.println(cacheManager.toString());
        return userRepository.findAll();
    }
    @Cacheable(key = "#id",value = "oneUserSearchCache")
    public User searchUser(int id) {
        System.out.println("Getting the user from DB!");
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
