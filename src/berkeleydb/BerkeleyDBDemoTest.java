package berkeleydb;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BerkeleyDBDemoTest {
	
	private BerkeleyDBDemo dbUtil = null;
	@BeforeClass  
    public static void setUpBeforeClass() throws Exception {  
		
        System.out.println("in BeforeClass================");  
        
    }  
  
    @AfterClass  
    public static void tearDownAfterClass() throws Exception {  
        System.out.println("in AfterClass=================");  
    }  
	
	@Before
	public void setup() {
		System.out.println("before =============");
		dbUtil = new BerkeleyDBDemo("F:/tmp");
	} 
	@After
	public void teardown() {
		// TODO Auto-generated method stub
		System.out.println("after =============");
	}

	@Test
	public void testWriteToDatabase() {
		for (int i = 0; i < 10; i++){
		dbUtil.writeToDatabase(i+"", "学生"+i, true);
		}
	}

	@Test
	public void testReadFromDatabase() {
		String value = dbUtil.readFromDatabase("2");
		assertEquals(value, "学生2");
	}

	@Test
	public void testGetEveryItem() {
		int size = dbUtil.getEveryItem().size();
		assertEquals(size, 10);
	}

//	@Test
//	public void testDeleteFromDatabase() {
//		dbUtil.deleteFromDatabase("4");
//		assertEquals(9, dbUtil.getEveryItem().size());
//	}
//	
	public void cleanup() {
		dbUtil.closeDB();
	}

}
