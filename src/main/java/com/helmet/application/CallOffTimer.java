package com.helmet.application;

import java.util.Timer;

public class CallOffTimer {

    Timer timer = new Timer(); 
	    
    public CallOffTimer(int initial, int subsequent) {
    	timer.schedule(new CallOffTask(), initial*1000, subsequent*1000);
	}
    
    public void cancel() {
    	timer.cancel();
    }
    

}
