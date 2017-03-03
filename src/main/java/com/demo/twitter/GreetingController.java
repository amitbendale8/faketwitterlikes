package com.demo.twitter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.TwitterUser;
import com.demo.utility.RandomnessUtility;
import com.demo.utility.TwitterUtility;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	try{
    		TwitterUser user1 = TwitterUtility.getUser();
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
    
    
    
    
    @RequestMapping("/likemytweet")
    public String likeMyTweet(@RequestParam(value="userName", defaultValue="World") String userName) {
      	List<TwitterUser> userList = TwitterUtility.getUserList();
      	Map<String,Twitter> userMap = TwitterUtility.getUserMap();
    	int noOfLikes = RandomnessUtility.getRandomNum(18, userList.size());
    	int[] randomUsrArray = RandomnessUtility.getRandomNumberArray(noOfLikes, 0, userList.size());
    	
    	long likeCount = 0;
    	
    	for(int i=0;i<randomUsrArray.length;i++){
    	
    		TwitterUser user = TwitterUtility.getUserList().get(randomUsrArray[i]);
    		try{
    			
        		Twitter twitter = userMap.get(user.getName());
        		
        		ResponseList<Status> statusList= twitter.getUserTimeline(userName);
        		Status status = statusList.get(0);
        		
        		if(!status.isFavorited()){
    				twitter.createFavorite(status.getId());
    				likeCount++;
        		}
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        	}
    	}
    	
    	return "Total Likes : "+likeCount;   
    }
    
    /**
     * 
     * @param userName
     * @return
     */
    @RequestMapping("/retweet")
    public String retweet(@RequestParam(value="userName", defaultValue="World") String userName) {
    	
      	List<TwitterUser> userList = TwitterUtility.getUserList();
      	Map<String,Twitter> userMap = TwitterUtility.getUserMap();
    	int noOfRetweets = RandomnessUtility.getRandomNum(18, userList.size());
    	int[] randomUsrArray = RandomnessUtility.getRandomNumberArray(noOfRetweets, 0, userList.size());
    	
    	long retweetCount = 0;
    	
    	for(int i=0;i<randomUsrArray.length;i++){
    	
    		TwitterUser user = TwitterUtility.getUserList().get(randomUsrArray[i]);
    		try{
    			
        		Twitter twitter = userMap.get(user.getName());
        		
        		ResponseList<Status> statusList= twitter.getUserTimeline(userName);
        		Status status = statusList.get(0);
        		
        		if(!status.isRetweeted()){
    				twitter.retweetStatus(status.getId());
    				retweetCount++;
        		}
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        	}
    	}
    	
    	return "Retweets : "+retweetCount;   
    }
    
    
    /**
     * 
     * @param userName
     * @return
     */
    @RequestMapping("/followme")
    public String followme(@RequestParam(value="userName", defaultValue="World") String userName) {
    	
    	
    	long followCount = 0;
    	List<TwitterUser> userList = TwitterUtility.getUserList();
    	for(int i=0;i<userList.size();i++){
    	
    		TwitterUser user = TwitterUtility.getUserList().get(i);
    		try{
    			
        		Twitter twitter = new TwitterFactory().getInstance();
        		twitter.setOAuthConsumer(user.getConsumerKey(), user.getConsumerSecret());
        		AccessToken oathAccessToken = new AccessToken(user.getAccessToken(),user.getAccessTokenSecret());
        		twitter.setOAuthAccessToken(oathAccessToken);
        		
        		twitter.createFriendship(userName);
        		followCount++;
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        		System.out.println("Message: "+e.getMessage());
        	}
    	}
    	
    	return "Total New Follower : "+followCount;   
    }
    
    /**
     * Likes a tweet given by id.
     * @param userName
     * @return
     */
    @RequestMapping("/likeBytweetId")
    public String likeBytweetId(@RequestParam(value="tweetID", defaultValue="World") String tweetID) {
    	
    	List<TwitterUser> userList = TwitterUtility.getUserList();
      	Map<String,Twitter> userMap = TwitterUtility.getUserMap();
    	int noOfLikes = RandomnessUtility.getRandomNum(18, userList.size());
    	int[] randomUsrArray = RandomnessUtility.getRandomNumberArray(noOfLikes, 0, userList.size());
    	
    	long likeCount = 0;
    	
    	for(int i=0;i<randomUsrArray.length;i++){
    	
    		TwitterUser user = TwitterUtility.getUserList().get(randomUsrArray[i]);
    		try{
        		Twitter twitter = userMap.get(user.getName());
        		Status status = twitter.showStatus(Long.parseLong(tweetID));
        		if(!status.isFavorited()){
    				twitter.createFavorite(status.getId());
    				likeCount++;
        		}
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        	}
    	}
    	
    	return "Total Likes : "+likeCount;
    	
    }
    
    /**
     * Retweet a tweet given by id.
     * @param userName
     * @return
     */
    @RequestMapping("/retweetBytweetId")
    public String retweetBytweetId(@RequestParam(value="tweetID", defaultValue="World") String tweetID) {
    	
    	List<TwitterUser> userList = TwitterUtility.getUserList();
      	Map<String,Twitter> userMap = TwitterUtility.getUserMap();
    	int noOfLikes = RandomnessUtility.getRandomNum(18, userList.size());
    	int[] randomUsrArray = RandomnessUtility.getRandomNumberArray(noOfLikes, 0, userList.size());
    	
    	long retweetCount = 0;
    	
    	for(int i=0;i<randomUsrArray.length;i++){
    	
    		TwitterUser user = TwitterUtility.getUserList().get(randomUsrArray[i]);
    		try{
        		Twitter twitter = userMap.get(user.getName());
        		Status status = twitter.showStatus(Long.parseLong(tweetID));
        		if(!status.isRetweeted()){
    				twitter.retweetStatus(status.getId());
    				retweetCount++;
        		}
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        	}
    	}
    	
    	return "Total Likes : "+retweetCount;
    	
    }
    
    /**
     * Display Users
     * @param userName
     * @return
     */
    @RequestMapping("/displayUsers")
    public String displayUsers() {
    	
    	TwitterUtility.getUserList();
    	return "wait";   
    }
    
}