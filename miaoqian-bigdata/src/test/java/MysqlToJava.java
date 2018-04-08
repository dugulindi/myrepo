import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;


public class MysqlToJava {
	@Test
	public void test() {
		Connection conn = null;
        String url = "jdbc:mysql://172.17.11.75:3306/db_sqtrans?user=read_only&password=123456&useUnicode=true&characterEncoding=UTF8";
        String table = "cs_invest_b";
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(url);
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getColumns(conn.getCatalog(), "%", table, "%"); 
            while (rs.next()) {
            	String columnName = rs.getString("COLUMN_NAME"); 
            	String columnType = rs.getString("TYPE_NAME"); 
            	int datasize = rs.getInt("COLUMN_SIZE"); 
            	int digits = rs.getInt("DECIMAL_DIGITS"); 
            	System.out.println(columnName+" "+columnType+" "+datasize+" "+digits); 
			}
            System.out.println(123);
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
