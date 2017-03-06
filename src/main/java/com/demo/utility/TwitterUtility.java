package com.demo.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.google.GoogleAPIHelper;
import com.demo.entity.TwitterUser;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUtility {
	
	private static Map<String, Twitter> userMap = new HashMap<String, Twitter>(); 
	
	private static List<TwitterUser> userList = new ArrayList<TwitterUser>();
	
	public static Map<String, Twitter> getUserMap() {
		return userMap;
	}

	public static void setUserMap(Map<String, Twitter> userMap) {
		TwitterUtility.userMap = userMap;
	}


	static{
		//Manipulate Userlist
    	try{
	    	Sheets service = GoogleAPIHelper.getSheetsService();
	    	String spreadsheetId = "1o8OhWQI1gRNHOLqNGzmQnZxw_B-7wxxVrvWt_a0EtC4";
	    	String range = "set1!A2:I";
	        ValueRange response = service.spreadsheets().values()
	            .get(spreadsheetId, range)
	            .execute();
	        List<List<Object>> values = response.getValues();
	        if (values == null || values.size() == 0) {
	        	System.out.println("No Data Found o");
	        } else {
	        	System.out.println("User list found. Total Rows: "+values.size());
	          for (List row : values) {
	            // Print columns A and E, which correspond to indices 0 and 4.
	        	 TwitterUser user = new TwitterUser(row.get(5).toString(),row.get(6).toString(),row.get(7).toString(),row.get(8).toString(),row.get(1).toString());
	        	 userList.add(user);
	          }
	        }
    	}catch(Exception e){
    		System.out.println("Error while getting users: "+e.getMessage());
    	}
    	
    	//Add users to usermap
    	
    	for(int i=0;i<userList.size();i++){
        	
    		TwitterUser user = TwitterUtility.getUserList().get(i);
    		try{
    			
        		Twitter twitter = new TwitterFactory().getInstance();
        		twitter.setOAuthConsumer(user.getConsumerKey(), user.getConsumerSecret());
        		AccessToken oathAccessToken = new AccessToken(user.getAccessToken(),user.getAccessTokenSecret());
        		twitter.setOAuthAccessToken(oathAccessToken);
        		userMap.put(user.getName(), twitter);
        		
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        		System.out.println("Message: "+e.getMessage());
        	}
    	}
	}
	
	public static AccessToken getAccessToken() throws Exception{
	    // The factory instance is re-useable and thread safe.
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("fWtcemk8nfS4HrYsz6GzVHgxX")
		.setOAuthConsumerSecret("S7t6pi9ojW1c5JwuW1NEXdk1xnyu42usFyHTaLwOvX1ovavVsR")
		.setOAuthAccessToken(null)
		.setOAuthAccessTokenSecret(null);
    	TwitterFactory factory = new TwitterFactory(cb.build());
     
        Twitter twitter = factory.getInstance();
	   
	    RequestToken requestToken = twitter.getOAuthRequestToken();
	  
	    String verifier = requestToken.getParameter("oauth_verifier");
	    AccessToken accessToken = null;
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      String pin = br.readLine();
	      try{
	         if(pin.length() > 0){
	           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	         
	         }else{
	           accessToken = twitter.getOAuthAccessToken(requestToken);
	         }
	      } catch (TwitterException te) {
	        if(401 == te.getStatusCode()){
	          System.out.println("Unable to get the access token.");
	        }else{
	          te.printStackTrace();
	        }
	      }
	    }
	    return accessToken;
	}
	
	/**
	 * Returns the master user
	 * @return
	 */
	public static TwitterUser getUser(){
		
		TwitterUser user = null;
		
		//User1
		user = new TwitterUser("weZUZReJhUyl2PNnwGg0RbvXG","O1sQ6RluyD1q6rnbsfwGh3koJuEAGKxiImU4JhPRFRvhQiSUqE","832195442985279488-gL8grkxyOITWgcbg1K5Iwwy3yoPZ2Pw","zx2JKwH2yUJCWQPF7mOC38G0ttC9LBbXZB4kd2qiazi6M","Rahul Raj");
		
		return user;
		
	}
	
	
	/**
	 * Returns the master user list
	 * @return
	 */
	public static List<TwitterUser> getUserList(){
		
		return userList;
		
	}
	
	/**
	 * Likes tweet by tweet Id by all usr
	 * @param tweetID
	 * @return
	 */
	public static int likeTweet(long  tweetID){
		
      	List<TwitterUser> userList = TwitterUtility.getUserList();
      	Map<String,Twitter> userMap = TwitterUtility.getUserMap();
    	int noOfLikes = RandomnessUtility.getRandomNum(18, userList.size());
    	int[] randomUsrArray = RandomnessUtility.getRandomNumberArray(noOfLikes, 0, userList.size());
    	
    	int likeCount = 0;
    	
    	for(int i=0;i<randomUsrArray.length;i++){
    	
    		TwitterUser user = TwitterUtility.getUserList().get(randomUsrArray[i]);
    		try{
    			
        		Twitter twitter = userMap.get(user.getName());
        		
        		Status status = twitter.showStatus(tweetID);
        		if(!status.isFavorited()){
    				twitter.createFavorite(status.getId());
    				likeCount++;
        		}
        		
        		
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        	}
    	}
    	
    	return likeCount;   
	}
	
	/**
	 * Retweet by tweet Id
	 * @param tweetID
	 * @return
	 */
	public static int retweet(long  tweetID){
		
		List<TwitterUser> userList = TwitterUtility.getUserList();
      	Map<String,Twitter> userMap = TwitterUtility.getUserMap();
    	int noOfLikes = RandomnessUtility.getRandomNum(18, userList.size());
    	int[] randomUsrArray = RandomnessUtility.getRandomNumberArray(noOfLikes, 0, userList.size());
    	int retweetCount = 0;
    	for(int i=0;i<randomUsrArray.length;i++){
    	
    		TwitterUser user = TwitterUtility.getUserList().get(randomUsrArray[i]);
    		try{
        		Twitter twitter = userMap.get(user.getName());
        		Status status = twitter.showStatus(tweetID);
        		if(!status.isRetweeted()){
    				twitter.retweetStatus(status.getId());
    				retweetCount++;
        		}
        	}catch(Exception e){
        		System.out.println("Exception occured for user "+user.getName());
        	}
    	}
    	
    	return retweetCount;
	}
}
