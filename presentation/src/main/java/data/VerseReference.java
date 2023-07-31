package data;

/**
 * Verse reference class holds a bible verse reference.
 * @author Matthew McCaughey
 *
 */
public class VerseReference {
	
	/**
	 * The book the reference is in.
	 */
	private String book;
	
	/**
	 * The chapter the reference is in.
	 */
	private int chapter;
	
	/**
	 * The verse the reference is in.
	 */
	private int verse;
	
	/**
	 * Retruns the book the reference is in.
	 * @return the book the reference is in.
	 */
	public String getBook() {
		return book;
	}
	
	/**
	 * Returns the chapter the reference is in.
	 * @return the chapter the reference is in.
	 */
	public int getChapter() {
		return chapter;
	}
	
	/**
	 * Returns the verse the reference is in.
	 * @return the verse the reference is in.
	 */
	public int getVerse() {
		return verse;
	}
	
	/**
	 * Sets the book the reference is in.
	 * @param the book the reference is in.
	 */
	public void setBook(String book) {
		this.book = book;
	}
	
	/**
	 * Sets the chatper the reference is in.
	 * @param the chapter the reference is in.
	 */
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	
	/**
	 * Sets the verse the reference is in.
	 * @param the verse the reference is in.
	 */
	public void setVerse(int verse) {
		this.verse = verse;
	}

}
