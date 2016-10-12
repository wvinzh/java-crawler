package berkeleydb;

public class BerkeleyDBUtilTest {
	public static void main(String[] args) {
		BerkeleyDBDemo dbUtil = new BerkeleyDBDemo("F://tmp");
		//写入
		for (int i = 0; i < 10; i++){
			dbUtil.writeToDatabase("第"+i+"个", "学生"+i, true);
			}
	}
}
