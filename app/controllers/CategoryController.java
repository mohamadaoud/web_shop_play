package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.showAllCategories;
import views.html.displayOneCategory;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;
import java.util.List;
import models.Category;

public class CategoryController extends Controller {

	@Transactional
	public static Result getCategories() {

		List<Category> categories = JPA.em().createQuery("SELECT category FROM Category category", Category.class).getResultList();

		return ok(showAllCategories.render(categories));
	}
	
	@Transactional
	public static Result getCategory(int id) {

		Category category = JPA.em().find(Category.class, id);
		
		return ok(displayOneCategory.render(category));	
	}

}
