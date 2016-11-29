package com.helmet.application;

import java.util.concurrent.Future;

public class NhsBookingsBookTaskHandle extends NhsBookingsBookTaskResult
{
  private final Future<NhsBookingsBookTaskResult> future;
  
  public Future<NhsBookingsBookTaskResult> getFuture()
  {
    return future;
  }

  public NhsBookingsBookTaskHandle(String clientName, String siteName, String locationName, String jobProfileName, Integer bookingsToProcess, Future<NhsBookingsBookTaskResult> future)
  {
    super(clientName, siteName, locationName, jobProfileName, bookingsToProcess);
    this.future = future;
  }

  public Boolean equals(String clientName, String siteName, String locationName, String jobProfileName)
  {
    return clientName.equals(getClientName()) && siteName.equals(getSiteName()) && locationName.equals(getLocationName()) && jobProfileName.equals(getJobProfileName());
  }

}
