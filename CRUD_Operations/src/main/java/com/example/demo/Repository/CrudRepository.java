package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.CrudEntity;

@Repository
public interface CrudRepository extends JpaRepository<CrudEntity, Integer> {

	Optional<CrudEntity> findByUserId(String userId);

}
