package com.example.microservices.demo.usermanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.microservices.demo.usermanagement.Entity.UserEntity;
import com.example.microservices.demo.usermanagement.Exception.BadRequestException;
import com.example.microservices.demo.usermanagement.repository.UserRepository;


@Service
public class Userservices {
	@Autowired
	private UserRepository userRepository;
	
	//get all users by status
	public List<UserEntity> findUsersBasedOnStatus(Boolean status) throws Exception {
		try {
			return userRepository.findAllUsers(status);	
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
		
	//get single user
	public UserEntity getUserID(String userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
	//post data
	public UserEntity createUserDatails(UserEntity userdetails) throws Exception {	
		UserEntity response=userRepository.findByEmail(userdetails.getEmail());
			if(response!=null)  {
				throw new BadRequestException("User already exist with this email");
			}
			else {
				try {	
					userdetails.setStatus(true);
					return userRepository.save(userdetails);
				}
				catch(Exception e) {
					throw new Exception(e.getMessage());
				}
		   }
	}
	
	
	
	//update user details
	public UserEntity updateUerData(UserEntity userdetails, String userid) throws Exception {
		if(userRepository.existsById(userid)) {
			UserEntity updatedata =userRepository.getById(userid);
			if((updatedata.getEmail()).equals(userdetails.getEmail())){
				updatedata.setFirstName(userdetails.getFirstName());
				updatedata.setLastName(userdetails.getLastName());
				updatedata.setMobileNumber(userdetails.getMobileNumber());
				updatedata.setDob(userdetails.getDob());
				updatedata.setMobileNumber(userdetails.getMobileNumber());
				updatedata.setAddress(userdetails.getAddress());
				updatedata.setGender(userdetails.getGender());
				return userRepository.save(updatedata);
			}
			else{
				if(userRepository.existsByEmail(userdetails.getEmail())) {
					throw new BadRequestException("Email already existing");
				}
				try {
					updatedata.setEmail(userdetails.getEmail());	
					updatedata.setFirstName(userdetails.getFirstName());
					updatedata.setLastName(userdetails.getLastName());
					updatedata.setMobileNumber(userdetails.getMobileNumber());
					updatedata.setDob(userdetails.getDob());
					updatedata.setMobileNumber(userdetails.getMobileNumber());
					updatedata.setAddress(userdetails.getAddress());
					updatedata.setGender(userdetails.getGender());
					return userRepository.save(updatedata);
				}
				catch(Exception e) {
					throw new Exception(e.getMessage());
				}
			}
		}
			
		else {
				throw new Exception("User details not found");
			}

		}
	
	
	//Enable or disable user by id
	public UserEntity enableAndDisableUserById(String id, Boolean status) throws Exception {
		if(userRepository.existsById(id)){
			UserEntity updatedata =userRepository.getById(id);
			updatedata.setStatus(status);
			return userRepository.save(updatedata);
		}
		else{
			throw new Exception("User details not found");
		}
	}

	//Search user based on id or name
	public List<UserEntity> searchUserBasedOnIdName(String searchtext) throws Exception{
			try {
				List<UserEntity> allUsers = userRepository.findByIdOrNameRegexWithLimit(searchtext);
				return allUsers;
			}catch(Exception e){
				throw new Exception(e.getMessage());
			}
	}
	

	//Enable or disable multiple user	
    public String enabledisableUsers(List<String> userIds,Boolean status) throws Exception {
    	try {
			userRepository.enableDisableStatusByIds(status, userIds);
			return "User status updated successfully";
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
    }

	
}
