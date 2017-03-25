package com.demo.linkedind;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.utility.LinkedInUtility;



@RestController
public class LinkedInController {
	@RequestMapping("/linkedIn/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	try{
    		String returnStr = null;
    		returnStr = "Hello linkedIn";
    		return returnStr;
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }
	
	
	@RequestMapping("/linkedIn/test")
    public String test(@RequestParam(value="name", defaultValue="World") String name) {
    	try{
    		LinkedInUtility.testLinkedIn();
    	}catch(Exception e){
    		return e.getMessage();
    	}
    	return "Testing Done";
    }
}
