package accaunt.lab.mvLab.com;

import accaunt.lab.mvLab.com.Catalogs.Reagents.ReagentCatalog;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DB_Helper {
    public static DB_Helper instace;

    private static Connection conn;
    private static Statement statmt;
    private static ResultSet resSet;

    private DB_Helper() {
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:base/labBase.s3db");
        }
        catch (SQLException e) {}
        catch (ClassNotFoundException e) {}

        //System.out.println("База Подключена!");
    }

    public static ArrayList<HashMap<String, Object>> ReadReagentCatalog()
    {
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

                HashMap<String, Object> catElement = new HashMap<String, Object>();
                catElement.put("id", id);
                catElement.put("name", name);
                catElement.put("description", description);

                catalog.add(catElement);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return catalog;
    }

    public static void addReagentCatalogElement(ReagentCatalog element) {
      //  insert into Reagents (ID, Name, Description) Values (Null, 'Super react', '')
        String queryString = "Insert into Reagents (";
        String fieldString = "";
        boolean firstField = true;

        for (Field catFld : element.getClass().getDeclaredFields()) {
            if  (! firstField) {
                queryString += ", ";
                fieldString += ", ";
            }
            boolean isStringType = catFld.getType().getName().equals("java.lang.String");
            String strSymbol = isStringType ? "'" : "";

            String fldName = catFld.getName();
            queryString += fldName;
            fldName = fldName.substring(0, 1).toUpperCase() + fldName.substring(1);
            try {
                fieldString += strSymbol + element.getClass().getMethod("get" + fldName).invoke(element) + strSymbol;
            }
            catch (NoSuchMethodException e) {
              //TODO Handle exception
            }
            catch (IllegalAccessException e) {
                //TODO Handle exception
            }
            catch (InvocationTargetException e) {
                //TODO Handle exception
            }
            firstField = false;
        }
        queryString += ") Values (" + fieldString + ")";
    }

    public static DB_Helper getInstace() {
        if (instace == null)
            instace = new DB_Helper();
        return instace;
    }
}
