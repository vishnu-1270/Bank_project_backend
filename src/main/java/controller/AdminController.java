package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.IAdmin;
import dao.IUser;
import model.Admin;
import model.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class AdminController {
	
	@Autowired
	IUser user;
	
	@Autowired
	IAdmin admin;
	
//	@PostMapping("adminLogin")
//	public String adminLogin(@RequestBody Admin a) {
//		if(admin.findByUsername(a.getUsername())!=null) {
//			if(admin.findByPassword(a.getPassword())!=null) {
//				return "true";
//			}else return "false";
//		}else return "false";
//	}
//	
	@GetMapping("viewAllUsers")
	public List<User> viewAllUsers(){
		return user.findAll();
	}
	
	@GetMapping("findUser/{uid}")
	public Optional<User> viewDetails (@PathVariable("uid") int uid) {
		Optional<User> u=user.findById(uid);
		return u;
	}
	
	@PostMapping("createUser")
	public String createUser(@RequestBody User u) {
		user.save(u);
		return "User Account Successfully Created";
	}
	
	@PutMapping("updateUser/{uid}")
	public String updateUser(@RequestBody User u, @PathVariable("uid") int uid) {
		user.findById(uid).map(update ->{
			update.setName(u.getName());
			update.setEmail(u.getEmail());
			update.setContact(u.getContact());
			update.setAddress(u.getAddress());
			
			return user.save(update);
		});
		return " User details updated Successfully";
	}
	
	@DeleteMapping("deleteUser/{uid}")
	public String deleteUser(@PathVariable("uid") int uid) {
		user.deleteById(uid);
		return "User deleted Successfully";
	}
	
	@GetMapping("adminLogin1/{username}/{password}")
	public String adminLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
		if(admin.findByUsername(username)!=null) {
			if(admin.findByPassword(password)!=null) {
				return "true";
			}else return "false";
		}else return "false";
	}
	

}
