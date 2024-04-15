package com.example.microservices.demo.usermanagement.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.microservices.demo.usermanagement.Entity.UserEntity;
import com.example.microservices.demo.usermanagement.Entity.Userupdatepayload;
import com.example.microservices.demo.usermanagement.Exception.BadRequestException;
import com.example.microservices.demo.usermanagement.Exception.DataNotFoundException;
import com.example.microservices.demo.usermanagement.Exception.UnautherizedException;
import com.example.microservices.demo.usermanagement.customannitation.Authentication;
import com.example.microservices.demo.usermanagement.service.Userservices;

import io.jsonwebtoken.lang.Arrays;


@RestController
@CrossOrigin
public class Usercontroller {
		
	@Autowired
	private Userservices service;
	
		@GetMapping("/user/getAll/{status}")
		public ResponseEntity findAllUsers(@PathVariable Boolean status) throws Exception {
					List<UserEntity> response=service.findAllUser(status);
					if(response.isEmpty()) {
						throw new DataNotFoundException("User Data not found");
					}
					else {
						//return response;
						return new ResponseEntity(response , HttpStatus.OK);
					}
			}	
		
		@GetMapping("/user/getById/{userId}")
		public ResponseEntity findByUserID(@PathVariable String userId) {
			UserEntity responsId= service.findUserSingleID(userId);
			if(responsId==null){
				throw new DataNotFoundException("users Data not found please give another id");
			}
			else {
				return new ResponseEntity(responsId , HttpStatus.OK);
				
			}
		}
		
		@PutMapping("/disable/deleteallusers/{status}")
		public ResponseEntity<String> updateUsersData1(@RequestBody Userupdatepayload userdetails,@PathVariable Boolean status) throws Exception {
			String response=service.enabledisableUsers(userdetails.getIds(),status);
			if(response==null) {
				throw new BadRequestException("users data not updated");
			}
			else {
				return new ResponseEntity(response , HttpStatus.OK);
			} 
		}
		
		@PostMapping("/user/save")
		public ResponseEntity CreateUsersData(@RequestBody UserEntity companydetails) throws Exception {
			UserEntity response=service.createUserData(companydetails);
			if(response==null) {
				throw new BadRequestException("Users Details saving failed");
			}
			else {
				return new ResponseEntity(response , HttpStatus.CREATED);
			}
		}
		
		@PutMapping("/user/update/{userid}")
		public ResponseEntity<String> updateUsersData(@RequestBody UserEntity userdetails, @PathVariable String userid) throws Exception {
					UserEntity response=service.updateUerData(userdetails,userid);
					if(response==null) {
						throw new BadRequestException("users Data not updated");
					}
					else {
						return new ResponseEntity(response , HttpStatus.OK);
					}
				}
		 
	
		@DeleteMapping("/user/delete/{id}/{status}")
		public ResponseEntity deleteUserID(@PathVariable String id,@PathVariable Boolean status) throws Exception {
			UserEntity response=service.deleteUserSingleId(id,status); 
			if(response==null) {
				throw new DataNotFoundException("users Data not deleted");
			}
			else {
				return new ResponseEntity(response , HttpStatus.OK);
			}
		}
		
		@GetMapping("/user/search/{searchtext}")
		public ResponseEntity<List<UserEntity>> getUsers(@PathVariable String searchtext) throws Exception {
			List<UserEntity> usersDetails =  service.searchUser(searchtext);
			if(usersDetails != null) {
				return new ResponseEntity (usersDetails, HttpStatus.OK);
			}
			else {
				throw new DataNotFoundException("User data not found");
			}
		}

		
		
}

