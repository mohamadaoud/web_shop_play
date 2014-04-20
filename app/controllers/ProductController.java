package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.showAllProducts;
import views.html.displayOneProduct;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;
import java.util.List;
import models.Product;

public class ProductController extends Controller {
	
	@Transactional
	public static Result getProducts() {

		List<Product> products = JPA.em().createQuery("SELECT p FROM Product p", Product.class).getResultList();

		return ok(showAllProducts.render(products));
	}

	@Transactional
	public static Result getProduct(int id) {

		Product product = JPA.em().find(Product.class, id);
		
		return ok(displayOneProduct.render(product));
	}
	
}