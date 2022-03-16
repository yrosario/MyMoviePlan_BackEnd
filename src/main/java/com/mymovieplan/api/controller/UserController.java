package com.mymovieplan.api.controller;



import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.mymovieplan.api.model.User;
import com.mymovieplan.api.model.Role;
import com.mymovieplan.api.service.RoleService;
import com.mymovieplan.api.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final String AUTHORIZATION = "Authorization";

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getUserList(){
		
		List<User> users = userService.findAll();
		if(users.isEmpty())
			return new ResponseEntity<>("No User Found", HttpStatus.OK);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
		
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getUser(@PathVariable("username") String username){
		
		User user = userService.findUserByUserName(username);
		if(user == null)
			return new ResponseEntity<>("No User Found", HttpStatus.NO_CONTENT);
		
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody User user){
		if(userService.findUserByUserName(user.getEmail()) != null)
				return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
		
		for(Role role: user.getRoles())
		{
			role.setUserRole(user);
			roleService.save(role);
		}
		
		user.getRoles().stream().forEach(role -> role.setUserRole(user));
		 
		userService.save(user);
		return new ResponseEntity<>("User Has been Created", HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		User retriveUser = userService.findUserById(user.getId());
		if(retriveUser == null)
			return new ResponseEntity<>("No User Found", HttpStatus.NOT_FOUND);
		
		if(!user.getRoles().isEmpty())
			
		
		userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PostMapping("change_password")
	public ResponseEntity<String> changePassword(@RequestBody HashMap<String, String> request){
		
		String username = request.get("username");
		User user = userService.findUserByUserName(username);
		
		if(user == null)
			return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
		
		String currentPassword = request.get("currentPassword");
		String newPassword = request.get("newPassword");
		String confirmPassword = request.get("confirmPassword");
		
		if(!newPassword.equals(confirmPassword))
			return new ResponseEntity<>("Password does not match", HttpStatus.BAD_REQUEST);
		
		String userPassword = user.getPassword();
		try {
			if (newPassword != null && !newPassword.isEmpty()) {
				if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
					userService.updateUserPassword(user, newPassword);
				}else {
					return new ResponseEntity<>("Incorrect Current Password", HttpStatus.BAD_REQUEST);
				}
			} 
			return new ResponseEntity<>(userPassword, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error Occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestBody HashMap<String, String> request){
		User user = userService.findUserByUserName(request.get("username"));
		if(user == null)
			return new ResponseEntity<>("User " + request.get("username") + "was not found", HttpStatus.BAD_REQUEST);
		
		userService.deleteUser(request.get("username"));
		
		return new ResponseEntity<>("User " + request.get("username") + "has been succesfully deleted", HttpStatus.OK );
	}
	
	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refreshToken = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refreshToken);
				
				
				String username = decodedJWT.getSubject();
				User user = userService.findUserByUserName(username);
				
				String accessToken = JWT.create().withSubject(user.getUsername())
			             .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 100))
			             .withIssuer(request.getRequestURL().toString())
			             .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
			             .sign(algorithm);
	
			   response.setHeader("access_token", accessToken);
	           response.setHeader("refresh_token", refreshToken);
	           
	          


				
			}catch(Exception e) {
				response.setHeader("error", e.getMessage());
				response.sendError(HttpStatus.FORBIDDEN.value());
			}
		}else {
			throw new RuntimeException("Request token is missing");
			}

			
		}
		
	}


