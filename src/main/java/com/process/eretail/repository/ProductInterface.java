package com.process.eretail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.process.eretail.model.ProductModel;


@Repository
public interface ProductInterface extends JpaRepository<ProductModel, Integer>{
	
	Optional<ProductModel> findById(Integer id);


}
