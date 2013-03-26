import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class StelsMDBSample {
	public static void main(String[] args) {

		try {
			Class.forName("jstels.jdbc.mdb.MDBDriver2");

			Properties prop = new Properties();
			prop.put("create", "true");
			prop.put("format", "access2010");

			// ex) jdbc:jstels:mdb:/usr/local/temp/sample.mdb
			// あらかじめファイルの作成は行なっておく
			Connection con = DriverManager.getConnection(
					"jdbc:jstels:mdb:[ mdb ファイルの絶対パス ]",
					prop);
			Statement st = con.createStatement();

			st.execute("CREATE TABLE mdb_sample (id int, name varchar2(50))");

			st.execute("INSERT INTO mdb_sample values (1, 'bar')");
			st.execute("INSERT INTO mdb_sample values (2, 'foo')");

			ResultSet rs = st.executeQuery("SELECT * FROM mdb_sample");

			while (rs.next()) {
				System.out.println("col1 : " + rs.getInt(1) + " col2 : "
						+ rs.getString(2));
			}

			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
