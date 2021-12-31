package com.etiya.ReCapProject.business.abstracts;

import com.etiya.ReCapProject.business.requests.UserRequests.LoginUserRequest;
import com.etiya.ReCapProject.core.utilities.results.Result;

public interface UserService {
	Result login(LoginUserRequest loginUserRequest);
	Result isUserExists(int userId);
	Boolean isUserEmailExists(String email);

}
