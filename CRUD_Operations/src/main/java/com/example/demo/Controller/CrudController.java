package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.CrudEntity;
import com.example.demo.Service.CrudService;


@RestController
@RequestMapping("/v1/api")
public class CrudController {
	
	@Autowired
	private CrudService Service;
	
	
	@PostMapping("/save")
    public CrudEntity add(@RequestBody CrudEntity entity) {
        return Service.Addvalues(entity);
    }
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getEntityByUserId(@PathVariable String userId) {
	    CrudEntity entity = Service.getByUserId(userId);

	    if (entity != null) {
	        return ResponseEntity.ok(entity);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Entity not found for userId: " + userId);
	    }
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteEntityByUserId(@PathVariable String userId) {
	    boolean deleted = Service.deleteByUserId(userId);

	    if (deleted) {
	        return ResponseEntity.ok("Entity with userId " + userId + " deleted successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Entity not found for userId: " + userId);
	    }
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<?> updateEntityByUserId(@PathVariable String userId, @RequestBody CrudEntity updatedEntity) {
	    CrudEntity existingEntity = Service.getByUserId(userId);

	    if (existingEntity != null) {
	        // Update fields as needed
	        existingEntity.setFirst_name(updatedEntity.getFirst_name());
	        existingEntity.setLast_name(updatedEntity.getLast_name());
	        existingEntity.setDateOfbirth(updatedEntity.getDateOfbirth());
	        existingEntity.setAddress(updatedEntity.getAddress());

	        // Save the updated entity
	        CrudEntity savedEntity = Service.updateEntity(existingEntity);

	        return ResponseEntity.ok(savedEntity);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Entity not found for userId: " + userId);
	    }
	}
	
	

}
