package org.wn.jmx;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.wn.config.SpringConfig;

@ManagedResource
public class DataBaseMonitor {

    @ManagedOperation(description = "Start HSQL DatabaseManagerSwing.")
    public void dbManager() {
        String connectionURL = "jdbc:hsqldb:mem:" + SpringConfig.DATABASE_NAME;
    	String[] args = {"--url", connectionURL, "--noexit"};
        DatabaseManagerSwing.main(args);
    }
}
