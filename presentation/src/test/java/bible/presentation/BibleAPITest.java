package bible.presentation;


	import org.testng.annotations.Test;
	import junit.framework.Assert;

	public class BibleAPITest {
		
		@Test 
		public void getChaptersAmount() {
			Assert.assertEquals(50, BibleAPI.getChapters("GEN").length);
			Assert.assertEquals(22, BibleAPI.getChapters("REV").length);
		}
		
		@Test
		public void getChaptersNumber() {
			Assert.assertEquals("1", BibleAPI.getChapters("GEN")[0]);
			Assert.assertEquals("50", BibleAPI.getChapters("GEN")[49]);
		}

		@Test
		public void sixtySixGetBookNames() {
			Assert.assertEquals(66, BibleAPI.getBookNames().length);
		}
		
		@Test
		public void getBookNamesCheck() {
			Assert.assertEquals("Genesis", BibleAPI.getBookNames()[0]);
			Assert.assertEquals("Revelation", BibleAPI.getBookNames()[65]);
		}
		
		@Test
		public void sixtySixGetBookIds() {
			Assert.assertEquals(66, BibleAPI.getBookIDs().length);
		}
		
		@Test
		public void getBookIDsCheck() {
			Assert.assertEquals("GEN", BibleAPI.getBookIDs()[0]);
			Assert.assertEquals("REV", BibleAPI.getBookIDs()[65]);
		}
		
		@Test
		public void sixtySixGetBookLongNames(){
			Assert.assertEquals(66, BibleAPI.getBookLongNames().length);
		}
		
		@Test
		public void getBookLongNamesCheck() {
			Assert.assertEquals("The First Book of Moses, called Genesis", BibleAPI.getBookLongNames()[0]);
			Assert.assertEquals("THE REVELATION OF ST. JOHN THE DIVINE", BibleAPI.getBookLongNames()[65]);
		}
		
}