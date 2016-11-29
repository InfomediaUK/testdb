package com.helmet.application.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helmet.application.Constants;
import com.helmet.application.Utilities;

public abstract class AccessFilter implements Filter {

  private FilterConfig filterConfig = null;
    
    private String urlPattern = null; 
    private String accessDeniedPage = null; 

  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    urlPattern = filterConfig.getInitParameter(Constants.urlPattern);   
    accessDeniedPage = filterConfig.getInitParameter(Constants.accessDeniedPage); 
  }

  public void destroy() {
    this.filterConfig = null;
  }

  protected boolean hasAccess(HttpServletRequest hreq, String servletPath) {
    // override in decendant methods if required
    //
    // not sure about this !!!!!
    //
    return true;
  }
  
  public void doFilter(ServletRequest req, ServletResponse res,
      FilterChain chain) throws IOException, ServletException {

    HttpServletRequest hreq = null;
        HttpServletResponse hres = null;
        if (req instanceof HttpServletRequest &&
            res instanceof HttpServletResponse) {
            hreq = (HttpServletRequest) req;
            hres = (HttpServletResponse) res;
        }

        String servletPath = hreq.getServletPath();
        
    String slash = "/";
    String accessPath = slash;
    
    int indexOfSlash = servletPath.indexOf(slash, 1);
    if (indexOfSlash != -1) {
      // servletPath contains a slash after the first position so strip out the accessPath.
      accessPath = servletPath.substring(0, indexOfSlash);
    }
    
        if (accessPath.equals(urlPattern)) {
          
          String path = servletPath.substring(urlPattern.length() + 1);
          if (!hasAccess(hreq, path)) {
            Utilities.redirectToPage(hreq, hres, accessDeniedPage);
              return;
          }
    }
    
    chain.doFilter(req, res);
  }

}
