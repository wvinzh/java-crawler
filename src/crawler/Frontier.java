package crawler;

public interface Frontier {
	public CrawUrl getNext() throws Exception;

	public boolean putUrl(CrawUrl url) throws Exception;
}
