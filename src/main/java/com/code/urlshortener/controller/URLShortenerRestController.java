package com.code.urlshortener.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.code.urlshortener.entity.ShortenUrl;

/**
 * @author Aqeel
 *
 */

@RestController
public class URLShortenerRestController {
	
	private Map<String, ShortenUrl> shortenUrlList = new HashMap<>();
	
	@RequestMapping(value="/shortenurl", method=RequestMethod.POST)
	public ResponseEntity<Object> getShortenUrl(@RequestBody ShortenUrl shortenUrl) throws MalformedURLException {
		String randomChar = getRandomChars();
		
		setShortUrl(randomChar, shortenUrl);
		
		return new ResponseEntity<Object>(shortenUrl, HttpStatus.OK);
	}
	
	@RequestMapping(value="/s/{randomstring}", method=RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
		response.sendRedirect(shortenUrlList.get(randomString).getFullUrl());
	}
	
	
	
	private void setShortUrl(String randomChar, ShortenUrl shortenUrl) throws MalformedURLException {
		shortenUrl.setShortUrl("http://localhost:8080/s/" + randomChar);
		shortenUrlList.put(randomChar, shortenUrl);
	}
	
	private String getRandomChars() {
		String randomString = "";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for(int i=0; i<5; i++) {
			randomString += possibleChars.charAt( (int)  Math.floor( Math.random() * possibleChars.length() ));
		}
		return randomString;
	}
}