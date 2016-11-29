package com.helmet.taglib;

import javax.servlet.jsp.PageContext;

public class TagUtil {

    public static int getScope(String scopeName) {

        if ("page".equalsIgnoreCase(scopeName)) {
            return PageContext.PAGE_SCOPE;
        }
        if ("request".equalsIgnoreCase(scopeName)) {
            return PageContext.REQUEST_SCOPE;
        }
        if ("session".equalsIgnoreCase(scopeName)) {
            return PageContext.SESSION_SCOPE;
        }
        if ("application".equalsIgnoreCase(scopeName)) {
            return PageContext.APPLICATION_SCOPE;
        }

        return PageContext.PAGE_SCOPE;
    }
    

}
