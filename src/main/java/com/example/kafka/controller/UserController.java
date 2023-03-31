package com.example.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.model.UserDetails;

@RestController
@RequestMapping("user/kafka")
public class UserController {
	
	
	private static final String TOPIC="Kafka_Example_json";
	@Autowired
	private KafkaTemplate<String, UserDetails> template;
	
	//@Autowired
	//private KafkaTemplate<String, String> temp;
	
	@GetMapping("/message/{data}")
	public String post(@PathVariable("data") String msg ) {
	//	temp.send(TOPIC, msg);
		 return "published successfully";	
		
	}
	
	@PostMapping("/insert")
	public String insertRecordToTopic(@RequestBody UserDetails user) {		
	    template.send(TOPIC, user);
		//temp.send(TOPIC, user.toString());
		return "published successfully";	
	}

}
