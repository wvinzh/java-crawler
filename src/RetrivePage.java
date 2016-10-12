import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class RetrivePage {	
	public static void main(String[] args) throws IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String path = "http://www.qq.com/";
		String filename = path.split("/")[2].toString();
		HttpGet httpget = new HttpGet(path);
		File file = new File("F://pages/"+filename+".txt");
		FileOutputStream out = new FileOutputStream(file); 
		System.out.println("executing request" + httpget.getRequestLine());  
		CloseableHttpResponse response = httpclient.execute(httpget);		
		try {
			// 获取响应实体    
            HttpEntity entity = response.getEntity();  
            System.out.println("--------------------------------------");  
            // 打印响应状态    
            System.out.println(response.getStatusLine());                                                                                                                                                                                                       							
            if (entity != null) {  
                // 打印响应内容长度    
                System.out.println("Response content length: " + entity.getContentLength());  
                
                // 打印响应内容    
                //System.out.println("Response content: " + EntityUtils.toString(entity)); 
                out.write(EntityUtils.toByteArray(entity));            
            }  
            System.out.println("------------------------------------"); 
		} finally {
		    response.close();
		}
	}

}
