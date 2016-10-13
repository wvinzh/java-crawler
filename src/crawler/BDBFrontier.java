/**   
 * Copyright © 2016 公司名. All rights reserved.
 * 
 * @Title: BDBFrontier.java 
 * @Prject: HttpClientDemo
 * @Package: crawler 
 * @Description: TODO
 * @author: zengh   
 * @date: 2016年10月13日 下午5:28:25 
 * @version: V1.0   
 */
package crawler;

import java.util.Map.Entry;
import java.util.Set;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;

/**
 * @ClassName: BDBFrontier
 * @Description: 实现了基于BDB的URL表
 * @author: zengh
 * @date: 2016年10月13日 下午5:28:25
 */
public class BDBFrontier extends AbstractFrontier implements Frontier {

	private StoredMap<Object, Object> pendingUrisDB = null;

	public BDBFrontier(String homeDirectory) throws DatabaseException {
		super(homeDirectory);
		// 键值绑定
		EntryBinding keyBinding = new SerialBinding<>(javaCatalog, String.class);
		EntryBinding valueBinding = new SerialBinding<>(javaCatalog, CrawUrl.class);

		pendingUrisDB = new StoredMap<>(database, keyBinding, valueBinding, true);
	}

	@Override
	protected void put(Object key, Object value) {
		pendingUrisDB.put(key, value);
	}

	@Override
	protected Object get(Object key) {
		return pendingUrisDB.get(key);
	}

	@Override
	protected void delete(Object key) {
		pendingUrisDB.remove(key);
	}

	@Override
	public CrawUrl getNext() throws Exception {
		CrawUrl result = null;
		if (!pendingUrisDB.isEmpty()) {
			Set entrys = pendingUrisDB.entrySet();
			//System.out.println(entrys);
			Entry entry = pendingUrisDB.entrySet().iterator().next();
			result = (CrawUrl) entry.getValue();
			delete(entry.getKey());
		}
		return result;
	}

	@Override
	public boolean putUrl(CrawUrl url) throws Exception {
		put(url.getUrl(), url);
		return true;
	}

	private String caculateMD5(String Url) {
		return MD5.getMD5(Url.getBytes());
	}

}
