package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.createcategory;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;
import java.util.Map;
import models.Category;

public class CreateCategory extends Controller {
	
	@Security.Authenticated(Authentication.class)
	public static Result createCategoryForm() {
		return ok(createcategory.render());
	}
	
	@Transactional
	public static Result createCategory() {
		
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		String name = form.get("name")[0];
		Category category = new Category();
		category.setName(name);
		JPA.em().persist(category);
		
		return redirect(routes.UserController.categories());		
	}
	
}
