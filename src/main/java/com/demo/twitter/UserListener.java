package com.demo.twitter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

@Component
@Scope("prototype")
public class UserListener extends Thread{
	
	public Map<String,Integer> userMap = new HashMap<String, Integer>();
	@Override
	public void run() {

		System.out.println(getName() + " is running");

    	System.out.println("Started Listening");
    	
    	TwitterUser user = TwitterUtility.getUser();
    	ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(user.getConsumerKey());
        cb.setOAuthConsumerSecret(user.getConsumerSecret());
        cb.setOAuthAccessToken(user.getAccessToken());
        cb.setOAuthAccessTokenSecret(user.getAccessTokenSecret());

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
                User user = status.getUser();
                
                // gets Username
                
                String username = status.getUser().getScreenName();
                System.out.println(username);
  
                if(userMap.containsKey(username)){
	                long tweetID = status.getId(); 
	                int likeCount = TwitterUtility.likeTweet(status);
	                int retweetCount = TwitterUtility.retweet(status);
	                System.out.println("Total Retweets: "+retweetCount);
	                System.out.println("Total likes: "+likeCount);
                }

            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
    
        userMap.put("khurr_mundi", 5);
        //fq.follow(new long[] { 12354654});
        fq.follow(new long[] {1021626787});
        twitterStream.addListener(listener);
        twitterStream.filter(fq);  
		while(true){
			
		}
	}

}