package com.helmet.application;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;


public class TradeshiftHandler
{

  private static TradeshiftHandler tradeshiftHandler;
  private String hostName;
  private String tenantIdHeaderName;
  private String userAgent;
  private String restUrlPart;
  private String documentsUrlPart;
  private String dispatcherUrlPart;
  private String documentProfileId;
  private String documentTypeCode;
  
  protected transient XLogger logger = XLoggerFactory.getXLogger(getClass());

  public static TradeshiftHandler getInstance()
  {
    if (tradeshiftHandler == null)
    {
      // NOT instantiated yet so create it.
      synchronized (TradeshiftHandler.class)
      {
        // Only ONE thread at a time here!
        if (tradeshiftHandler == null)
        {
          tradeshiftHandler = new TradeshiftHandler();
        }
      }
    }
    return tradeshiftHandler;
  }

  public String getTenantIdHeaderName()
  {
    return tenantIdHeaderName;
  }

  public void setTenantIdHeaderName(String tenantIdHeaderName)
  {
    this.tenantIdHeaderName = tenantIdHeaderName;
  }

  public String getUserAgent()
  {
    return userAgent;
  }

  public void setUserAgent(String userAgent)
  {
    this.userAgent = userAgent;
  }

  public String getDispatcherUrlPart()
  {
    return dispatcherUrlPart;
  }

  public void setDispatcherUrlPart(String dispatcherUrlPart)
  {
    this.dispatcherUrlPart = dispatcherUrlPart;
  }

  public String getDocumentProfileId()
  {
    return documentProfileId;
  }

  public void setDocumentProfileId(String documentProfileId)
  {
    this.documentProfileId = documentProfileId;
  }

  public String getDocumentTypeCode()
  {
    return documentTypeCode;
  }

  public void setDocumentTypeCode(String documentTypeCode)
  {
    this.documentTypeCode = documentTypeCode;
  }

  public String getDocumentsUrlPart()
  {
    return documentsUrlPart;
  }

  public void setDocumentsUrlPart(String documentsUrlPart)
  {
    this.documentsUrlPart = documentsUrlPart;
  }

  public String getHostName()
  {
    return hostName;
  }

  public void setHostName(String hostName)
  {
    this.hostName = hostName;
  }

  public String getRestUrlPart()
  {
    return restUrlPart;
  }

  public void setRestUrlPart(String restUrlPart)
  {
    this.restUrlPart = restUrlPart;
  }

}
