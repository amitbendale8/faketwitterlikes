package com.demo.fb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class FBController {
	@RequestMapping("/fb/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	try{
    		String returnStr = null;
    		returnStr = "Hello World";
    		return returnStr;
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }
}
