package crawler;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DownLoadFile {
	//根据url和网页类型生成需要保存的文件名，去除url中飞文件名字符
	public static String getFilenNameByUrl(String url, String contentType) {
		//移除http
		url = url.substring(7);
		//txt/html类型
		if (contentType.indexOf("html") != -1) {
			url = url.replaceAll("[\\?/:*|<>*\"]", "_");
			return url+".txt";
		}else {
			return url.replaceAll("[\\?/:*|<>*\"]", "_")+"."+
					contentType.substring(contentType.lastIndexOf("/")+1);
		}
	}
	private static void saveToLocal(byte[] data, String filepath) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(filepath));
			fileOutputStream.write(data);
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//下载url指向的网页
	public static String downloadFile(String url) {
		String filepath = null;
		//生成http客户端对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//设置连接超时
	
		//定义get方法
		HttpGet httpget = new HttpGet(url);
		//执行get请求
	try {
		CloseableHttpResponse response = httpclient.execute(httpget);
		// 获取响应实体    
        HttpEntity entity = response.getEntity();  
        System.out.println("--------------------------------------");  
        // 打印响应状态    
        System.out.println(response.getStatusLine());                                                                                                                                                                                                       							
        if (entity != null) {  
            // 打印响应内容长度    
            System.out.println("Response content length: " + entity.getContentLength());             
            // 打印响应内容    
            byte[] responseBody = EntityUtils.toByteArray(entity);
            filepath = "F://pages/"+getFilenNameByUrl(url, 
            		response.getHeaders("Content-Type")[0].getValue());
            saveToLocal(responseBody, filepath);
        }  
        System.out.println("------------------------------------");
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		httpget.releaseConnection();
	}
	return filepath;
	}
}
