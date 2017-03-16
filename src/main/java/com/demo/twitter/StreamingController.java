package com.demo.twitter;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.TwitterUser;
import com.demo.utility.TwitterUtility;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

@RestController
public class StreamingController {
	
	

	 /**
     * Display Users
     * @param userName
     * @return
     */
    @RequestMapping("/twitter/listen")
    public String listen() {
    	//Start a thread
    	(new UserListener()).start();
    	return "OK";
    	
    }
}
