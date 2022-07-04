package event.repository.hibernate;

import event.model.Event;
import event.model.File;
import event.repository.FileRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import session.HibernateUtil;

import java.util.List;

public class HibernateFile implements FileRepository {

    private static SessionFactory sessionFactory=
            HibernateUtil.getSessionFactory();

    public File save(File file){    // public void addTag(long id, String name) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            File file1 = new File(1l, file.getPath());
            session.save(file1);//    session.save(tag);
            transaction.commit();
            return  file1;}
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

//
    public List<File> getAll() {
        Transaction transaction = null;
        try(      Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<File> files = session.createQuery("from File").list();// session.createQuery("FROM tags").list();
            transaction.commit();
            return files;
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
        return null;
    }

    public File getById(Long id) {
        Transaction transaction = null;
        try(   Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            File file = (File) session.get(File.class, id);
            transaction.commit();
            return file;
        }
        catch (Exception e){
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
        try (        Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();

            File file = (File) session.get(File.class, id);
            String hql = "delete Event where id_file= :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();

            session.delete(file);
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null) {
                System.out.println("");
            }
            e.printStackTrace();
        }
    }

    public File update(File t) {//    public void updateTag(long id, String name) {
        Transaction transaction = null;
        try (     Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            File file = (File) session.get(File.class, t.getId());
            file.setPath(t.getPath());
            session.update(file);
            transaction.commit();
            return file;
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
