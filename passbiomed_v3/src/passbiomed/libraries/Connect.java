package passbiomed.libraries;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Connect {

		public static int ConnectDataBase (String loginField, String passwordField) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver OK");
				
				String url = "jdbc:mysql://localhost:3306/med_file_new_v1";
				String user = "root";
				String password = "Wxcvbn123*";
				
				Connection connect = (Connection) DriverManager.getConnection(url, user, password);
				
				System.out.println("Connexion effective");
				
				Statement state = (Statement) connect.createStatement();
				ResultSet result = state.executeQuery("SELECT * FROM Login");
				ResultSetMetaData resultMeta = (ResultSetMetaData) result.getMetaData();
				
				System.out.println("\n BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				
				for(int i = 1; i<= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t*");
				
				System.out.println("\n BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
				
				while(result.next()) {
					for(int i = 1; i<=resultMeta.getColumnCount(); i++)
					{
						System.out.print("\t" + result.getObject(i).toString() + "\t");
					}
						
					System.out.println("\n -------------------------------------------");
				}
				
				result.close();
				state.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return 0;
		}
}
