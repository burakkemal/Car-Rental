package com.etiya.ReCapProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.ReCapProject.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User getByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsUserByEmail(String email);
}
