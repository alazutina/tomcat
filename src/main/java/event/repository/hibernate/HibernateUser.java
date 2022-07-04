package event.repository.hibernate;

import event.model.File;
import event.model.User;
import event.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import session.HibernateUtil;

import java.util.List;

public class HibernateUser implements UserRepository {

    private static SessionFactory sessionFactory=
            HibernateUtil.getSessionFactory();

    public User save(User user){    // public void addTag(long id, String name) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
        //    System.out.println(user+"!");
            transaction = session.beginTransaction();
            User user1 = new User(1l, user.getName());
            session.save(user1);//    session.save(tag);
            transaction.commit();
            return  user1;}
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

        public List<User> getAll() {
        Transaction transaction = null;
        try(      Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User u Left Join Fetch u.events ", User.class);
            List<User> users = query.getResultList();
            transaction.commit();
            return users;
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

    public User getById(Long id) {
        Transaction transaction = null;
        try(   Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User u Left Join FETCH u.events where u.id = "+ id , User.class);
            User user= (User) query.getSingleResult();
            transaction.commit();
            return user;
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(Long id) {
        Transaction transaction = null;
        try (        Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
           User user = (User) session.get(User.class, id);

            String hql = "delete Event where id_user= :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();

            session.delete(user);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
    }


    public User update(User u) {//    public void updateTag(long id, String name) {
        Transaction transaction = null;
        try (     Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            User user = (User) session.get(User.class,u.getId());
            user.setName(u.getName());
            session.update(user);
            transaction.commit();
            return user;
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

}
