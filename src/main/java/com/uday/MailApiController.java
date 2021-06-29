package com.uday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uday.service.MailApiService;

@RestController
public class MailApiController {
	
	@Autowired
	private MailApiService mailApiService;
	
	@GetMapping("/sendMail")
	public String sendMessage() {
		//you can give your yourmailid,yourpassword,recepientmailid
		String str=mailApiService.sendMail("yourmailid@gmail.com", "password", "recepientmailid@gmail.com");
		
		return str;
	}
	
	
	@GetMapping("/getMsg")
	public String getMessage() {
		String str=mailApiService.getMessage();
		
		return str;
	}

}
