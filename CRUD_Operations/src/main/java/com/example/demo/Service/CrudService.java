package com.example.demo.Service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.CrudEntity;
import com.example.demo.Repository.CrudRepository;

@Service
public class CrudService {
	
	@Autowired
	CrudRepository Repository;
	
	public CrudEntity Addvalues   (CrudEntity entity) {
        String generatedUserId = generateUserId();
        entity.setUserId(generatedUserId);
        return Repository.save(entity);
    }
	
	
	private String generateUserId() {
	    String characters = "ACEFGHWXYZ0123456789";
	    StringBuilder userId = new StringBuilder();
	    for (int i = 0; i < 6; i++) {
	        int index = (int) (Math.random() * characters.length());
	        userId.append(characters.charAt(index));
	    }

	    return userId.toString();
	}
	
	public CrudEntity getByUserId(String userId) {
        Optional<CrudEntity> optionalEntity = Repository.findByUserId(userId);

        return optionalEntity.orElse(null);
    }

    public boolean deleteByUserId(String userId) {
        Optional<CrudEntity> optionalEntity = Repository.findByUserId(userId);

        if (optionalEntity.isPresent()) {
            Repository.delete(optionalEntity.get());
            return true;
        } else {
            return false;
        }
    }

    
    public CrudEntity updateEntity(CrudEntity existingEntity) {
        return Repository.save(existingEntity);
    }

}
