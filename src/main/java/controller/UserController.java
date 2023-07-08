package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dao.IUser;
import model.User;
import service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
	
	@Autowired
	IUser iuser;
	
	@Autowired
	UserService userService;
	
	User newUser = null;
	
//	@PostMapping("userLogin")
//	public String userLogin(@RequestBody User u) {
//		if(iuser.findByUsername(u.getUsername())!=null) {
//			if(iuser.findByPassword(u.getPassword())!=null) {
//				return "true";
//			}else return "false";
//		}else return "false";
//	}

	@GetMapping("viewDetails/{uid}")
	public Optional<User> viewDetails (@PathVariable("uid") int uid) {
		Optional<User> u=iuser.findById(uid);
		return u;
	}
	
	@PutMapping("updatePassword/{uid}")
	public String updatepassword(@RequestBody User user, @PathVariable("uid") int uid) {
		iuser.findById(uid).map(update -> {
			update.setPassword(user.getPassword());
			return iuser.save(update);
		});
		return "password Updated Successfully";
	}
	
	@PutMapping("deposit/{amount}/{password}")
	public String deposit( @PathVariable("amount") long amount, @PathVariable("password") String password) {
		newUser = iuser.findByPassword(password);
//		newUser = iuser.findById(uid).get();
		return userService.deposit(amount, newUser);
	}
	
	@PutMapping("withdraw/{amount}/{password}")
	public String withdraw( @PathVariable("amount") long amount, @PathVariable("password") String password) {
		newUser = iuser.findByPassword(password);
		return userService.withdraw(amount, newUser);
	}
	
	@PutMapping("transfer/{anumber}/{amount}/{password}")
	public String transfermoney(@PathVariable("anumber") long anumber, @PathVariable("amount") long amount, @PathVariable("password") String password) {
		newUser = iuser.findByPassword(password);
		return userService.transferMoney(anumber, amount, newUser);
	}
	
	@GetMapping("userLogin/{username}/{password}")
	public String adminLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
		if(iuser.findByUsername(username)!=null) {
			if(iuser.findByPassword(password)!=null) {
				iuser.save(iuser.findByPassword(password));
				return "true";
				
			}else return "false";
		}else return "false";
	}
}
