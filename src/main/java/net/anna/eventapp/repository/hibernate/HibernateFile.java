package net.anna.eventapp.repository.hibernate;

import net.anna.eventapp.repository.FileRepository;
import net.anna.eventapp.model.FileEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import session.HibernateUtil;

import java.util.List;

public class HibernateFile implements FileRepository {

    private static SessionFactory sessionFactory=
            HibernateUtil.getSessionFactory();

    public FileEntity save(FileEntity file){    // public void addTag(long id, String name) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            FileEntity file1 = new FileEntity(1l, file.getPath());
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

    public List<FileEntity> getAll() {
        Transaction transaction = null;
        try(      Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<FileEntity> files = session.createQuery("from FileEntity").list();// session.createQuery("FROM tags").list();
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

    public FileEntity getById(Long id) {
        Transaction transaction = null;
        try(   Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            FileEntity file = (FileEntity) session.get(FileEntity.class, id);
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

            FileEntity file = (FileEntity) session.get(FileEntity.class, id);
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

    public FileEntity update(FileEntity t) {//    public void updateTag(long id, String name) {
        Transaction transaction = null;
        try (     Session session = this.sessionFactory.openSession()){
            transaction = session.beginTransaction();
            FileEntity file = (FileEntity) session.get(FileEntity.class, t.getId());
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
