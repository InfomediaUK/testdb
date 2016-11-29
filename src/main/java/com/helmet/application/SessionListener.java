package com.helmet.application;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

  private int sessionCount;

  public SessionListener() {
    this.sessionCount = 0;
  }

  public void sessionCreated(HttpSessionEvent se) {
    HttpSession session = se.getSession();
    System.out.println(session.getMaxInactiveInterval());
    // set the maxInactiveInterval to 60 seconds - purely to check when destroyed
    session.setMaxInactiveInterval(60);
    synchronized (this) {
      sessionCount++;
    }
    String id = session.getId();
    Date now = new Date();
    String message = new StringBuffer("New Session created on ").append(
        now.toString()).append("\nID: ").append(id).append("\n")
        .append("There are now ").append("" + sessionCount).append(
            " live sessions in the application.").toString();

    System.out.println(message);
  }

  public void sessionDestroyed(HttpSessionEvent se) {

    HttpSession session = se.getSession();
    String id = session.getId();
    synchronized (this) {
      --sessionCount;
    }
    String message = new StringBuffer("Session destroyed"
        + "\nValue of destroyed session ID is").append("" + id).append(
        "\n").append("There are now ").append("" + sessionCount)
        .append(" live sessions in the application.").toString();
    System.out.println(message);
  }
} 