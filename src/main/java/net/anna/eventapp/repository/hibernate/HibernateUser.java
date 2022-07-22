package net.anna.eventapp.repository.hibernate;

import net.anna.eventapp.repository.UserRepository;
import net.anna.eventapp.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import session.HibernateUtil;

import java.util.List;

public class HibernateUser implements UserRepository {

    private static SessionFactory sessionFactory=
            HibernateUtil.getSessionFactory();

    public UserEntity save(UserEntity user){    // public void addTag(long id, String name) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
        //    System.out.println(user+"!");
            transaction = session.beginTransaction();
            UserEntity user1 = new UserEntity(1l, user.getName());
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

        public List<UserEntity> getAll() {
        Transaction transaction = null;
        try(      Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserEntity  ", UserEntity.class);
        //    Query query = session.createQuery("from UserEntity u Left Join Fetch u.events ", UserEntity.class);
            List<UserEntity> users = query.getResultList();
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

    public UserEntity getById(Long id) {
        Transaction transaction = null;
        try(   Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserEntity u Left Join FETCH u.events where u.id = "+ id , UserEntity.class);
            UserEntity user= (UserEntity) query.getSingleResult();
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
           UserEntity user = (UserEntity) session.get(UserEntity.class, id);

            String hql = "delete EventEntity where id_user= :id";
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


    public UserEntity update(UserEntity u) {//    public void updateTag(long id, String name) {
        Transaction transaction = null;
        try (     Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            UserEntity user = (UserEntity) session.get(UserEntity.class,u.getId());
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
