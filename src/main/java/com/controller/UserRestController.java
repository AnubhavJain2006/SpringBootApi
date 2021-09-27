package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.UserEntity;
import com.dao.UserRepository;

@RestController
public class UserRestController {

	@Autowired
	UserRepository userRepo;

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody UserEntity user) {
		if (user != null) {
			try {
				user = userRepo.save(user);
				return new ResponseEntity<UserEntity>(user, HttpStatus.OK);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<String>("{\"Message\":\"Internal Server Error\"}",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("{\"Message\":\"Empty user\"}", HttpStatus.OK);
		}
	}

	@GetMapping("/allUser")
	public ResponseEntity<?> getAllRecords() {
		try {
			List<UserEntity> users = userRepo.findAll();
			return new ResponseEntity<List<UserEntity>>(users, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("{\"Message\":\"Internal Server Error\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteById/{userId}")
	public ResponseEntity<?> deleteById(@PathVariable("userId") int userId) {
		try {
			UserEntity user = userRepo.findById(Integer.valueOf(userId)).get();
			System.out.println(user);
			userRepo.deleteById(Integer.valueOf(userId));
			return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("{\"Message\":\"Internal Server Error\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getUserById/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) {
		try {
			UserEntity user = userRepo.findById(Integer.valueOf(userId)).get();
			if (user != null) {
				return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("{\"Message\":\"User Not Found\"}", HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("{\"Message\":\"Internal Server Error\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
		if (user != null) {
			try {
				user = userRepo.save(user);
				return new ResponseEntity<UserEntity>(user, HttpStatus.OK);

			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ResponseEntity<String>("{\"Message\":\"Internal Server Error\"}",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<String>("{\"Message\":\"Empty user\"}", HttpStatus.OK);
		}
	}
}
