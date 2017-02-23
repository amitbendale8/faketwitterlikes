package com.demo.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.demo.twitter.TwitterUser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUtility {
	
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
	
	
	public static List<TwitterUser> getUserList(){
		
		List<TwitterUser> userList = new ArrayList<TwitterUser>();
		
		//User1
		userList.add(new TwitterUser("fWtcemk8nfS4HrYsz6GzVHgxX","S7t6pi9ojW1c5JwuW1NEXdk1xnyu42usFyHTaLwOvX1ovavVsR","1021626787-x8cxzFHoQE3JIQvw9s4J8qiG1LX7ZT00J1LiS6Q","6GOaFKjCFzltjBvL7KbtuTHLLr4aQy61BwVjx6N6sZfNr","Amit"));
		
		//User2
		userList.add(new TwitterUser("weZUZReJhUyl2PNnwGg0RbvXG","O1sQ6RluyD1q6rnbsfwGh3koJuEAGKxiImU4JhPRFRvhQiSUqE","832195442985279488-gL8grkxyOITWgcbg1K5Iwwy3yoPZ2Pw","zx2JKwH2yUJCWQPF7mOC38G0ttC9LBbXZB4kd2qiazi6M","Rahul Raj"));
		return userList;
		
	}
}
