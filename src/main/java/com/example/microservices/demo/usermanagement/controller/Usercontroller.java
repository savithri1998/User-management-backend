package com.example.microservices.demo.usermanagement.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import com.example.microservices.demo.usermanagement.Entity.UserEntity;
import com.example.microservices.demo.usermanagement.Entity.Userupdatepayload;
import com.example.microservices.demo.usermanagement.Exception.BadRequestException;
import com.example.microservices.demo.usermanagement.Exception.DataNotFoundException;
import com.example.microservices.demo.usermanagement.service.Userservices;


@RestController
@CrossOrigin
public class Usercontroller {
		
	@Autowired
	private Userservices service;
	
		@GetMapping("/user/getAll/{status}")
		public ResponseEntity findAllUsersBasedOnStatus(@PathVariable Boolean status) throws Exception {
					List<UserEntity> response=service.findUsersBasedOnStatus(status);
					if(response.isEmpty()) {
						throw new DataNotFoundException("User data not found");
					}
					else {
						//return response;
						return new ResponseEntity(response , HttpStatus.OK);
					}
			}	
		
		@GetMapping("/user/getById/{userId}")
		public ResponseEntity findByUserID(@PathVariable String userId) {
			UserEntity responsId= service.getUserID(userId);
			if(responsId==null){
				throw new DataNotFoundException("Users data not found please give another id");
			}
			else {
				return new ResponseEntity(responsId , HttpStatus.OK);
				
			}
		}
		
		@PutMapping("/enabledisable/multiuser/{status}")
		public ResponseEntity<String> enableDisableMultiUserStatus(@RequestBody Userupdatepayload userdetails,@PathVariable Boolean status) throws Exception {
			String response=service.enabledisableUsers(userdetails.getIds(),status);
			if(response==null) {
				throw new BadRequestException("Unable to update user status");
			}
			else {
				return new ResponseEntity(response , HttpStatus.OK);
			} 
		}
		
		@PostMapping("/user/save")
		public ResponseEntity createUsersNewUser(@RequestBody UserEntity userdetails) throws Exception {
			UserEntity response=service.createUserDatails(userdetails);
			if(response==null) {
				throw new BadRequestException("Unable to create user");
			}
			else {
				return new ResponseEntity(response , HttpStatus.CREATED);
			}
		}
		
		@PutMapping("/user/update/{userid}")
		public ResponseEntity<String> updateUsersDatails(@RequestBody UserEntity userdetails, @PathVariable String userid) throws Exception {
					UserEntity response=service.updateUerData(userdetails,userid);
					if(response==null) {
						throw new BadRequestException("Unable to update user");
					}
					else {
						return new ResponseEntity(response , HttpStatus.OK);
					}
				}
		 
	
		@DeleteMapping("/enabledisable/singleuser/{id}/{status}")
		public ResponseEntity enableDisableSingleUser(@PathVariable String id,@PathVariable Boolean status) throws Exception {
			UserEntity response=service.enableAndDisableUserById(id,status); 
			if(response==null) {
				throw new DataNotFoundException("Unable to enable/disable user");
			}
			else {
				return new ResponseEntity(response , HttpStatus.OK);
			}
		}
		
		@GetMapping("/user/search/{searchtext}")
		public ResponseEntity<List<UserEntity>> searchUserByNameAndID(@PathVariable String searchtext) throws Exception {
			List<UserEntity> usersDetails =  service.searchUserBasedOnIdName(searchtext);
			if(usersDetails != null) {
				return new ResponseEntity (usersDetails, HttpStatus.OK);
			}
			else {
				throw new DataNotFoundException("User details not found");
			}
		}

		
		
}

