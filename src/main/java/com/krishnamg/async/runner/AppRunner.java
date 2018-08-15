package com.krishnamg.async.runner;

import com.krishnamg.async.dto.User;
import com.krishnamg.async.service.GithubLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final GithubLookupService githubLookupService;

    public AppRunner(GithubLookupService githubLookupService) {
        this.githubLookupService = githubLookupService;
    }

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture<User> user1 = githubLookupService.findUser("krishnamg");
        CompletableFuture<User> user2 = githubLookupService.findUser("findkrishnam");
        CompletableFuture<User> user3 = githubLookupService.findUser("nfultz");

        CompletableFuture.allOf(user1, user2, user3);

        // Print results, including elapsed time
        LOGGER.info("--> " + user1.get());
        LOGGER.info("--> " + user2.get());
        LOGGER.info("--> " + user3.get());
    }


}
