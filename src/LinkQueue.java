import java.util.HashSet;
import java.util.Set;

public class LinkQueue {
	//已经访问的url集合
	private static Set<String> visitedUrl = new HashSet<>();
	//待访问的url集合
	private static Queue unVisitedUrl = new Queue();
	//获得url列表
	public static Queue getUnVisitedUrl() {
		return unVisitedUrl;
	}
	//添加到访问过的url列表中
	public static void  addVisitedUrl(String url) {
		visitedUrl.add(url);
	}
	//移除访问过的URL
	public static void  removeVisitedUrl(String url) {
		visitedUrl.remove(url);
	}
	//未访问的url出队列
	public static String unVisitedUrlDeQueue() {
		return unVisitedUrl.deQueue();
	}
	//保证每个url只被访问一次
	public static void addUnvisitedUrl(String url) {
		if (url != null && !url.trim().equals("") && 
				!visitedUrl.contains(url) && !unVisitedUrl.contains(url)) {
			unVisitedUrl.enQueue(url);
		}
	}
	//获得已经访问的url数目
	public static int getVisitedUrlNum() {
		return visitedUrl.size();
	}
	//判断为访问过的url列表是否为空
	public static boolean unVisitedUrlsEmpty() {
		return unVisitedUrl.isQueueEmpty();
	}
}
