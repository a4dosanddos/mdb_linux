import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;

public class JackcessSample {
	public static void main(String[] args) {

		try {
			// mdb ファイルの作成
			Database db = Database.create(new File("sample.mdb"));

			// テーブルを作成
			Table newTable = new TableBuilder("NewTable")
					.addColumn(
							new ColumnBuilder("col1").setSQLType(Types.INTEGER)
									.toColumn())
					.addColumn(
							new ColumnBuilder("col2").setSQLType(Types.VARCHAR)
									.toColumn()).toTable(db);

			// データを登録
			newTable.addRow(1, "bar");
			newTable.addRow(2, "foo");

			// データの取得
			for (Map<String, Object> map : newTable) {
				System.out.println("col1 : " + map.get("col1") + " col2 : "
						+ map.get("col2"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
