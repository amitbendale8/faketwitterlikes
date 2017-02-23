package com.demo.twitter;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.utility.TwitterUtility;

import twitter4j.IDs;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	try{
    		TwitterUser user1 = TwitterUtility.getUserList().get(0);
    		Twitter twitter = new TwitterFactory().getInstance();
    		twitter.setOAuthConsumer(user1.getConsumerKey(), user1.getConsumerSecret());
    		AccessToken oathAccessToken = new AccessToken(user1.getAccessToken(),user1.getAccessTokenSecret());
    		twitter.setOAuthAccessToken(oathAccessToken);
    		String userName = "bhak_kala";
    		ResponseList<Status> statusList= twitter.getUserTimeline(userName);
    		for(int i=0;i<statusList.size();i++){
    			Status status = statusList.get(i);
    			if(!status.isFavorited()){
    				twitter.createFavorite(status.getId());
        		}
    			
    		}
    		
        return   "liked "+statusList.size()+" tweets of"+user1.getName();
    	}catch(Exception e){
    		return "Some Exception Occured";
    	}
    }
    
    
    @RequestMapping("/followers")
    public String followers(@RequestParam(value="userName", defaultValue="World") String userName) {
    	StringBuffer returnString = new StringBuffer();
    	long defaultProfileImageCount = 0;
    	long followerCount = 0;
    	try{
    		TwitterUser user1 = TwitterUtility.getUserList().get(0);
    		Twitter twitter = new TwitterFactory().getInstance();
    		twitter.setOAuthConsumer(user1.getConsumerKey(), user1.getConsumerSecret());
    		AccessToken oathAccessToken = new AccessToken(user1.getAccessToken(),user1.getAccessTokenSecret());
    		twitter.setOAuthAccessToken(oathAccessToken);
    		
    		
    		User u1 = null ;
    	      long cursor = -1;
    	      IDs ids;
    	      returnString.append("User List\n");
    	      do {
    	              ids = twitter.getFollowersIDs(userName, cursor);
    	          for (long id : ids.getIDs()) {
    	        	  try{
	    	              User user = twitter.showUser(id);
	    	              String profileUrl = user.getProfileImageURL();
	    	              if(profileUrl.contains("default_profile")){
	    	            	  defaultProfileImageCount++; 
	    	              }
	    	              followerCount++;
    	        	  }catch(Exception e){
    	        		  
    	        	  }
    	          }
    	      } while ((cursor = ids.getNextCursor()) != 0);
    		
    		
    	}catch(Exception e){
    		return "Some Exception Occured";
    	}
    	
    	//return "request "+userName;
    	return "Total followers having default Profiles"+defaultProfileImageCount+ "  out of total "+followerCount +" users";
    }
    
    
}