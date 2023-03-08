package com.spring.elk.controller;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class ElkController {
	
	@SuppressWarnings("unused")
	private static final Logger Log= LogManager.getLogger(ElkController.class);



	@GetMapping("/check")
	public String main() {
		return  "OK";
	}
	
	@RequestMapping( value="/elkmsg",method = RequestMethod.GET)
	public String getMessage() {
		
		String response = "Welcome to Elk Testing with Log4J2 "+ new Date();
		
		Log.log(Level.INFO, response);
		
		return response;
		
		
		}
	
	@RequestMapping(value= "/exception")
	public String exception() {
		
		String response ="";
		
		
		try {
			throw new Exception("Exception for ELK testing");
			
		}catch(Exception ex) {
			ex.printStackTrace();
			Log.error(ex);
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String stackTrace =sw.toString();
			Log.error("Exception - " + stackTrace);
			response = stackTrace;
		}
		return response;
	}
	
}
