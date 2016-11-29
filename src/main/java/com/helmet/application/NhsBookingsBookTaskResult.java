package com.helmet.application;

public class NhsBookingsBookTaskResult extends NhsBookingsBookTaskBase
{
  private Integer bookingsToProcess;
  private Integer bookingsProcessedOk;
  private Integer bookingsFailed;
  private boolean isDone;

  public NhsBookingsBookTaskResult(String clientName, String siteName, String locationName, String jobProfileName, Integer bookingsToProcess)
  {
    super(clientName, siteName, locationName, jobProfileName);
    this.bookingsToProcess = bookingsToProcess;
    this.bookingsProcessedOk = new Integer(0);
    this.bookingsFailed = new Integer(0);
  }

  public NhsBookingsBookTaskResult(NhsBookingsBookTaskHandle nhsBookingsBookTaskHandle)
  {
    super(nhsBookingsBookTaskHandle.getClientName(), nhsBookingsBookTaskHandle.getSiteName(), nhsBookingsBookTaskHandle.getLocationName(), nhsBookingsBookTaskHandle.getJobProfileName());
    this.bookingsToProcess = nhsBookingsBookTaskHandle.getBookingsToProcess();
    this.bookingsProcessedOk = nhsBookingsBookTaskHandle.getBookingsProcessedOk();
    this.bookingsFailed = nhsBookingsBookTaskHandle.getBookingsFailed();
  }

  public Integer getBookingsToProcess()
  {
    return bookingsToProcess;
  }

  public void setBookingsToProcess(Integer bookingsToProcess)
  {
    this.bookingsToProcess = bookingsToProcess;
  }
  
  public Integer getBookingsFailed()
  {
    return bookingsFailed;
  }

  public void setBookingsFailed(Integer bookingsFailed)
  {
    this.bookingsFailed = bookingsFailed;
  }

  public Integer getBookingsProcessedOk()
  {
    return bookingsProcessedOk;
  }

  public void setBookingsProcessedOk(Integer bookingsProcessedOk)
  {
    this.bookingsProcessedOk = bookingsProcessedOk;
  }
  
  public boolean isDone()
  {
    return isDone;
  }

  public void setDone(boolean isDone)
  {
    this.isDone = isDone;
  }
  
}
