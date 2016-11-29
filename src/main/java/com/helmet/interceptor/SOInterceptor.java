package com.helmet.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SOInterceptor implements MethodInterceptor {
    
    public Object invoke(MethodInvocation invocation) throws Throwable {
    	String name = invocation.getMethod().getDeclaringClass().getName() + "." + invocation.getMethod().getName();
    	try {
        	System.out.println("**** before **** calling " + name);
        	return invocation.proceed();
    	}
        finally {
        	System.out.println("**** after **** calling " + name);
        }
    }
    
}

