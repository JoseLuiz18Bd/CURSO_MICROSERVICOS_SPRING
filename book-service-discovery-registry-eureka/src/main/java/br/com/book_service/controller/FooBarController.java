package br.com.book_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("book-service")
public class FooBarController {
	
	private Logger logger = LoggerFactory.getLogger(FooBarController.class);

//	@GetMapping("/foo-bar")
//	@Retry( name = "foo-bar", fallbackMethod = "fallbackMethod" )
//	public String fooBar() {
//		
//		logger.info("Request to foo-bar is received!");
//		
//		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
////		return "Foo-Bar!!!";
//		return response.getBody();
//		
//	}
//	
//	public String fallbackMethod(Exception ex) {
//		 return "fallbackMethod foo-bar!!!";
//	}
	
//	@GetMapping("/foo-bar")
//	@CircuitBreaker( name = "default", fallbackMethod = "fallbackMethod" ) //while (1) {curl http://localhost:8765/book-service/foo-bar; sleep 0.1}
//	public String fooBar() {
//		
//		logger.info("Request to foo-bar is received!");
//		
//		var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar", String.class);
////		return "Foo-Bar!!!";
//		return response.getBody();
//		
//	}
//	
//	public String fallbackMethod(Exception ex) {
//		 return "fallbackMethod foo-bar!!!";
//	}
	
//	@GetMapping("/foo-bar")
//	@RateLimiter( name = "default" ) //while (1) {curl http://localhost:8765/book-service/foo-bar; sleep 0.1}
//	public String fooBar() {
//		
//		logger.info("Request to foo-bar is received!");
//			
//		return "Foo-Bar!!!";
//		
//	}
//	
//	public String fallbackMethod(Exception ex) {
//		 return "fallbackMethod foo-bar!!!";
//	}
	
	@GetMapping("/foo-bar")
	@Bulkhead( name = "default" ) //while (1) {curl http://localhost:8765/book-service/foo-bar; sleep 0.1}
	public String fooBar() {
		
		logger.info("Request to foo-bar is received!");
			
		return "Foo-Bar!!!";
		
	}
	
	public String fallbackMethod(Exception ex) {
		 return "fallbackMethod foo-bar!!!";
	}
}
