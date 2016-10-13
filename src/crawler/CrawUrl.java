package crawler;

import java.io.Serializable;

/**
 * @ClassName: CrawUrl
 * @Description: 封装用于存储的URl类
 * @author: zengh
 * @date: 2016年10月13日 下午5:00:14
 */
public class CrawUrl implements Serializable {

	public CrawUrl() {

	}

	private String url;//

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
