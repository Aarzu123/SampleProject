package com.jwt.controller;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.exception.UserNotFoundException;
import com.jwt.model.User;
import com.jwt.services.IUserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;

@RestController
@RequestMapping("userauth/v1")
public class UserAuthController {

	@Autowired
	private IUserService userService;

	private Map<String, String> map = new HashMap<>();

	@GetMapping("/")
	public String serverStarted() {
		return "Authentication Server Started";
	}

	@PostMapping("/login")
	public ResponseEntity<?> doLogin(@RequestBody User user) {

		try {
			String jwtToken = generateToken(user.getUserName(), user.getPassword());
			map.put("message", "User Successfully LoggedIn");
			map.put("token", jwtToken);

		} catch (Exception e) {
			map.put("message", e.getMessage());
			map.put("token", null);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	private String generateToken(String username, String password) throws ServletException, UserNotFoundException {

		String jwtToken = "";

		if (username == null || password == null) {
			throw new ServletException("Please send valid username and password");
		}

		
		boolean flag = userService.validateUserService(username, password);
		if (!flag)
			throw new ServletException("Invalid Credentials");
		else {

			jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 3000000))
					.signWith(SignatureAlgorithm.HS256, "secret key").compact();
		}
		return jwtToken;
	}
}
