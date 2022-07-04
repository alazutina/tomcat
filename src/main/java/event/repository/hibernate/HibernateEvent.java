package event.repository.hibernate;

import event.model.Event;
import event.model.File;
import event.model.User;
import event.repository.EventRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import session.HibernateUtil;

import java.util.List;

public class HibernateEvent implements EventRepository {

    private static SessionFactory sessionFactory=
            HibernateUtil.getSessionFactory();

    public Event save(Event event) {    // public void addTag(long id, String name) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(event);//
            transaction.commit();
            return event;
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

    //
    public List<Event> getAll() {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Event ");
//            List<File> files = session.createQuery("from File").list();// session.createQuery("FROM tags").list();
//            Query query = session.createQuery("from User u Left Join Fetch u.events ", User.class);
//            List<User> users = query.getResultList();
            List<Event> events = query.getResultList();
            transaction.commit();
            return events;
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

    public Event getById(Long id) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Event event = (Event) session.get(Event.class, id);
            transaction.commit();
            return event;
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }
    //4

    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "delete Event where id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();

//            Event event = (Event) session.get(Event.class, id);
//            session.delete(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
    }

    public Event update(Event event) {//    public void updateTag(long id, String name) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Event event1 = new Event(event.getId(), event.getAction(), event.getFile(), event.getUser());
            session.update(event1);
            transaction.commit();
            return event1;
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
            return null;
        }
    }
}