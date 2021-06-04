/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class sqlMain {

    public String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Backgammon;integratedSecurity=true";
    public ResultSet rs;

    public ResultSet whithResultSet(String sqlStatement, String[] PreparedStatement) {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ps.setEscapeProcessing(true);

            for (int i = 0; i < PreparedStatement.length; i++) {
                ps.setString(i + 1, PreparedStatement[i]);
            }

            return ps.executeQuery();
        } catch (ClassNotFoundException ex) {
            System.err.println("1");
            Logger.getLogger(sqlMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             System.err.println("2");
            Logger.getLogger(sqlMain.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    

        
            /*        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ps.setEscapeProcessing(true);
            
            for(int i =0; i< PreparedStatement.length;i++){
            ps.setString(i+1,PreparedStatement[i]);
            }
            
            return ps.executeQuery();
            }catch(Exception e){
            System.out.println("xxxxxxx");
            return null;
            
            }*/

}
/**
 
     public sqlMain(String a) throws SQLException {
        

        String SPsql = "EXEC new_player ?,?";   // for stored proc taking 2 parameters
        //  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(DB_URL);
        //  Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
        PreparedStatement ps = conn.prepareStatement(SPsql);
        ps.setEscapeProcessing(true);
        //  ps.setQueryTimeout(90);
        ps.setString(1, "bbb");
        ps.setString(2, "bbb");
        ResultSet rs = ps.executeQuery();
    }

    public sqlMain() throws SQLException, ClassNotFoundException {

        String SPsql = ""
                + "";   // for stored proc taking 2 parameters
      //  String SPsql = "select 1 as token ";
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       Connection conn = DriverManager.getConnection(DB_URL);
        //  Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
        PreparedStatement ps = conn.prepareStatement(SPsql);
        ps.setEscapeProcessing(true);
        //  ps.setQueryTimeout(90);
        ps.setString(1, "aaa");
        ps.setString(2, "aaa");
       // int rs = ps.executeUpdate();
      
        ResultSet rs = ps.executeQuery();
       if(rs.next()){
        System.out.println("\nrs work agin");
        System.out.print(rs.getInt("token") + " ");

       }
       
        
              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement statement;
        String query = "EXEC a1";
        statement = conn.createStatement();
        rs = statement.executeQuery(query);
        if (rs.next()) {
        int a = rs.getInt(1);
        System.out.print(a);
        }
        
        // close the resorces
        rs.close();
        
        conn.close();
}
    

    public sqlMain(int a) throws ClassNotFoundException {

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DB_URL);

            // declare of statement and resultSet
            Statement statement;

            // the statement to be execute
            String query = "select * from LogIn";

            statement = conn.createStatement();

            // execute and get the result set.
            rs = statement.executeQuery(query);

            // run over the resultSet
            while (rs.next()) {

                System.out.print(rs.getInt("ID") + " ");
                System.out.print(rs.getString("UserName") + " ");
                System.out.print(rs.getString("Password") + " ");
                System.out.println("");
            }

            // close the resorces
            rs.close();

            conn.close();

//Create Statment
//Execute
        } catch (SQLException ex) {
            Logger.getLogger(sqlMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Users() throws SQLException {
        String SPsql = "EXEC new_player ?,?";   // for stored proc taking 2 parameters
        //  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(DB_URL);
        //  Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
        PreparedStatement ps = conn.prepareStatement(SPsql);
        ps.setEscapeProcessing(true);
        //  ps.setQueryTimeout(90);
        ps.setString(1, "bbb");
        ps.setString(2, "bbb");
        ResultSet rs = ps.executeQuery();
        // return null;
    }
        public void Users() throws SQLException {
            String[]aa=new String[]{"aaa","aaa"};
        String SPsql = "declare @tmp int exec @tmp= login_P ?,? select @tmp as tmp";   // for stored proc taking 2 parameters
        //  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection(DB_URL);
        //  Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
        PreparedStatement ps = conn.prepareStatement(SPsql);
        ps.setEscapeProcessing(true);
        //  ps.setQueryTimeout(90);
        for(int i=0;i<2;i++){
         ps.setString(i+1, aa[i]);
        }
       // ps.setString(1, "bbb");
        //ps.setString(2, "bbb");
        ResultSet rs = ps.executeQuery();
            System.out.println(rs.next());
        // return null;
    }
 */
