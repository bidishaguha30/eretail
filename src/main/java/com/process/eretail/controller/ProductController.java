package com.process.eretail.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.process.eretail.model.CartTotalOutputModel;
import com.process.eretail.model.CartValueDiscountModel;
import com.process.eretail.model.ProductModel;
import com.process.eretail.model.ProductQuantity;
import com.process.eretail.repository.CreateProductRepository;
import com.process.eretail.repository.ProductInterface;
import java.util.stream.Collectors;


@RestController
public class ProductController {

	@Autowired
	ProductInterface productInterface;
	
	@Autowired
	CreateProductRepository createProductRepository;
	
	
	@GetMapping("/product")
    public List<ProductModel> index(){
        return productInterface.findAll();
    }

	 @GetMapping("/product/{id}")
	    public Optional<ProductModel> show(@PathVariable String id){
	        int productId = Integer.parseInt(id);
	        return productInterface.findById(productId);
	    }
	
	 @PostMapping("/product/insert")
	    public ProductModel create(@RequestBody Map<String, String> body) throws ParseException{
	        String name = body.get("name");
	        String idText = body.get("id");
	        Integer templId = Integer.parseInt(idText);
	        String department = body.get("department");
	        String company = body.get("company");
	        String startDateS = body.get("startDate");
	        String endDateS = body.get("endDate");
	        String activeS = body.get("active");
	        Double cost = Double.parseDouble(body.get("cost"));
	        String currency = body.get("currency");

	        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateS);
	        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDateS);
	        Boolean active = false;
            if(activeS.equalsIgnoreCase("true")) {
            	active = true;
            }
            else
            {
            	active = false;
            }
	        return productInterface.save(new ProductModel(templId,name,department,company,startDate,endDate,active,cost,currency));
	    }

	 @DeleteMapping("product/delete/{id}")
	    public boolean delete(@PathVariable String id){
	        int productId = Integer.parseInt(id);
	        productInterface.deleteById(productId);
	        return true;
	    }
	 
   @PutMapping("/product/{id}")
   public ProductModel update(@PathVariable String id, @RequestBody Map<String, String> body) throws ParseException{
       int productId = Integer.parseInt(id);
       // getting template
       ProductModel prodModel = new ProductModel();
       Optional<ProductModel> prod = productInterface.findById(productId);
       if(prod.isPresent()) {
    	   prodModel = prod.get();  	   
       }
       prodModel.setProductName(body.get("name"));
       prodModel.setCompany(body.get("company"));
       prodModel.setDepartment(body.get("department"));
       Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(body.get("startDate"));
       Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(body.get("endDate"));
       Boolean active = false;
       if(body.get("active").equalsIgnoreCase("true")) {
       	active = true;
       }
       else
       {
       	active = false;
       }
       Double cost = Double.parseDouble(body.get("cost"));
       prodModel.setCost(cost);
       prodModel.setCurrency(body.get("currency"));
       prodModel.setActive(active);
       prodModel.setLifecycleEndDate(endDate);
       prodModel.setLifecycleStartDate(startDate);
       return productInterface.save(prodModel);
   }
   
   @PostMapping("/product/cartTotal")
   public CartTotalOutputModel cartTotal(@RequestBody List<ProductQuantity> body) {
	  List<CartTotalOutputModel> cartList = new ArrayList<CartTotalOutputModel>();
	  List<String> totalDiscountList = new ArrayList<String>();
	  CartTotalOutputModel cartTotal = new CartTotalOutputModel();
	  CartValueDiscountModel cartModel = new CartValueDiscountModel();
	  HashMap<String,Integer> mapDepartment = new HashMap<>();
	  Double totalCost = 0.0;
	  int value = 1;
	  for(ProductQuantity temp : body) {
		  cartModel = createProductRepository.totalCartValue(temp.getProductId(),temp.getQuantity());
		  totalDiscountList.addAll(cartModel.getDiscountList());
		  totalCost = totalCost + cartModel.getTotalCartCost();
		  if(mapDepartment.containsKey(cartModel.getDepartment())) {
			  value = mapDepartment.get(cartModel.getDepartment()) + 1;
		       mapDepartment.put(cartModel.getDepartment(), value);
		         }
		  else {
		       mapDepartment.put(cartModel.getDepartment(), value);
		       }
		  	}
	  
          if(totalCost>50) {
        	  totalCost = totalCost - 5;
          }

  		long deptStatusNo = mapDepartment.entrySet().stream()
  				.filter(x-> x.getValue() > 5)
  				.map(x-> x.getValue())
  				.count();

  		if(deptStatusNo > 0) {
  		     totalCost = totalCost/10;
  		}
		  cartTotal.setCartCost(totalCost);
		  cartTotal.setDiscountList(totalDiscountList);
	   return cartTotal;
   }
   
}
