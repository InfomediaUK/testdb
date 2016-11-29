package com.helmet.application;

import java.util.HashMap;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class BookingLockHandler {
	
	private static BookingLockHandler bookingLockHandler;

	protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

	private HashMap lockMap;
	
	public static BookingLockHandler getInstance() {
		if (bookingLockHandler == null) {
			// NOT instantiated yet so create it.
			synchronized (BookingLockHandler.class) {
				// Only ONE thread at a time here!
				if (bookingLockHandler == null) {
					bookingLockHandler = new BookingLockHandler();
				}
			}
		}
		return bookingLockHandler;
	}

	public void init() {
		lockMap = new HashMap();
	}

	public void cancel() {
		lockMap.clear();
	}
	
	synchronized public void addLock(Integer lockId) {
      if (isLocked(lockId)) {
    	  // TODO
        logger.debug("already locked - " + lockId);
    	  throw new AlreadyLockedRuntimeException("already locked - " + lockId);
      }
      lockMap.put(lockId, lockId);
      System.out.println("locked - " + lockId);
      logger.debug("locked - " + lockId + " from " + getClass().getName());
	}

	synchronized public void removeLock(Integer lockId) {
	      if (!isLocked(lockId)) {
	    	  // TODO
          logger.debug("not locked - " + lockId);
	    	  throw new NotLockedRuntimeException("not locked - " + lockId);
	      }
	      lockMap.remove(lockId);
	      System.out.println("unlocked - " + lockId);
        logger.debug("unlocked - " + lockId);
	}

	synchronized private boolean isLocked(Integer lockId) {
		return lockMap.get(lockId) != null;
	}
	
//	public void doSomething(Integer lockId) {
//		addLock(lockId);
//		try {
//		      System.out.println(".");
//		      System.out.println(".");
//		      System.out.println(".");
//		}
//		finally {
//			removeLock(lockId);
//		}
//	}

}
