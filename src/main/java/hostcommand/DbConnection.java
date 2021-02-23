package hostcommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import dnl.utils.text.table.TextTable;

public class DbConnection {
	
	public ArrayList<RecordedPlay> getPlays() {
		URL baseUrl;
		try {
			baseUrl = new URL("http://sportocotoday.com:6604/automation/recorded_plays?game_id=" + System.getProperty("gameId"));
						
			//aim high
			//url = new URL("http://sportocotoday.com:6604/automation/recorded_plays?game_id=fbncaa-lb01-47a0ca-avh-01062021");
			//koala bear
			//url = new URL("http://sportocotoday.com:6604/automation/recorded_plays?game_id=fbncaa-lb01-35eb43-bvk-01122021");
			
			HttpURLConnection conn;
			conn = (HttpURLConnection) baseUrl.openConnection();
			conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");
	        
	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
	        }
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            StringBuffer content = new StringBuffer();
            while ((output = br.readLine()) != null) {
            	content.append(output);
            }
            conn.disconnect();

            
    		ArrayList<RecordedPlay> recordedPlays = new ArrayList<RecordedPlay>();
            JSONArray jsonArray = new JSONArray(content.toString());
			int rowCount = jsonArray.length();
			String[] cols = {"EVENT", "REF ID", "WAIT", "VALUE", "IS PREV ENDED"};
			Object[][] data = new Object[rowCount][5];

			for (int i=0; i<jsonArray.length(); i++) {
	        	JSONObject rs = jsonArray.getJSONObject(i);
	        	
				data[i][0] = rs.getString("event");
				data[i][1] = rs.getString("ref_id");
				data[i][2] = rs.getFloat("wait");
				data[i][3] = rs.isNull("event_select_value") ? "" : rs.getString("event_select_value");
				data[i][4] = rs.isNull("is_previous_play_ended") ? false : rs.getInt("is_previous_play_ended") == 1 ? true : false;	       
				
				recordedPlays.add(new RecordedPlay(
								rs.getString("event"),
								rs.getString("ref_id"),
								rs.getFloat("wait"),
								rs.isNull("event_select_value") ? "" : rs.getString("event_select_value"),
								rs.isNull("is_previous_play_ended") ? false : rs.getInt("is_previous_play_ended") == 1 ? true : false
							));

			}
                       
			TextTable tt = new TextTable(cols, data);
			tt.setAddRowNumbering(true);
			tt.printTable();
			
			return recordedPlays;
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
		
}
