package com.example.microservices.demo.usermanagement.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.microservices.demo.usermanagement.Entity.UserEntity;
import com.example.microservices.demo.usermanagement.Exception.BadRequestException;
import com.example.microservices.demo.usermanagement.Exception.DataNotFoundException;
import com.example.microservices.demo.usermanagement.repository.UserRepository;
import org.jasypt.util.text.AES256TextEncryptor;


@Service
public class Userservices {
	@Autowired
	private UserRepository userRepository;
	
	//getmapping(get all users)
	public List<UserEntity> findAllUser(Boolean status) throws Exception {
		try {
			return userRepository.findAllUsers(status);	
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
		
	//get single user
	public UserEntity findUserSingleID(String CompanyId) {
		return userRepository.findById(CompanyId).orElse(null);
	}
	
	//post data
	public UserEntity createUserData(UserEntity userdetails) throws Exception {	
		UserEntity response=userRepository.findByEmail(userdetails.getEmail());
			if(response!=null)  {
				throw new BadRequestException("Email already existing");
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
	
	
	
	//put mapping for update
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
				throw new Exception("id not existing");
			}

		}
	
	
	//delete users
	public UserEntity deleteUserSingleId(String id, Boolean status) throws Exception {
		if(userRepository.existsById(id)){
			UserEntity updatedata =userRepository.getById(id);
			updatedata.setStatus(status);
			return userRepository.save(updatedata);
		}
		else{
			throw new Exception("Id not exist");
		}
	}

	public List<UserEntity> searchUser(String searchtext) throws Exception{
			try {
				List<UserEntity> allUsers = userRepository.findByIdOrNameRegexWithLimit(searchtext);
				return allUsers;
			}catch(Exception e){
				throw new Exception(e.getMessage());
			}
	}
	

    public String enabledisableUsers(List<String> userIds,Boolean status) throws Exception {
    	try {
			userRepository.setStatusTrueByIds(status, userIds);
			return "User status updated successfully";
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
    }

	
}
