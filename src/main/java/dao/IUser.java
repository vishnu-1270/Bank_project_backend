package dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.User;

public interface IUser extends JpaRepository<User, Integer> {
	
	User findByAnumber(long anumber);
	
	@Query(value="select * from user where username=?", nativeQuery=true)
	public User findByUsername(String username);
	
	@Query(value="select * from user where password=?", nativeQuery=true)
	public User findByPassword(String password);
	

}
