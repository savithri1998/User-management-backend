package com.example.microservices.demo.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.microservices.demo.usermanagement.Entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{

	boolean existsByEmail(String email);
	UserEntity findByEmail(String email);
		
		 @Query("SELECT u FROM UserEntity u WHERE u.status = :type")
		 List<UserEntity> findAllUsers(Boolean type);

		 
		 @Query(value = "SELECT * FROM userdatabase.user_entity u " +
		            "WHERE (u.status = true) " +
		            "AND ((:textRegex is null or CONVERT(u.user_id, CHAR) REGEXP :textRegex) " +
		            "OR (:textRegex is null or u.first_name REGEXP :textRegex) " +
		            "OR (:textRegex is null or u.last_name REGEXP :textRegex)) " +
		            "LIMIT 5", nativeQuery = true)
			List<UserEntity> findByIdOrNameRegexWithLimit(String textRegex);
		 
	 	@Modifying
	    @Transactional
	    @Query("UPDATE UserEntity u SET u.status = :status WHERE u.id IN :ids")
	    void enableDisableStatusByIds(@Param("status") boolean status, @Param("ids") List<String> userIds);
}
