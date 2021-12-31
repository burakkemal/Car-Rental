package com.etiya.ReCapProject.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.UserService;
import com.etiya.ReCapProject.business.requests.UserRequests.LoginUserRequest;
import com.etiya.ReCapProject.core.utilities.results.Result;

@RestController
@RequestMapping("api/users")
public class UsersController {
	private UserService userService;
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("login")
	public Result login(@RequestBody LoginUserRequest loginUserRequest){
		return this.userService.login(loginUserRequest);
	}
	
	
	
	
	
	
	
	
	
	
	
}
