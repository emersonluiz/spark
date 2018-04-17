package br.com.emersonluiz.spark.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.emersonluiz.spark.model.User;

public class UserRepository {

	EntityManager session;

	public UserRepository() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		session = sf.createEntityManager();
	}

	public List<User> getUsers() {
		try {
			@SuppressWarnings("unchecked")
			List<User> users = session.createQuery("Select u From User u").getResultList();
			return users;
		} catch (Exception e) {
			throw e;
		}
	}

	public User getUser(int id) {
		try {
			Query query = session.createQuery("SELECT u FROM User u WHERE id = :id", User.class);
			query.setParameter("id", id);
			User user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	public User create(User user) {
		try {
			session.getTransaction().begin();
			session.persist(user);
			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			throw e;
		}
	}

	public void delete(int id) {
		User user = getUser(id);
		session.getTransaction().begin();
		session.remove(user);
		session.getTransaction().commit();
	}
}
