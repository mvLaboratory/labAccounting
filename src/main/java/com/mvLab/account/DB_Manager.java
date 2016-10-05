package com.mvLab.account;

import com.mvLab.account.catalogs.Catalog;
import com.mvLab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.account.documents.Document;
import com.mvLab.account.documents.Savable;
import com.mvLab.account.documents.reagentAdmission.ReagentAdmission;
import com.mvLab.account.documents.reagentConsumption.ReagentConsumption;
import com.mvLab.account.register.RecordSet;
import com.mvLab.account.register.Register;
import com.mvLab.account.reports.Report;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

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


    //Reagent Catalog+++
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
    //Reagent Catalog---

    //Reagent Admission+++
    public List readReagentAdmission() {
        List catalog = new ArrayList();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            catalog = session.createQuery("FROM ReagentAdmission").list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error selecting reagent admission.");
        }finally {
            session.close();
        }
        return catalog;
    }

    public Document readReagentAdmissionElement(Integer id){
        ReagentAdmission element = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            element = session.get(ReagentAdmission.class, id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error reading reagent admission id: " + id + "from DB!");
        }finally {
            session.close();
        }

        return element;
    }
    //Reagent Admission---

    //Reagent Consumption+++
    public List readReagentConsumption() {
        List docList = new ArrayList();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            docList = session.createQuery("FROM ReagentConsumption").list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error selecting consumption document.");
        }finally {
            session.close();
        }
        return docList;
    }

    public Document readReagentConsumptionElement(Integer id){
        ReagentConsumption document = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            document = session.get(ReagentConsumption.class, id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error reading reagent consumption document: id: " + id + "from DB!");
        }finally {
            session.close();
        }

        return document;
    }
    //Reagent Consumption---

    //Documents+++
    public Integer saveDocumentElement(Document element){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer docID = null;
        try{
            tx = session.beginTransaction();
            docID = (Integer) session.save(element);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with saving " + element.getHeader());
        }finally {
            session.close();
        }
        return docID;
    }

    public Integer saveElement(Savable element){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer docID = null;
        try{
            tx = session.beginTransaction();
            docID = (Integer) session.save(element);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with saving element");
        }finally {
            session.close();
        }
        return docID;
    }

    public void updateDocumentElement(Document element){
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
    //Documents---

    //RegisterRecordSet+++
    public void saveRecordSet(RecordSet recordSet){
        Session session = factory.openSession();
        Transaction tx = null;
        //Integer docID = null;
        try{
            tx = session.beginTransaction();
            for (Register record : recordSet.getRecordSet()) {
                session.save(record);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with saving record set");
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with saving record set");
        }
        finally {
            session.close();
        }
    }

    public Integer saveRegisterRecord(Register record){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer recordID = null;
        try{
            tx = session.beginTransaction();
            recordID = (Integer) session.save(record);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with saving record set");
        }finally {
            session.close();
        }
        return recordID;
    }

    public void deleteDocPosts(Document doc){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
//            session.createQuery("Select balance.reagent as reagent, Sum(balance.quantity) as balance from ReagentBalance balance group by balance.reagent")
            Query query = session.createQuery("delete from ReagentBalance where document = :doc");
            query.setParameter("doc", doc);
            int result = query.executeUpdate();

            Query queryUsage = session.createQuery("delete from ReagentUsage where document = :doc");
            queryUsage.setParameter("doc", doc);
            int resultUsage = queryUsage.executeUpdate();

            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with deleting element");
        }finally {
            session.close();
        }
    }
    //RegisterRecordSet---

    //Reports+++
//    public List<ReagentBalance> readReagentBalance() {
//        List balance = new ArrayList();
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try{
//            tx = session.beginTransaction();
//            String queryString = BalanceReport.getQueryString();
//            balance = session.createNativeQuery(queryString, BalanceReport.class).list();
//            tx.commit();
//        }catch (HibernateException e) {
//            if (tx != null)
//                tx.rollback();
//            e.printStackTrace();
//            WindowManager.openErrorWindow("Error building report.");
//        }finally {
//            session.close();
//        }
//        return balance;
//    }

    public <T> List<T> readReport(Report report) {
        List usage = new ArrayList();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String queryString = report.getQueryString();
            usage = session.createNativeQuery(queryString, report.getClass()).list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error building report.");
        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error building report.");
        }
        finally {
            session.close();
        }
        return usage;
    }
    //Reports---

    public void updateElement(Object element){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(element);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with updating.");
        }finally {
            session.close();
        }
    }

    public void deleteElement(Object element){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(element);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            WindowManager.openErrorWindow("Error with deleting element");
        }finally {
            session.close();
        }
    }

    public static void close() {
        factory.close();
    }
}
