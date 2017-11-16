package fr.mathieu.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.mathieu.commande.Commande;


@Stateless
public class GestionUser {
	
	@PersistenceContext(name="Catalogue")
	EntityManager em;
	
	public void addUser(User user) {
		em.merge(user);
	}
	
	public User login(String login, String password) {
		TypedQuery<User> query = em.createQuery("from " + User.class.getSimpleName(), User.class);
		List<User> users = query.getResultList();
		
		for (User user : users) {
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> getUsers(){
		TypedQuery<User> query = em.createQuery("from " + User.class.getSimpleName(), User.class);
		return query.getResultList();
	}
	
	public User findUserById(int userId) {
		User user = em.find(User.class, userId);
		return user;
	}

}
