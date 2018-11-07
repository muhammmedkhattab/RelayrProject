package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import bsh.ParseException;

public class API {
	public static List<String> titleList = new ArrayList<String>();


	public static void searchAPI(String Url) throws IOException, ParseException {
		
		URL url = new URL(Url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		JSONObject myResponse = new JSONObject(content.toString());
		for (int i = 0; i < 3; i++) {
			titleList.add(myResponse.getJSONArray("items").getJSONObject(i).getString("name"));
			// System.out.println(titleList.get(i));
		}

	}
}
