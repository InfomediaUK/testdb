package com.helmet.application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.helmet.application.agy.AgyConstants;

public class ExecutorContextListener implements ServletContextListener
{
  private  ExecutorService executor;

  public void contextInitialized(ServletContextEvent servletContextEvent)
  {
    ServletContext context = servletContextEvent.getServletContext();
    int numberOfExecutors = 1;
    ThreadFactory daemonFactory = new DaemonThreadFactory();
    try
    {
      numberOfExecutors = Integer.parseInt(context.getInitParameter("numberOfExecutors"));
    }
    catch (NumberFormatException ignore)
    {
    }

    if (numberOfExecutors <= 1)
    {
      executor = Executors.newSingleThreadExecutor(daemonFactory);
    }
    else
    {
      executor = Executors.newFixedThreadPool(numberOfExecutors, daemonFactory);
    }
    context.setAttribute(AgyConstants.NHS_EXECUTOR, executor);
  }

  public void contextDestroyed(ServletContextEvent servletContextEvent)
  {
    ServletContext context = servletContextEvent.getServletContext();
    executor.shutdownNow(); // or process/wait until all pending jobs are done
  }

}
