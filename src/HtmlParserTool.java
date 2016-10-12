import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserTool {
	public static Set<String> getAllLinks(String url, LinkFilter filter) {
		Set<String> linkSet = new HashSet<>();
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			Elements media = doc.select("[src]"); 
	        System.out.println("\nLinks:"+links.size());
	        for (Element link : links) {
	            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
	        	String linkString = link.attr("abs:href");
	        	if (filter.accept(linkString)) {
	        		linkSet.add(linkString);
				}	            
	        }
	        for (Element src : media) {
	            if (src.tagName().equals("img")){
	            	String imgs = src.absUrl("abs:src");
	            	if (filter.accept(imgs)) {
	            		linkSet.add(imgs);
					}
	            }
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
        return linkSet;
	}
}
