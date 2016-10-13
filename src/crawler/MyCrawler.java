package crawler;

import java.util.Set;

/**
 * @ClassName: MyCrawler
 * @Description: 爬虫类
 * @author: zengh
 * @date: 2016年10月12日 下午7:12:19
 */
public class MyCrawler {

	BDBFrontier bdbFrontier;//

	public MyCrawler() {
		bdbFrontier = new BDBFrontier("F://BDB/tmp");
	}

	// 使用种子初始化url队列
	private void initCrawlerWithSeeds(String[] seeds) {
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}

	// 抓取过程
	public void crawling(String[] seeds) throws Exception {
		// 定义过滤器，提取以http://www.qq.com开头的
		LinkFilter filter = new LinkFilter() {
			@Override
			public boolean accept(String url) {
				if (url.contains(".")) {
					return true;
				}
				return false;
			}
		};
		// 初始化队列
		initCrawlerWithSeeds(seeds);
		// 循环抓取，列表为空或者抓取100
		while (!LinkQueue.unVisitedUrlsEmpty() && LinkQueue.getVisitedUrlNum() <= 100) {
			String visitUrl = LinkQueue.unVisitedUrlDeQueue();
			if (visitUrl == null) {
				continue;
			}
			DownLoadFile downLoader = new DownLoadFile();
			// 下载网页
			downLoader.downloadFile(visitUrl);
			// 该url放入已访问的url中
			LinkQueue.addVisitedUrl(visitUrl);
			// 提取出下载网页中的URL
			Set<String> links = HtmlParserTool.getAllLinks(visitUrl, filter);
			// 新的未访问的url入队
			for (String link : links) {
				LinkQueue.addUnvisitedUrl(link);
				CrawUrl crawUrl = new CrawUrl();
				crawUrl.setUrl(link);
				bdbFrontier.putUrl(crawUrl);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(SeedsInjector.getLinksFromFile("F://seeds.txt"));
	}

}
