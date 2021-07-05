package com.example.rediscachedemo;

import com.example.rediscachedemo.entity.User;
import com.example.rediscachedemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RedisCacheDemoApplication implements CommandLineRunner {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Autowired
    public RedisCacheDemoApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(RedisCacheDemoApplication.class, args);
    }

    @Override
    public void run(String... strings) {
        //Populating embedded database here
        LOG.info("Saving users. Current user count is {}.", userRepository.count());
        User shubham = new User("Shubham", 2000L);
        User pankaj = new User("Pankaj", 29000L);
        User lewis = new User("Lewis", 550L);

        userRepository.save(shubham);
        userRepository.save(pankaj);
        userRepository.save(lewis);
        LOG.info("Done saving users. Data: {}.", userRepository.findAll());
    }

}