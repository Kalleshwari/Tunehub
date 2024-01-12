package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Services.UsersService;
import com.example.demo.entities.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService service;
	//@PostMapping("/register")
	/*public String addUsers(@RequestParam("username") String username,
			@RequestParam("email")String email,
			@RequestParam("password")String password,
			@RequestParam("gender")String gender,
			@RequestParam("role")String role,
			@RequestParam("address")String address
			) 
			System.out.println(username+" "+email+" "+password+" "+gender+" "+role+" "+address);
		return "home";
	{*/
	@PostMapping("/register")
		public String addUsers(@ModelAttribute Users user) {
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus == false) {
			service.addUser(user);
			System.out.println("user Added");
		}else {
			System.out.println("user already exist");
		}
		//System.out.println(user.getUsername()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getGender()+" "+user.getRole()+" "+user.getAddress());
		//service.addUser(user);
		return "home";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,@RequestParam("password")String password
			,HttpSession session) {
		if(service.validateUser(email,password)==true) {
		String role=service.getRole(email);
		
		session.setAttribute("email", email);
	
		if(role.equals("admin")) {
			return "adminHome";
		}
		else {
			return "customerHome";
		}
	}
	else {
		return "login";
	}
	}
	/*@GetMapping("/pay")
	public String pay(@RequestParam String email) {
		
		boolean paymentStatus=false;//payment api
		
		if(paymentStatus == true){
			Users user= service.getUser(email);
			user.setPremium(true);
			service.updateUser(user);
		}
		return "login";
	}*/
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
