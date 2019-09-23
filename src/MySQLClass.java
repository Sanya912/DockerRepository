import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class MySQLClass {

    public static boolean executeDBScripts(String aSQLScriptFilePath, Statement stmt) throws IOException,SQLException {
        boolean isScriptExecuted = false;
        try {
            BufferedReader in = new BufferedReader(new FileReader(aSQLScriptFilePath));
            String str;
            StringBuffer sb = new StringBuffer();
            while ((str = in.readLine()) != null) {
                sb.append(str + "\n ");
                stmt.execute(str.toString());
                System.out.println(str.toString());
            }
            isScriptExecuted = true;
        } catch (Exception e) {
            System.err.println("Failed to Execute" + aSQLScriptFilePath +". The error is"+ e.getMessage());
        }
        return isScriptExecuted;
    }

    public static void main(String args[]){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db","root","example");
            Statement stmt=con.createStatement();
            executeDBScripts("C:\\!!!Automation\\DockCompSQL\\mys.sql", stmt);
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}