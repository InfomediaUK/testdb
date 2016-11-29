package com.helmet.api;

public class ServiceFactory {
	
    private static ServiceFactory instance;

    AgyService agyService;

    public final static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

	public AgyService getAgyService() {
		return agyService;
	}
	public void setAgyService(AgyService agyService) {
		this.agyService = agyService;
	}

}
