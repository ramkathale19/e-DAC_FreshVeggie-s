
package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.User;
import com.app.service.IAdminService;
import com.app.service.IUserService;


@Controller
@RequestMapping("/user")

public class UserController {
	
	@Autowired 
	private IUserService userService;
	
	@Autowired
	private IAdminService adminService;
	
    public UserController() {
		System.out.println("in the ctor of "+getClass().getName());
	}
	
	@GetMapping("/register")
	public String showRegisterUserForm(Model map)
	{
		map.addAttribute("user_details", new User());
		return "/user/register";
	}
	
	@PostMapping("/register")
	public String registerUserForm(@Valid User user,BindingResult result,RedirectAttributes flashMap, HttpSession session)
	{
		session.setAttribute("user_details", user);
		flashMap.addFlashAttribute("message", userService.registerUser(user));
		return "redirect:/user/login";
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email,@RequestParam String password,Model map,HttpSession session) 
	{
	    System.out.println("in process login form "+email+" "+password);
	    try {
	    	User validationUser=userService.authenticateUser(email,password);
	    	session.setAttribute("message", validationUser + "login successfull...");

			 }catch (RuntimeException e) {
			map.addAttribute("message", "Invalid Login, Please Retry");
			}
	    return "redirect:/user/details";
     }
	
	 @GetMapping("/list")
	  public String listUsers(Model map) {
	  System.out.println("in list users...");
	  map.addAttribute("user_list",userService.findAllUsers());
	  return"/admin/list"; 
	  }
	  
	
	  @GetMapping("/delete")
	public String deleteUserDetails(HttpSession hs, @RequestParam int uid, RedirectAttributes flashMap) {
		Integer id = (Integer) hs.getAttribute("uid");
		System.out.println("in delete user details " + uid);
		 userService.deleteUser(uid);
		flashMap.addFlashAttribute("message");
		return "redirect:/admin/list";
	}

	
	
			 
}

