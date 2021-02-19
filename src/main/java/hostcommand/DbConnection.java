package hostcommand;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dnl.utils.text.table.TextTable;
import hostcommand.RecordedPlay;

public class DbConnection {
	private static String url = "jdbc:mysql://192.249.114.226/sportoco_ds2";
	private static String username = "sportoco_dev";
	private static String password = "L[R,B[x(g4PC";
	private Connection conn;
	
	
	public void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			if (conn != null) {
				System.out.println("Connected to the database!");			
			} else {
				System.out.println("Database connection failed!");								
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			
		}
		
	}
	
	public ArrayList<RecordedPlay> getPlays() {
		ArrayList<RecordedPlay> arr = new ArrayList<RecordedPlay>();
		try {
			CallableStatement stmt = conn.prepareCall("{call sp_automation_read_recorded_plays(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, System.getProperty("gameId"));
			////stmt.setString(1, "fbncaa-lb01-71d070-gvd-01072021");
			ResultSet rs = stmt.executeQuery();
			
			int cnt = 0;
			int rowCount = 0;

			if (rs.last()) {
				rowCount = rs.getRow();
				rs.beforeFirst();
			}
			
			String[] cols = {"EVENT", "REF ID", "WAIT", "VALUE", "IS PREV ENDED"};
			Object[][] data = new Object[rowCount][5];

			while (rs.next()) {
				
				data[cnt][0] = rs.getString("event");
				data[cnt][1] = rs.getString("ref_id");
				data[cnt][2] = rs.getFloat("wait");
				data[cnt][3] = rs.getString("event_select_value");
				data[cnt][4] = rs.getBoolean("is_previous_play_ended");
				
				arr.add(new RecordedPlay(
							rs.getString("event"),
							rs.getString("ref_id"),
							rs.getFloat("wait"),
							rs.getString("event_select_value"),
							rs.getBoolean("is_previous_play_ended")
						));
				
				++cnt;
			}
										
			TextTable tt = new TextTable(cols, data);
			tt.setAddRowNumbering(true);
			tt.printTable();
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
	
}
