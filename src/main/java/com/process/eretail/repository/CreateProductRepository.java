package com.process.eretail.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.process.eretail.model.CartValueDiscountModel;
import com.process.eretail.model.ProductModel;

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
	
//	public Double discountCartByDepartment(Double totalCost) {
//		
//		
//		
//		return totalCost;
//		
//	}

}
