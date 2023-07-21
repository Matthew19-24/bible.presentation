package bible.presentation;


	import org.testng.annotations.Test;
	import junit.framework.Assert;

	public class BibleAPITest {

		@Test
		public void sixtySixBooks() {
			Assert.assertEquals(66, BibleAPI.getBooks().length);
		}
		
		@Test
		public void bookCheck() {
			Assert.assertEquals("Genesis", BibleAPI.getBooks()[0]);
			Assert.assertEquals("Revelation", BibleAPI.getBooks()[65]);
		}
		
}