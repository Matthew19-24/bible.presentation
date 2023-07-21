package bible.presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class BibleAPI {

	private static final String API_KEY = "db1951b2ccc58f601dbf816e7c27723e";
	
	public static void main(String[] args) {
		getBooks();
	}
	
	
    public static String[] getBooks() {
        try {
            URL url = new URL("https://api.scripture.api.bible/v1/bibles/de4e12af7f28f599-02/books");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api-key", API_KEY);
            System.out.println("Test");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                System.out.println(response.toString()); 
               
                // Parse the JSON response to extract book names
                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONArray dataArray = jsonResponse.getJSONArray("data");
                String[] allBooks = new String[dataArray.length()];

                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject bookObject = dataArray.getJSONObject(i);
                    allBooks[i] = bookObject.getString("name");
                }

                return allBooks;
                
                } else {
                System.out.println("HTTP request failed: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        }
    
}