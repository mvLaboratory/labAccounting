package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;

public class DB_Manager {
    public static DB_Manager instance;
    private static SessionFactory factory;

    private DB_Manager() {
        try{
            Configuration conf = new Configuration();
            conf = conf.configure();
            factory = conf.buildSessionFactory();
        }
        catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            ex.printStackTrace();
            WindowManager.openErrorWindow("DB connecting error!");
            //throw new ExceptionInInitializerError(ex);
        }
    }


    public static void initialize() {
        if (instance == null)
            instance = new DB_Manager();
    }

    public static DB_Manager getInstance() {
        if (instance == null)
            instance = new DB_Manager();
        return instance;
    }

    //  public static ArrayList<HashMap<String, Object>> ReadReagentCatalog() {
    public static List readReagentCatalog() {
        //List<ReagentCatalog> catalog = new ArrayList<>();
        List catalog = new ArrayList();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            catalog = session.createQuery("FROM ReagentCatalog").list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error selecting reagents.");
        }finally {
            session.close();
        }
        return catalog;
    }

    public Catalog readReagentCatalogElement(Integer id){
        ReagentCatalog element = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            element = session.get(ReagentCatalog.class, id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error reading reagent catalog id: " + id + "from DB!");
        }finally {
            session.close();
        }

        return element;
    }

    public Integer saveCatalogElement(Catalog element){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer catalogID = null;
        try{
            tx = session.beginTransaction();
            catalogID = (Integer) session.save(element);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with saving " + element.getHeader());
        }finally {
            session.close();
        }
        return catalogID;
    }

    public void updateCatalogElement(Catalog element){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(element);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with updating " + element.getHeader());
        }finally {
            session.close();
        }
    }

    public void deleteCatalogElement(Catalog element){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(element);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with deleting " + element.getHeader());
        }finally {
            session.close();
        }
    }

    public static void close() {
        factory.close();
    }
}
