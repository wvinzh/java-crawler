package crawler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;


/** 
 * @ClassName: SeedsInjector 
 * @Description: 
 * @author: zengh
 * @date: 2016年10月12日 下午7:05:30  
 */
public class SeedsInjector {

	/** 
	 * @Title: getLinksFromFile 
	 * @Description: 从文件中读取初始的url生成初始连接列表
	 * @param filepath
	 * @return
	 * @return: String[]
	 */
	public static String[] getLinksFromFile(String filepath) {
		
		Set<String> links = new HashSet<>();
		FileReader fileReader;
		try {
			fileReader = new FileReader(new File(filepath));
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String link = null;
			while ((link = bufferedReader.readLine()) != null) {
				 links.add(link);
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String[] slink =new String[0];
		return links.toArray(slink);
	}
}
