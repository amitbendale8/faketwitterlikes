package com.demo.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.demo.entity.TwitterUser;

public class CSVHelper {
	
	/**
	 * Read user data from CSV
	 * @return
	 */
	public static List<TwitterUser> getUsers(){
		List<TwitterUser> userList = new ArrayList<TwitterUser>();
		 BufferedReader br = null;
        try {
        	ClassPathResource secretJson = new ClassPathResource("user.csv");
            InputStream in =
            		secretJson.getInputStream(); 
           
            String line = "";
            String cvsSplitBy = ",";
            br = new BufferedReader(new InputStreamReader(in));
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] info = line.split(cvsSplitBy);
                TwitterUser user = new TwitterUser(info[0],info[1],info[2],info[3],info[4]);
	        	 userList.add(user);
               

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Exception while getting users: "+e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return userList;

    }

	
}
