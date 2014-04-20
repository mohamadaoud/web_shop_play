package controllers;

import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import models.User;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.categories;
import views.html.products;
import views.html.userlogin;

public class UserController extends Controller {
	
	@Security.Authenticated(Authentication.class)
	public static Result categories() {
		return ok(categories.render());
	}
	
	@Security.Authenticated(Authentication.class)
	public static Result products() {
		return ok(products.render());
	}
	
	public static Result userForm() {
		session().clear();
		return ok(userlogin.render());
	}
	
	@Transactional
	public static Result userLogin() {

		Map<String, String[]> form = request().body().asFormUrlEncoded();

		String username = form.get("username")[0];
		String password = form.get("password")[0];

		boolean usernameIsEmpty = "".equals(username);
		boolean passwordIsEmpty = "".equals(password);

		if (usernameIsEmpty || passwordIsEmpty) {
			if (usernameIsEmpty) {
				flash().put("username-empty", "yes");
			}
			if (passwordIsEmpty) {
				flash().put("password-empty", "yes");
			}
			return redirect(routes.UserController.userForm());
		}

		TypedQuery<User> query = JPA.em().createQuery("SELECT user FROM User user WHERE user.username = :username AND user.password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);

		List<User> matchingUsers = query.getResultList();
		
		if(matchingUsers.size() == 1) {
			session().put("username", username);
			return redirect(routes.Application.main());
		} else {
			flash().put("no-username-password-match", "yes");
			return redirect(routes.UserController.userForm());
		} 		
	} 

	public static Result userLogout() {
		
		return redirect(routes.UserController.userForm());
		
	}
	
}
