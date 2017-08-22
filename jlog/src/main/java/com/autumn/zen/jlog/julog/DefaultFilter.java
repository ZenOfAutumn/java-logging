package com.autumn.zen.jlog.julog;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class DefaultFilter implements Filter {

	public boolean isLoggable(LogRecord record) {
		if (record.getLevel().equals(Level.FINE)) {
			return false;
		}
		return true;
	}

}
