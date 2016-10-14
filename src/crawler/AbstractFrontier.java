package crawler;

import java.io.File;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.dbi.EnvConfigObserver;

/**
 * @ClassName: AbstractFrontier
 * @Description: 封装对BDB的操作
 * @author: zengh
 * @date: 2016年10月13日 下午5:12:46
 */
public abstract class AbstractFrontier {

	private Environment env;
	private static final String CLASS_CATLOG = "java_class_catalog";
	protected StoredClassCatalog javaCatalog;
	protected Database catalogdatabase;
	protected Database database;

	/**
	 * @Title:AbstractFrontier
	 * @Description:TODO
	 */
	public AbstractFrontier(String homeDirectory) throws DatabaseException {
		System.out.println("Opening env in:" + homeDirectory);
		// 打开env
		EnvironmentConfig environmentConfig = new EnvironmentConfig();
		environmentConfig.setTransactional(true);
		environmentConfig.setAllowCreate(true);
		env = new Environment(new File(homeDirectory), environmentConfig);
		// 设置database config
		DatabaseConfig databaseConfig = new DatabaseConfig();
		databaseConfig.setTransactional(true);
		databaseConfig.setAllowCreate(true);
		// 打开database
		database = env.openDatabase(null, "URL", databaseConfig);
		// 打开catalogdatabase
		catalogdatabase = env.openDatabase(null, CLASS_CATLOG, databaseConfig);
		javaCatalog = new StoredClassCatalog(catalogdatabase);
	}

	public void close() throws DatabaseException {
		database.close();
		javaCatalog.close();
		env.close();
	}

	protected abstract void put(String key, CrawUrl value);

	protected abstract Object get(Object key);

	protected abstract void delete(Object key);

}
