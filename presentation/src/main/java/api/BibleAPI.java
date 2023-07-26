package api;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The BibleAPI class is intended to retrieve books, chapters and verses of the bible.
 * @author Matthew McCaughey
 *
 */
public class BibleAPI extends ApiCalls{
	
	/**
	 * Returns the text from the verse reference
	 * @param bookId The book ID of the book that the verse is in
	 * @param chapter The chapter that the verse is in
	 * @param verse The verse number to get the text from
	 * @return The text from the reference
	 */
	public static String getVerse(String bookId, int chapter, int verse)
	{
        return getVerseJson(bookId,chapter,verse).getString("text");
	}
	
	/**
	 * Returns the verses within a specific chapter of a book
	 * @param bookId The book ID of the book that the chapter is in
	 * @param chapter The chapter to get the verses from
	 * @return int array of verses in the chapter
	 */
	public static int[] getVerses(String bookId, int chapter) {
		int verseCount = getVersesJson(bookId, chapter).getJSONObject("data").getInt("verseCount");
		int[] verses = new int[verseCount];
		
		for(int i = 0; i < verseCount; i++) {
			verses[i] = i+1;
		}
		return verses;
	}
	
	/**
	 * Returns the chapters in a book
	 * @param bookId The book ID of the book that the chapters are in
	 * @return int array of chapters in a book
	 */
	public static int[] getChapters(String bookId) {
        JSONArray dataArray = getChaptersJson(bookId).getJSONArray("data");
        int[] chapters = new int[dataArray.length() - 1];

        for (int i = 1; i < dataArray.length(); i++) {
            JSONObject bookObject = dataArray.getJSONObject(i);
            chapters[i - 1] = Integer.parseInt(bookObject.getString("number"));
        }

        return chapters;
        }
    
    /**
     * Returns the book ID of a specific book
     * @param bookName The name of the book
     * @return The book ID of the specific book
     */
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
    
    /**
	 * Returns all books of the bible as their long names
	 * @return Long names of all books of the bible
	 */
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
    
	/**
	 * Returns names of all of the books of the bible
	 * @return Names of all the books of the bible
	 */
    public static String[] getBookNames() {
      
        JSONArray dataArray = getBooksJson().getJSONArray("data");
        String[] allBooks = new String[dataArray.length()];

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject bookObject = dataArray.getJSONObject(i);
            allBooks[i] = bookObject.getString("name");
        }

        return allBooks;
        }
}