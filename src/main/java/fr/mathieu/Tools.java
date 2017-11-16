package fr.mathieu;

import javax.servlet.http.HttpSession;

import fr.mathieu.user.User;

public class Tools {
	
	public static User getLoggedInUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		return user;
	}
	
	public static void setLoggedInUser(HttpSession session, User user) {
		session.setAttribute("user", user);
	}
	
	public static void setLoggedOutUser(HttpSession session) {
		session.setAttribute("user", null);
	}

}
