package com.mvLab.lab.accaunt;

import com.mvLab.lab.accaunt.catalogs.Catalog;
import com.mvLab.lab.accaunt.documents.Document;

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

public class DB_Manager {
    public static DB_Manager instance;
    private static Connection conn;
    private static Statement statmt;
    private static ResultSet resSet;
    private static String errLog;

    private DB_Manager() {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:base/labBase.s3db");
        }
        catch (SQLException e) {
            WindowManager.openErrorWindow("SQL error");
        }
        catch (ClassNotFoundException e) {
            WindowManager.openErrorWindow("ClassNotFoundException");
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

    public static ArrayList<HashMap<String, Object>> ReadReagentCatalog() {
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM Reagents");
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        ArrayList<HashMap<String, Object>> catalog = new ArrayList<HashMap<String, Object>>();

        try {
            while(resSet.next())
            {
                int id = resSet.getInt("id");
                String  name = resSet.getString("name");
                String  description = resSet.getString("description");
                String  uuid = resSet.getString("uuid");

                HashMap<String, Object> catElement = new HashMap<String, Object>();
                catElement.put("id", id);
                catElement.put("name", name);
                catElement.put("description", description);
                catElement.put("uuid", uuid);

                catalog.add(catElement);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return catalog;
    }

    public static ArrayList<HashMap<String, Object>> readDocuments(Class<? extends Document> documentClass) {
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM reagentArrivalDocument");
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        ArrayList<HashMap<String, Object>> documents = new ArrayList<HashMap<String, Object>>();

        try {
            while(resSet.next()) {
                ArrayList<Class> classes = new ArrayList<Class>();
                classes.add(documentClass);
                classes.add(documentClass.getSuperclass());

                for (Class catClass : classes) {
                    for (Field catFld : catClass.getDeclaredFields()) {
//                        if (element.isServiceField(catFld.getName()))
//                            continue;
//                        String fldName = catFld.getName().toLowerCase();
//                        fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
//                        String fldValue = resSet.getString(fldName);
//                        Method setter = element.getClass().getMethod("set" + fldName, catFld.getType());
//                        Object castedValue = Lab_Helper.castValue(fldValue, catFld.getType());
//                        setter.invoke(element, castedValue);
                    }
                }
            }
        }
        catch (SQLException e) {
            //TODO handle exception
            WindowManager.openErrorWindow(e.toString());
        }

        return documents;
    }

    public static void readCatalogElement(Catalog element) {
        try {
            statmt = conn.createStatement();
            resSet = statmt.executeQuery("SELECT * FROM Reagents where uuid = '" + element.getUuid() + "'");
            if (resSet.next()) {
                ArrayList<Class> classes = new ArrayList<Class>();
                classes.add(element.getClass());
                classes.add(element.getClass().getSuperclass());

                for (Class catClass : classes) {
                    for (Field catFld : catClass.getDeclaredFields()) {
                        if (element.isServiceField(catFld.getName()))
                            continue;
                        String fldName = catFld.getName().toLowerCase();
                        fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
                        String fldValue = resSet.getString(fldName);
                        Method setter = element.getClass().getMethod("set" + fldName, catFld.getType());
                        Object castedValue = Lab_Helper.castValue(fldValue, catFld.getType());
                        setter.invoke(element, castedValue);
                    }
                }
            }
        }
        catch (Exception e) {
            errLog += e.getMessage();
            //TODO handle exception
            WindowManager.openErrorWindow(e.toString());
        }
    }

    public static void addReagentCatalogElement(Catalog element) {
      //  insert into Reagents (ID, Name, Description) Values (Null, 'Super react', '')
        String queryString = "Insert into Reagents (";
        String fieldString = "";
        boolean firstField = true;
        ArrayList<Class> classes = new ArrayList<Class>();
        classes.add(element.getClass());
        classes.add(element.getClass().getSuperclass());

        for (Class catClass : classes) {
            for (Field catFld : catClass.getDeclaredFields()) {
                if (element.isServiceField(catFld.getName()))
                    continue;
                if (!firstField) {
                    queryString += ", ";
                    fieldString += ", ";
                }
                boolean isStringType = catFld.getType().getName().equals("java.lang.String");
                if (! isStringType)
                    isStringType = catFld.getType().getName().equals("java.util.UUID");
                String strSymbol = isStringType ? "'" : "";

                String fldName = catFld.getName();
                queryString += fldName;
                fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
                try {
                    fieldString += strSymbol + element.getClass().getMethod("get" + fldName).invoke(element) + strSymbol;
                } catch (NoSuchMethodException e) {
                    //TODO Handle exception
                    WindowManager.openErrorWindow(e.getMessage());
                } catch (IllegalAccessException e) {
                    //TODO Handle exception
                    WindowManager.openErrorWindow(e.getMessage());
                } catch (InvocationTargetException e) {
                    //TODO Handle exception
                    WindowManager.openErrorWindow(e.toString() + "; \n" + e.getTargetException().toString());
                }
                firstField = false;
            }
        }
        queryString += ") Values (" + fieldString + ")";

        try {
            statmt.execute(queryString);
        }
        catch (Exception e) {
            //TODO Handle exception
        }
    }

    public static void updateCatalogElement(Catalog element) {
        //Update Reagents Set Name = 'test update', Description = 'e1e1e1e' where uuid = ''
        String queryString = "Update " + element.getTableName() + " Set ";

        boolean firstField = true;
        ArrayList<Class> classes = new ArrayList<Class>();
        classes.add(element.getClass());
        classes.add(element.getClass().getSuperclass());

        for (Class catClass : classes) {
            for (Field catFld : catClass.getDeclaredFields()) {
                if (element.isServiceField(catFld.getName()))
                    continue;
                if (!firstField) {
                    queryString += ", ";
                }
                boolean isStringType = catFld.getType().getName().equals("java.lang.String");
                if (! isStringType)
                    isStringType = catFld.getType().getName().equals("java.util.UUID");
                String strSymbol = isStringType ? "'" : "";

                String fldName = catFld.getName();
                fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
                try {
                    queryString += fldName + " = " + strSymbol + element.getClass().getMethod("get" + fldName).invoke(element) + strSymbol;
                } catch (NoSuchMethodException e) {
                    //TODO Handle exception
                    WindowManager.openErrorWindow(e.getMessage());
                } catch (IllegalAccessException e) {
                    //TODO Handle exception
                    WindowManager.openErrorWindow(e.getMessage());
                } catch (InvocationTargetException e) {
                    //TODO Handle exception
                    WindowManager.openErrorWindow(e.toString() + "; \n" + e.getTargetException().toString());
                }
                firstField = false;
            }
        }
        queryString += " where uuid = '" + element.getUuid() + "'";

        try {
            statmt.execute(queryString);
        }
        catch (Exception e) {
            //TODO Handle exception
        }
    }

    public static void close() {
        try {
            conn.close();
        }
        catch (SQLException e) {
//            TODO handle exception
        }
    }
}
