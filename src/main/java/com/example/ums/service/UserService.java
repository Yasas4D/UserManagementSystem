package com.example.ums.service;

import com.example.ums.model.User;
import com.example.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(key = "'allUser'", value = "allUserCache")
    public List<User> getAllUsers() {
        System.out.println("Getting All the users from DB! | Not Cached");
//        System.out.println("Cache manager....");
//        System.out.println(cacheManager.toString());
        return userRepository.findAll();
    }

    @Cacheable(key = "#id", value = "oneUserSearchCache")
    public User searchUser(int id) {
        System.out.println("Getting the user from DB!");
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @CachePut(key = "'allUser'", value = "allUserCache")
    public List<User> addNewUser(@RequestBody User user) {
        System.out.println("Registering the new user");
        userRepository.save(user);
        return userRepository.findAll();
    }

    @CacheEvict(value = "allUserCache", allEntries = true)
    public boolean removeUser(int id) {
        System.out.println("Getting the user from DB!");
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            System.out.println("Successfully deleted the user!");
            return true;
        }
        System.out.println("User Not Found!");
        return false;
    }

}
