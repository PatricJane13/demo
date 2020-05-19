package spring.dao;

import spring.model.Role;
import spring.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
        entityManager.persist(user.getRoles().get(0));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Query query = entityManager.createQuery("From User");
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        Query query = entityManager.createQuery("FROM User WHERE id = :id");
        query.setParameter("id", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public User findByUsername(String email) {
        Query query = entityManager.createQuery("FROM User WHERE email=:email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @Override
    public void updateUserRole(String role, Long id) {
        User user = getUserById(id);
        List<Role> list = user.getRoles();
        Role userRole = list.get(0);
        Query query = entityManager.createQuery("UPDATE Role Set role =:role WHERE id =:id ");
        query.setParameter("role", role);
        query.setParameter("id", userRole.getId());
        query.executeUpdate();
    }
}
