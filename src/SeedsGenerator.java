import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class SeedsGenerator {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] slink =new String[0];
		return links.toArray(slink);
	}
}
