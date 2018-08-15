package com.krishnamg.async.service;

import com.krishnamg.async.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class GithubLookupService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    private final RestTemplate restTemplate;

    public GithubLookupService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<User> findUser(String userName) throws InterruptedException {
        LOGGER.info("Looking up " + userName);
        String url = String.format("https://api.github.com/users/%s", userName);
        User user = restTemplate.getForObject(url, User.class);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(user);
    }


}
