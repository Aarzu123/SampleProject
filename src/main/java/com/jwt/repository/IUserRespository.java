package com.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.jwt.model.User;

@Repository
public interface IUserRespository extends JpaRepository<User, Integer> {

	
	@Query("Select u from User u where u.userName=?1 and u.password=?2")
	public User validateUser(String username,String password);
	
	@Query("Select u from User u where u.userName=?1")
	public User fidByUName(String username);

	
//	For Mongo Syntax
	
//	@Query("{'username':{$in : [?0]},'password':{$in : [?1]}} ")
//	public User validateUserMongo(String username,String password);
//
//	@Query("{'username':?0}")
//	public User fidByUNameMongo(String username);

}
