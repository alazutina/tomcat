package net.anna.eventapp.repository.hibernate;

import net.anna.eventapp.repository.EventRepository;
import net.anna.eventapp.model.EventEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import session.HibernateUtil;

import java.util.List;

public class HibernateEvent implements EventRepository {

    private static SessionFactory sessionFactory=
            HibernateUtil.getSessionFactory();

    public EventEntity save(EventEntity event) {    // public void addTag(long id, String name) {
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

    public List<EventEntity> getAll() {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from EventEntity ");
            List<EventEntity> events = query.getResultList();
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

    public EventEntity getById(Long id) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            EventEntity event = (EventEntity) session.get(EventEntity.class, id);
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
//             transaction = session.beginTransaction();
//             String hql = "delete EventEntity where id= :id";
//             Query query = session.createQuery(hql);
//             query.setParameter("id", id);
//             query.executeUpdate();
//             transaction.commit();
            
            
           transaction = session.beginTransaction();
           EventEntity event = (EventEntity) session.get(EventEntity.class, id);

//             String hql = "delete EventEntity where id_user= :id";
//             Query query = session.createQuery(hql);
//             query.setParameter("id", id);
//             query.executeUpdate();

            session.delete(event);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
    }

    public EventEntity update(EventEntity event) {//    public void updateTag(long id, String name) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            EventEntity event1 = new EventEntity(event.getId(), event.getAction(), event.getFile(), event.getUser());
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
