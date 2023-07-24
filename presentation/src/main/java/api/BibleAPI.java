package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class BibleAPI {

	private static final String API_KEY = "db1951b2ccc58f601dbf816e7c27723e";
	
	public static void main(String[] args) {
		System.out.println(getVerse("GEN", 1, 1));
	}
	
	public static int[] getChapters(String bookId) {
        JSONArray dataArray = getChaptersJson(bookId).getJSONArray("data");
        int[] chapters = new int[dataArray.length() - 1];

        for (int i = 1; i < dataArray.length(); i++) {
            JSONObject bookObject = dataArray.getJSONObject(i);
            chapters[i - 1] = Integer.parseInt(bookObject.getString("number"));
        }

        return chapters;
        }
	
	public static String getVerse(String bookId, int chapter, int verse)
	{
		JSONObject dataArray = getVerseJson(bookId, chapter, verse).getJSONObject("data");
		
        return extractVerseText(dataArray.getString("content"));
	}
	
	public static int getVerseCount(String bookId, int chapter)
	{
		JSONObject dataArray = getVersesJson(bookId, chapter).getJSONObject("data");
        return dataArray.getInt("verseCount");
	}
	
	public static int[] getVerses(String bookId, int chapter) {
		int verseCount = getVerseCount(bookId, chapter);
		int[] verses = new int[verseCount];
		
		for(int i = 0; i < verseCount; i++) {
			verses[i] = i+1;
		}
		return verses;
	}
	
	public static String[] getBookLongNames()
	{
		JSONArray dataArray = getBooksJson().getJSONArray("data");
        String[] allBooks = new String[dataArray.length()];

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject bookObject = dataArray.getJSONObject(i);
            allBooks[i] = bookObject.getString("nameLong");
        }

        return allBooks;
	}
	
    public static String[] getBookNames() {
      
        JSONArray dataArray = getBooksJson().getJSONArray("data");
        String[] allBooks = new String[dataArray.length()];

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject bookObject = dataArray.getJSONObject(i);
            allBooks[i] = bookObject.getString("name");
        }

        return allBooks;
        }
    
    public static String getBookID(String bookName) {
        
        JSONArray dataArray = getBooksJson().getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject bookObject = dataArray.getJSONObject(i);
            String name = bookObject.getString("name");

            // If the "name" field matches the given bookName, return the corresponding "id".
            if (name.equals(bookName)) {
                return bookObject.getString("id");
            }
        }
        return null;
        }
    
    private static JSONObject getChaptersJson(String bookId) {
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
    
    private static JSONObject getBooksJson() {
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
    
    private static JSONObject getVersesJson(String bookId, int chapter) {
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
    
    private static JSONObject getVerseJson(String bookId, int chapter, int verse) {
    	try {
    		URL url = new URL("https://api.scripture.api.bible/v1/bibles/de4e12af7f28f599-02/verses/" + bookId + "." + Integer.toString(chapter) + "." + Integer.toString(verse));
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
    
    public static String extractVerseText(String response) {
        // Find the index of the closing </span> tag and the closing </p> tag.
        int endIndexSpan = response.indexOf("</span>");
        int endIndexP = response.indexOf("</p>");

        // Extract the verse text from the response using substring.
        String verseText = response.substring(endIndexSpan + 7, endIndexP).trim();

        // Remove any remaining HTML tags from the verse text using regular expressions.
        Pattern htmlPattern = Pattern.compile("<[^>]*>");
        Matcher matcher = htmlPattern.matcher(verseText);
        verseText = matcher.replaceAll("");

        return verseText;
    }
}