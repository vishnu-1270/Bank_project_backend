package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Admin;

public interface IAdmin extends JpaRepository<Admin, String> {
	
	@Query(value="select * from admin where username=?", nativeQuery=true)
	public Admin findByUsername(String username);
	
	@Query(value="select * from admin where password=?", nativeQuery=true)
	public Admin findByPassword(String password);

}
