package com.task_360t.cubes.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CubeLogger {

	private static final String LOG_FILE = "logFile.log";
	static CubeLogger loggerInstant;
	private Logger logger;
	
	private boolean enable;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * default ctor
	 */
	private CubeLogger() {
		setEnable(CONSTANTS.LOGGER_ENABLE);
		logger = Logger.getLogger(CONSTANTS.CUBES_LOGGER);
		if(CONSTANTS.LOGGER_ENABLE_FILE_LOGGING)
			enableFileLogging();
		for (Handler h : logger.getHandlers()) {
		    h.setLevel(Level.INFO);
		}
	}

	private void enableFileLogging() {
		try {  

	        // This block configure the logger with handler and formatter  
			FileHandler fh = new FileHandler(LOG_FILE);  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

	}

	/**
	 * static method that return the singleton object
	 * 
	 * @return singleton static object
	 */
	public static CubeLogger getInstant() {
		if (loggerInstant == null)
			loggerInstant = new CubeLogger();
		return loggerInstant;
	}

	/**
	 * log info
	 * 
	 * @param message message to log
	 */
	public void INFO(String message) {
		logger.info(message);
	}

	/**
	 * log error from error object
	 * 
	 * @param e error object
	 */
	public void ERROR(Exception e) {
		logger.severe(e.getMessage());
	}

	/**
	 * log error
	 * 
	 * @param message error message
	 */
	public void ERROR(String message) {
		logger.severe(message);
	}

	/**
	 * log a warning message
	 * 
	 * @param message warning message
	 */
	public void WARNING(String message) {
		logger.warning(message);
	}

}
