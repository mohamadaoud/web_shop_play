package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.createuser;
import play.db.jpa.Transactional;
import play.db.jpa.JPA;
import java.util.Map;
import models.User;

public class CreateUser extends Controller {
	
	public static Result createUserForm() {
		return ok(createuser.render());
	}
	
	@Transactional
	public static Result createUser() {
		
		Map<String, String[]> form = request().body().asFormUrlEncoded();
		
		String name = form.get("name")[0];
		String username = form.get("username")[0];
		String password = form.get("password")[0];
		String address = form.get("address")[0];
		String phone = form.get("phone")[0];

		User user = new User();
	
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setAddress(address);
		user.setPhoneNumber(phone);
		JPA.em().persist(user);
			
		return redirect(routes.UserController.userForm());
	}	
	
}
