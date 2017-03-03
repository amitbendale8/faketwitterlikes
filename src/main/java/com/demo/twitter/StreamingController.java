package com.demo.twitter;

import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.status.StatusListener;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class StreamingController {
	 /**
     * Display Users
     * @param userName
     * @return
     */
    @RequestMapping("/listen")
    public String listen() {
    		StatusListener listener = new StatusListener(){
            public void onStatus(Status status) {
                System.out.println(status.getUser().getName() + " : " + status.getText());
            }
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
			@Override
			public void addStatusEvent(ch.qos.logback.core.status.Status arg0) {
				// TODO Auto-generated method stub
				
			}
        };
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(listener);
        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
        twitterStream.sample();
    }
}
