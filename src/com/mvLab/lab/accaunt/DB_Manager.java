package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.catalogs.Reagents.ReagentCatalog;
import com.mvLab.lab.accaunt.documents.Document;
import com.mvLab.lab.accaunt.windows.MainWindow;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DB_Manager {
    public static DB_Manager instance;
    private static SessionFactory factory;

//    private static Connection conn;
//    private static Statement statmt;
//    private static ResultSet resSet;
//    private static String errLog;

    private DB_Manager() {
        try{
            Configuration conf = new Configuration();
            conf = conf.configure();
            factory = conf.buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            ex.printStackTrace();
            WindowManager.openErrorWindow("DB connecting error!");
            //throw new ExceptionInInitializerError(ex);
        }
//        conn = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            conn = DriverManager.getConnection("jdbc:sqlite:base/labBase.s3db");
//        }
//        catch (SQLException e) {
//            WindowManager.openErrorWindow("SQL error");
//        }
//        catch (ClassNotFoundException e) {
//            WindowManager.openErrorWindow("ClassNotFoundException");
//        }
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
//        try {
//            statmt = conn.createStatement();
//            resSet = statmt.executeQuery("SELECT * FROM Reagents");
//        }
//        catch (SQLException e) {
//            System.out.println(e.getErrorCode());
//        }
//
//        ArrayList<HashMap<String, Object>> catalog = new ArrayList<HashMap<String, Object>>();
//
//        try {
//            while(resSet.next())
//            {
//                int id = resSet.getInt("id");
//                String  name = resSet.getString("name");
//                String  description = resSet.getString("description");
//                String  uuid = resSet.getString("uuid");
//
//                HashMap<String, Object> catElement = new HashMap<String, Object>();
//                catElement.put("id", id);
//                catElement.put("name", name);
//                catElement.put("description", description);
//                catElement.put("uuid", uuid);
//
//                catalog.add(catElement);
//            }
//        }
//        catch (SQLException e) {
//            System.out.println(e.getErrorCode());
//        }

    }

    public Catalog readReagentCatalogElement(Integer id){
        ReagentCatalog element = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            element = (ReagentCatalog)session.get(ReagentCatalog.class, id);
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

//    public static ArrayList<HashMap<String, Object>> readDocuments(Class<? extends Document> documentClass) {
//        try {
//            statmt = conn.createStatement();
//            resSet = statmt.executeQuery("SELECT * FROM reagentArrivalDocument");
//        }
//        catch (SQLException e) {
//            System.out.println(e.getErrorCode());
//        }
//
//        ArrayList<HashMap<String, Object>> documents = new ArrayList<HashMap<String, Object>>();
//
//        try {
//            while(resSet.next()) {
//                ArrayList<Class> classes = new ArrayList<Class>();
//                classes.add(documentClass);
//                classes.add(documentClass.getSuperclass());
//
//                for (Class catClass : classes) {
//                    for (Field catFld : catClass.getDeclaredFields()) {
////                        if (element.isServiceField(catFld.getName()))
////                            continue;
////                        String fldName = catFld.getName().toLowerCase();
////                        fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
////                        String fldValue = resSet.getString(fldName);
////                        Method setter = element.getClass().getMethod("set" + fldName, catFld.getType());
////                        Object castedValue = Lab_Helper.castValue(fldValue, catFld.getType());
////                        setter.invoke(element, castedValue);
//                    }
//                }
//            }
//        }
//        catch (SQLException e) {
//            //TODO handle exception
//            WindowManager.openErrorWindow(e.toString());
//        }
//
//        return documents;
//    }

//    public static void readCatalogElement(Catalog element) {
//        try {
//            statmt = conn.createStatement();
//            resSet = statmt.executeQuery("SELECT * FROM Reagents where uuid = '" + element.getUuid() + "'");
//            if (resSet.next()) {
//                ArrayList<Class> classes = new ArrayList<Class>();
//                classes.add(element.getClass());
//                classes.add(element.getClass().getSuperclass());
//
//                for (Class catClass : classes) {
//                    for (Field catFld : catClass.getDeclaredFields()) {
//                        if (element.isServiceField(catFld.getName()))
//                            continue;
//                        String fldName = catFld.getName().toLowerCase();
//                        fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
//                        String fldValue = resSet.getString(fldName);
//                        Method setter = element.getClass().getMethod("set" + fldName, catFld.getType());
//                        Object castedValue = Lab_Helper.castValue(fldValue, catFld.getType());
//                        setter.invoke(element, castedValue);
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//            errLog += e.getMessage();
//            //TODO handle exception
//            WindowManager.openErrorWindow(e.toString());
//        }
//    }

 //   public static void addReagentCatalogElement(Catalog element) {
//      //  insert into Reagents (ID, Name, Description) Values (Null, 'Super react', '')
//        String queryString = "Insert into Reagents (";
//        String fieldString = "";
//        boolean firstField = true;
//        ArrayList<Class> classes = new ArrayList<Class>();
//        classes.add(element.getClass());
//        classes.add(element.getClass().getSuperclass());
//
//        for (Class catClass : classes) {
//            for (Field catFld : catClass.getDeclaredFields()) {
//                if (element.isServiceField(catFld.getName()))
//                    continue;
//                if (!firstField) {
//                    queryString += ", ";
//                    fieldString += ", ";
//                }
//                boolean isStringType = catFld.getType().getName().equals("java.lang.String");
//                if (! isStringType)
//                    isStringType = catFld.getType().getName().equals("java.util.UUID");
//                String strSymbol = isStringType ? "'" : "";
//
//                String fldName = catFld.getName();
//                queryString += fldName;
//                fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
//                try {
//                    fieldString += strSymbol + element.getClass().getMethod("get" + fldName).invoke(element) + strSymbol;
//                } catch (NoSuchMethodException e) {
//                    //TODO Handle exception
//                    WindowManager.openErrorWindow(e.getMessage());
//                } catch (IllegalAccessException e) {
//                    //TODO Handle exception
//                    WindowManager.openErrorWindow(e.getMessage());
//                } catch (InvocationTargetException e) {
//                    //TODO Handle exception
//                    WindowManager.openErrorWindow(e.toString() + "; \n" + e.getTargetException().toString());
//                }
//                firstField = false;
//            }
//        }
//        queryString += ") Values (" + fieldString + ")";
//
//        try {
//            statmt.execute(queryString);
//        }
//        catch (Exception e) {
//            //TODO Handle exception
//        }
 //   }

//    public static void updateCatalogElement(Catalog element) {
//        //Update Reagents Set Name = 'test update', Description = 'e1e1e1e' where uuid = ''
//        String queryString = "Update " + element.getTableName() + " Set ";
//
//        boolean firstField = true;
//        ArrayList<Class> classes = new ArrayList<Class>();
//        classes.add(element.getClass());
//        classes.add(element.getClass().getSuperclass());
//
//        for (Class catClass : classes) {
//            for (Field catFld : catClass.getDeclaredFields()) {
//                if (element.isServiceField(catFld.getName()))
//                    continue;
//                if (!firstField) {
//                    queryString += ", ";
//                }
//                boolean isStringType = catFld.getType().getName().equals("java.lang.String");
//                if (! isStringType)
//                    isStringType = catFld.getType().getName().equals("java.util.UUID");
//                String strSymbol = isStringType ? "'" : "";
//
//                String fldName = catFld.getName();
//                fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
//                try {
//                    queryString += fldName + " = " + strSymbol + element.getClass().getMethod("get" + fldName).invoke(element) + strSymbol;
//                } catch (NoSuchMethodException e) {
//                    //TODO Handle exception
//                    WindowManager.openErrorWindow(e.getMessage());
//                } catch (IllegalAccessException e) {
//                    //TODO Handle exception
//                    WindowManager.openErrorWindow(e.getMessage());
//                } catch (InvocationTargetException e) {
//                    //TODO Handle exception
//                    WindowManager.openErrorWindow(e.toString() + "; \n" + e.getTargetException().toString());
//                }
//                firstField = false;
//            }
//        }
//        queryString += " where uuid = '" + element.getUuid() + "'";
//
//        try {
//            statmt.execute(queryString);
//        }
//        catch (Exception e) {
//            //TODO Handle exception
//        }
//    }

    public static void close() {
        factory.close();
    }
}
