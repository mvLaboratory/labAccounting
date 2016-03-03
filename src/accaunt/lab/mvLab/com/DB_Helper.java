package accaunt.lab.mvLab.com;

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

    public static DB_Helper getInstace() {
        if (instace == null)
            instace = new DB_Helper();
        return instace;
    }
}
