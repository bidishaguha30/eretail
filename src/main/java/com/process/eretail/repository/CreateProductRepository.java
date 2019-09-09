package com.process.eretail.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.process.eretail.model.CartTotalOutputModel;
import com.process.eretail.model.CartValueDiscountModel;
import com.process.eretail.model.ProductModel;
import com.process.eretail.model.ProductQuantity;

@Component
public class CreateProductRepository {
	
	private ProductInterface productInterface;
	
	public CartValueDiscountModel totalCartValue(String id, String quantity){
	    int productId = Integer.parseInt(id);
	    int quant = Integer.parseInt(quantity);
		ProductModel prodModel = new ProductModel();
		Optional<ProductModel> prod = productInterface.findById(productId);
	       if(prod.isPresent()) {
	    	   prodModel = prod.get();  	   
	       }
	       CartValueDiscountModel discountList = discountCart(quant,prodModel.getCost()) ;
	       return discountList;		
	}

	private CartValueDiscountModel discountCart(Integer quant,Double cost) {
		 CartValueDiscountModel cartModel = new CartValueDiscountModel();
		List<String>  discountList = new ArrayList<String>();
		Double costTotal, costPer;
		//2 rule
		if(quant>10) {
			discountList.add("More than 10 same items reduce cost by 10%");
			costPer = cost/10;
			costTotal = costPer * quant;
		}
		else if(quant>5) {
			discountList.add("More than 5 same items reduce cost by 10%");
			costPer = cost/10;
			costTotal = costPer * quant;
		}
		else if(quant==1) {
			discountList.add("Purchase one item and can get the exact same item for 50% off");
			costPer = cost/2;
			costTotal = costPer + cost;
		}
		else {
			costTotal = cost * quant;
		}		
		if(costTotal>50) {
			discountList.add("Total Price is greater than 50");
			costTotal = costTotal - 5;
		}		
		cartModel.setDiscountList(discountList);
		cartModel.setTotalCartCost(costTotal);
		return cartModel;
	}

	public CartTotalOutputModel getTotalCartDiscountValue(List<ProductQuantity> body) {
	      CartTotalOutputModel cartTotal = new CartTotalOutputModel();
		  List<String> totalDiscountList = new ArrayList<>();
		  CartValueDiscountModel cartModel = new CartValueDiscountModel();
		  HashMap<String,Integer> mapDepartment = new HashMap<>();
		  Double totalCost = 0.0;
		  int value = 1;
		  for(ProductQuantity temp : body) {
			  cartModel = totalCartValue(temp.getProductId(),temp.getQuantity());
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
		    long deptStatusNumber = getDeptStatusNumber(mapDepartment);
	  		if(deptStatusNumber > 0) {
	  		     totalCost = totalCost/10;
	  		}
	        if(totalCost>50) {
	        	  totalCost = totalCost - 5;
	          }
			  cartTotal.setCartCost(totalCost);
			  cartTotal.setDiscountList(totalDiscountList);
		return cartTotal;
	}

	private long getDeptStatusNumber(HashMap<String, Integer> mapDepartment) {
		long deptStatusNo = mapDepartment.entrySet().stream()
  				.filter(x-> x.getValue() > 5)
  				.map(x-> x.getValue())
  				.count();		
		return deptStatusNo;
	}
	

}
