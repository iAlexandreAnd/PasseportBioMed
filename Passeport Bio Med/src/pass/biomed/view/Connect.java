package pass.biomed.view;

public class Connect {

		public static void main (String[] args) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver OK");
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
}
