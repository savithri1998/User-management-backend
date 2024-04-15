package com.example.allFormFields.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.allFormFields.Exception.DataNotFoundException;
import com.example.allFormFields.Repository.AllFieldsRepository;
import com.example.allFormFields.Services.AllFieldServices;



@RestController
public class AllFieldsController {
	@Autowired
	private AllFieldServices allFields;
	
	
	@GetMapping("/getallfieldsdata")
	public ResponseEntity<?> getAllFields(){
		List response=allFields.findAllFields();
		if(response.isEmpty()) {
			return new ResponseEntity<>(response,HttpStatus.NotFound);
		}
		else {
			return new ResponseEntity<>(response , HttpStatus.OK);
		}
	}
}
