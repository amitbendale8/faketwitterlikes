package com.demo.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.entity.FbUser;


import twitter4j.Twitter;

public class FbUtility {
	
private static Map<String, Twitter> userMap = new HashMap<String, Twitter>(); 
	
	private static List<FbUser> userList = new ArrayList<FbUser>();
	
	private static Map<String,String> accesTokenMap = new HashMap<String,String>();
	
	
	public static void post(String message) {/*
		userList = CSVHelper.getFbUsers();
		FbUser user1 = userList.get(0);
		Facebook facebook = new FacebookFactory().getInstance();
		String accessToken = getAccessToken(user1);
		facebook.setOAuthAppId(user1.getAppId(), user1.getAppSecret());
		facebook.setOAuthPermissions("publish_actions");
		facebook.setOAuthAccessToken(new AccessToken("EAACEdEose0cBACcyZCMI7eZAb3hay2l3MDCgD6UVc0YkD9F2NpuEPHDZAHZCYttGZBgG33o7rlycbSxgIcSalOZCL6GxgM7IVwZAws881lZCeNZAfdCyL7HtDaHDpFhksEctA15gJ7BOOcLzR1ryLqxXb7ClDsPCNs5vbNBMQM66ZAaZBttgjAMyaYUiIRuA8jo8XEZD", null));
	
		try{
			facebook.postStatusMessage(message);
		}catch(Exception e){
			System.out.println("Error in posting: "+e.getMessage());
		}
		
		int k = 3;
		k++;
	*/}
	
	
	private static String getAccessToken(FbUser user) {
		String accessToken = "";
		String url = "https://graph.facebook.com/oauth/access_token?"
            +"client_id="+user.getAppId()
           +"&client_secret="+user.getAppSecret()
           +"&grant_type=client_credentials";
		StringBuffer response = null;
		try{
			URL obj = new URL(url);
		
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			// optional default is GET
			con.setRequestMethod("GET");
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			String respStr = response.toString();
			accessToken = respStr.split("=")[1];
			in.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return accessToken;

	}


}
