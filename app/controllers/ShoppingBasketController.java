package controllers;

import java.util.List;
import java.util.Map;

import models.ShoppingBasket;
import models.Product;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.shoppingBasket;

public class ShoppingBasketController extends Controller {
	
	@Transactional
	public static Result addProduct(int id) {
		
		Map<String, String[]> form = request().body().asFormUrlEncoded();	
			
		Integer quantity = Integer.parseInt(form.get("quantity")[0]); 
		
		ShoppingBasket shoppingBasket = new ShoppingBasket();
		
		shoppingBasket.setQuantity(quantity);
		shoppingBasket.setProduct_id(id);
		JPA.em().persist(shoppingBasket);
		
		Product product = JPA.em().find(Product.class, id);
		product.setQuantity(product.getQuantity() - shoppingBasket.getQuantity());		
		
		return redirect(routes.ProductController.getProduct(id)); 
		
	}
	
	@Transactional
	public static Result getShoppingBasket() {	
		
		List<ShoppingBasket> basket = JPA.em().createQuery("SELECT s FROM ShoppingBasket s", ShoppingBasket.class).getResultList();
		List<Product> products = JPA.em().createQuery("SELECT product FROM Product product", Product.class).getResultList();

		return ok(shoppingBasket.render(basket, products));
		
	}

}