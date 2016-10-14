package crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import PersistentQueue.BdbPersistentQueue;

public class Test {
	public static void main(String[] args) {
		// DownLoadFile.downloadFile("http://www.chnxp.com.cn/soft/2016-03/26261.html#J_Reviews");
		String[] links = SeedsInjector.getLinksFromFile("F://seeds.txt");
		BDBFrontier bdbFrontier = new BDBFrontier("F://BDB/tmp");
		BdbPersistentQueue<String> bdbPersistentQueue = 
				new BdbPersistentQueue<>("F://BDB/tmp", "pq", String.class);
		// System.out.println(links[0]);
		// System.out.println(links[1]);
		// byte[] source = links[1].getBytes();
		// System.out.println(MD5.getMD5(source));
		// MD5.getMD5(source);
//		for (int i = 0; i < links.length; i++) {
//			String string = links[i];
//			CrawUrl crawUrl = new CrawUrl();
//			crawUrl.setUrl(string);
//			try {
//				bdbFrontier.putUrl(crawUrl);
//				CrawUrl url;
//				// 读取
//				while ((url = bdbFrontier.getNext()) != null) {
//					System.out.println(url.getUrl());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		CrawUrl url;int i = 1;
//		bdbPersistentQueue.iterator().hasNext();
		Iterator iterator =  bdbPersistentQueue.iterator();
		// 读取
		while (bdbPersistentQueue.iterator().hasNext()) {
			String string  = (String)iterator.next();
			System.out.println((i++)+"::"+string);
			
		}
//		try {
//			while ((url = bdbFrontier.getNext()) != null) {
//				System.out.println((i++)+"::"+url.getUrl());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
