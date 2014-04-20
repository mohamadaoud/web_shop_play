package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import views.html.order;
import views.html.createorder;
import java.util.List;
import models.Order;
import models.ShoppingBasket;
import models.Product;

public class OrderController extends Controller {
	
	public static Result order() {	
		return ok(order.render());
	}
	
	@Transactional
	@Security.Authenticated(Authentication.class)
	public static Result createOrder() {
		
		Order order = new Order();	
		JPA.em().persist(order);	
		
		List<ShoppingBasket> basket = JPA.em().createQuery("SELECT s FROM ShoppingBasket s", ShoppingBasket.class).getResultList();
		List<Product> products = JPA.em().createQuery("SELECT p FROM Product p", Product.class).getResultList();
		
		JPA.em().createNativeQuery("TRUNCATE table ShoppingBasket").executeUpdate();
		
		return ok(createorder.render(order, basket, products));
	}

	
}