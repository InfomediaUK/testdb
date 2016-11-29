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

public abstract class LoginFilter implements Filter {

  private FilterConfig filterConfig = null;
    
    private String loginPage = null; 
    private String ignorePattern1 = null; 
    private String ignorePattern2 = null; 
    private String ignorePattern3 = null; 
    private String ignorePattern4 = null; 
    private String ignorePattern5 = null; 
    private String ignorePattern6 = null; 
    private String ignorePattern7 = null; 
    private String ignorePattern8 = null; 
    private String ignorePattern9 = null; 
    private String ignorePattern10 = null; 

  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
    loginPage = filterConfig.getInitParameter(Constants.loginPage);   
    ignorePattern1 = filterConfig.getInitParameter(Constants.ignorePattern1);   
    ignorePattern2 = filterConfig.getInitParameter(Constants.ignorePattern2);   
    ignorePattern3 = filterConfig.getInitParameter(Constants.ignorePattern3);   
    ignorePattern4 = filterConfig.getInitParameter(Constants.ignorePattern4);   
    ignorePattern5 = filterConfig.getInitParameter(Constants.ignorePattern5);   
    ignorePattern6 = filterConfig.getInitParameter(Constants.ignorePattern6);   
    ignorePattern7 = filterConfig.getInitParameter(Constants.ignorePattern7);   
    ignorePattern8 = filterConfig.getInitParameter(Constants.ignorePattern8);   
    ignorePattern9 = filterConfig.getInitParameter(Constants.ignorePattern9);   
    ignorePattern10 = filterConfig.getInitParameter(Constants.ignorePattern10);   
  }

  public void destroy() {
    this.filterConfig = null;
  }

  abstract protected boolean isLoggedIn(HttpServletRequest hreq);
  
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
        
        if (!shouldBeIgnored(servletPath)) {

            if (!isLoggedIn(hreq)) {
              String loginPageWithParameters = addLoginParameters(loginPage, hreq);
            Utilities.redirectToPage(hreq, hres, loginPageWithParameters);
              return;
          }

        }
  
    chain.doFilter(req, res);
  
    postProcess(hreq);

  }

  private boolean shouldBeIgnored(String servletPath) {

    if ((loginPage != null && loginPage.equals(servletPath)) ||
      (ignorePattern1 != null && ignorePattern1.equals(servletPath)) ||
      (ignorePattern2 != null && ignorePattern2.equals(servletPath)) ||
      (ignorePattern3 != null && ignorePattern3.equals(servletPath)) ||
      (ignorePattern4 != null && ignorePattern4.equals(servletPath)) ||
      (ignorePattern5 != null && ignorePattern5.equals(servletPath)) ||
      (ignorePattern6 != null && ignorePattern6.equals(servletPath)) ||
      (ignorePattern7 != null && ignorePattern7.equals(servletPath)) ||
      (ignorePattern8 != null && ignorePattern8.equals(servletPath)) ||
      (ignorePattern9 != null && ignorePattern9.equals(servletPath)) ||
      (ignorePattern10 != null && ignorePattern10.equals(servletPath))) {
      return true;
    }
    
    return false; 
    
  }
  
  protected String addLoginParameters(String loginPage, HttpServletRequest hreq) {

    // override in decendant methods if required
    return loginPage;
  
  }

  protected void postProcess(HttpServletRequest hreq) {

    // override in decendant methods if required
    
  }

}
