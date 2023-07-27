package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * The ApiCalls class is intended to make the API calls to retrieve the returned content directly from API.Bible.
 * @author Matthew McCaughey
 *
 */
public class ApiCalls {
	
	/**
	 * The API Key for API.Bible
	 */
	private static final String API_KEY = "db1951b2ccc58f601dbf816e7c27723e";
	
	/**
	 * Gets the Json response from bible-api.com that contains the specific bible verse text within it.
	 * @param bookId The book ID of the book that the verse is in
	 * @param chapter The chapter that the verse is in
	 * @param verse The verse number to get the text from
	 * @return The bible verses Json response
	 */
	protected static JSONObject getVerseJson(String bookId, int chapter, int verse) {
    	try {
    		URL url = new URL("https://bible-api.com/" + bookId + " " + Integer.toString(chapter) + ":" + Integer.toString(verse) + "?translation=kjv");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JSONObject jsonResponse = new JSONObject(response.toString());

                return jsonResponse;
                
                } else {
                System.out.println("HTTP request failed: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    	
    }
	
	/**
	 * Retruns the Chapters that are contained within the specific book
	 * @param bookId The book ID of the book that the chapters are in
	 * @return The Json response that contains the chapters
	 */
	protected static JSONObject getChaptersJson(String bookId) {
    	try {
            URL url = new URL("https://api.scripture.api.bible/v1/bibles/de4e12af7f28f599-02/books/" + bookId + "/chapters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api-key", API_KEY);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JSONObject jsonResponse = new JSONObject(response.toString());

                return jsonResponse;
                
                } else {
                System.out.println("HTTP request failed: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
	/**
	 * Returns all 66 book titles of the bible
	 * @return The Json response containing all 66 books of the bible
	 */
    protected static JSONObject getBooksJson() {
    	try {
            URL url = new URL("https://api.scripture.api.bible/v1/bibles/de4e12af7f28f599-02/books");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api-key", API_KEY);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JSONObject jsonResponse = new JSONObject(response.toString());

                return jsonResponse;
                
                } else {
                System.out.println("HTTP request failed: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Returns all verse numbers that are contained in the specified book & chapter
     * @param bookId The book ID of the book that the verses are in
     * @param chapter The chapter that the verses are in
     * @return The Json response that contains the verses
     */
    protected static JSONObject getVersesJson(String bookId, int chapter) {
    	try {
    		URL url = new URL("https://api.scripture.api.bible/v1/bibles/de4e12af7f28f599-02/chapters/" + bookId + "." + Integer.toString(chapter));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api-key", API_KEY);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                JSONObject jsonResponse = new JSONObject(response.toString());

                return jsonResponse;
                
                } else {
                System.out.println("HTTP request failed: " + responseCode);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
