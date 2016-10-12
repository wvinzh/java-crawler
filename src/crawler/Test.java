package crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Test {	
	public static void main(String[] args){
		//DownLoadFile.downloadFile("http://www.chnxp.com.cn/soft/2016-03/26261.html#J_Reviews");
		String[] links = SeedsInjector.getLinksFromFile("F://seeds.txt");
//		System.out.println(links[0]);
//		System.out.println(links[1]);
		byte[] source = links[1].getBytes();
		System.out.println(MD5.getMD5(source));
		//MD5.getMD5(source);
	}

}
