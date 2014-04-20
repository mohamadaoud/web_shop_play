package controllers;

import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import models.Category;
import models.Product;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.createproduct;

public class CreateProduct extends Controller {
	
	@Transactional
	@Security.Authenticated(Authentication.class)
	public static Result createProductForm(){	
		List<Category> categories = JPA.em().createQuery("SELECT c FROM Category c", Category.class).getResultList();
		
		return ok(createproduct.render(categories));
	}

	@Transactional	
	public static Result createProduct(){
	
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		String name = form.get("name")[0];
		String description = form.get("description")[0];
		Double cost = Double.parseDouble(form.get("cost")[0]);
		Double RRP = Double.parseDouble(form.get("rrp")[0]);
		Integer quantity = Integer.parseInt(form.get("quantity")[0]);
		Integer categoryId = Integer.parseInt(form.get("category_id")[0]);
		
		TypedQuery<Category> query = JPA.em().createQuery("SELECT c FROM Category c WHERE c.id = :categoryId", Category.class);
		query.setParameter("categoryId", categoryId);

		List<Category> matchingCategories = query.getResultList();
		Product product = new Product();
	
		product.setName(name);
		product.setDescription(description);
		product.setCost(cost);
		product.setRRP(RRP);
		product.setQuantity(quantity);
		product.category = matchingCategories;
		JPA.em().persist(product);
		
		return redirect(routes.UserController.products());
	}

}
