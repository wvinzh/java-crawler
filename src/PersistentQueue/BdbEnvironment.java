package PersistentQueue;

import java.io.File;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.EnvironmentLockedException;
import com.sleepycat.je.EnvironmentNotFoundException;
import com.sleepycat.je.VersionMismatchException;
import com.sleepycat.je.dbi.EnvironmentImpl;
import com.sleepycat.je.dbi.RepConfigProxy;

public class BdbEnvironment extends Environment {
	
	StoredClassCatalog classCatalog;   
    Database classCatalogDB; 

	public BdbEnvironment(File envHome, EnvironmentConfig configuration) throws EnvironmentNotFoundException,
			EnvironmentLockedException, VersionMismatchException, DatabaseException, IllegalArgumentException {
		super(envHome, configuration);
	}

	public BdbEnvironment(File envHome, EnvironmentConfig configuration, RepConfigProxy repConfigProxy,
			EnvironmentImpl envImplParam) {
		super(envHome, configuration, repConfigProxy, envImplParam);
	}

	public BdbEnvironment(File envHome, EnvironmentConfig envConfig, boolean openIfNeeded,
			RepConfigProxy repConfigProxy, EnvironmentImpl envImplParam) {
		super(envHome, envConfig, openIfNeeded, repConfigProxy, envImplParam);
	}
	 /** 
     * 返回StoredClassCatalog 
     * @return the cached class catalog 
     */  
    public StoredClassCatalog getClassCatalog() {  
        if(classCatalog == null) {  
            DatabaseConfig dbConfig = new DatabaseConfig();  
            dbConfig.setAllowCreate(true);  
            try {  
                classCatalogDB = openDatabase(null, "classCatalog", dbConfig);  
                classCatalog = new StoredClassCatalog(classCatalogDB);  
            } catch (DatabaseException e) {  
                // TODO Auto-generated catch block  
                throw new RuntimeException(e);  
            }  
        }  
        return classCatalog;  
    }  
  
    @Override  
    public synchronized void close() throws DatabaseException {  
        if(classCatalogDB!=null) {  
            classCatalogDB.close();  
        }  
        super.close();  
    }  

}
