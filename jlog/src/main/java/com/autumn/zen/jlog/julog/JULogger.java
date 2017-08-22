package com.autumn.zen.jlog.julog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

public class JULogger {

	private static Logger LOG = Logger.getLogger(JULogger.class.getName(), "sun.util.logging.resources.logging_zh_TW");

	private static LogManager LOG_MANAGER = LogManager.getLogManager();

	static {

		InputStream is = null;
		try {
			is = JULogger.class.getClassLoader().getResourceAsStream("julog.properties");
			LOG_MANAGER.readConfiguration(is);

			// add handlers
			SocketHandler socketHandler = new SocketHandler();
			LOG.addHandler(socketHandler);

			ConsoleHandler consoleHandler = new ConsoleHandler();
			LOG.addHandler(consoleHandler);

			// set filter
			LOG.setFilter(new DefaultFilter());
			LOG_MANAGER.addLogger(LOG);
		} catch (SecurityException e) {
			LOG.severe(e.getMessage());
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}
	}

	static void log() {
		LOG.severe("servere log.");
		LOG.warning("warning log.");
		LOG.info("info log.");
		LOG.config("config log.");
		LOG.fine("fine log.");
		LOG.finer("finer log.");
		LOG.finest("finest log.");
	}

	static void listLogger() {
		Enumeration<String> loggers = LOG_MANAGER.getLoggerNames();
		while (loggers.hasMoreElements()) {
			String logName = loggers.nextElement();
			System.out.println(logName);
			Logger logger = LOG_MANAGER.getLogger(logName);
			for (Handler handler : logger.getHandlers()) {
				System.out.println(logName + ":" + handler.getClass().getName());
			}
		}
	}

	static void recurLogger(Logger logger) {
		Logger init = logger;
		do {
			disHandlers(init);
			init = init.getParent();
		} while (init != null);
	}

	static void disHandlers(Logger logger) {
		for (Handler handler : logger.getHandlers()) {
			System.out.println(logger.getName() + ":" + handler.getClass().getName());
		}
	}

	public static void main(String[] args) {
		log();

	}

}
