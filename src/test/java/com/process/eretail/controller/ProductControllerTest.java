package com.process.eretail.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.process.eretail.model.ProductModel;
import com.process.eretail.repository.CreateProductRepository;
import com.process.eretail.repository.ProductInterface;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	
	@Mock
    ProductInterface productInterface;
	
	@Mock
	CreateProductRepository createProductRepository;
	
	@InjectMocks
	ProductController prod;

	
	@Test
	public void index() throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");
		List<ProductModel> mockProductModelList = new ArrayList<>();

		ProductModel mockProductModel = new ProductModel(1, "123Product", "Dept1", "Company1", date, 
				date , true, 211.33, "$") ;
		mockProductModelList.add(mockProductModel);	
		Mockito.when(productInterface.findAll()).thenReturn(mockProductModelList);
		List<ProductModel> result = productInterface.findAll();
		Assert.assertEquals("123Product", result.get(0).getProductName());		
		
	}
}
 	