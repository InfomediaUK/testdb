package com.helmet.application.agy;

import java.io.File;
import java.io.FilenameFilter;

public class AgencyWorkerChecklistFileListFilter implements FilenameFilter
{
  private final String NAME = "checklist";
  private Integer applicantId;
  private Integer bookingId;
  private boolean includeDeleted;
  
  public AgencyWorkerChecklistFileListFilter(Integer applicantId)
  {
    super();
    this.applicantId = applicantId;
  }

  public AgencyWorkerChecklistFileListFilter(Integer applicantId, boolean includeDeleted)
  {
    super();
    this.applicantId = applicantId;
    this.includeDeleted = includeDeleted;
  }

  public AgencyWorkerChecklistFileListFilter(Integer applicantId, Integer bookingId, boolean includeDeleted)
  {
    super();
    this.applicantId = applicantId;
    this.bookingId = bookingId;
    this.includeDeleted = includeDeleted;
  }

  public boolean accept(File file, String filename)
  {
    if (includeDeleted)
    {
      if (bookingId == null)
      {
        // Starts with 'checklistNNN-' and ends with '.pdf'.
        return filename.startsWith(NAME + applicantId + "-") && filename.endsWith(".pdf");
      }
      else
      {
        // Starts with 'checklistNNN-' and ends with '.pdf'.
        return filename.startsWith(NAME + applicantId + "-") && filename.contains("-" + bookingId + "-") && filename.endsWith(".pdf");
      }
    }
    else
    {
      if (bookingId == null)
      {
        // Starts with 'checklistNNN-' and ends with '.pdf' and has NOT been deleted.
        return filename.startsWith(NAME + applicantId + "-") && filename.endsWith(".pdf") && !filename.contains(".del.");
      }
      else
      {
        // Starts with 'checklistNNN-' and ends with '.pdf' and has NOT been deleted.
        return filename.startsWith(NAME + applicantId + "-") && filename.contains("-" + bookingId + "-") && filename.endsWith(".pdf") && !filename.contains(".del.");
      }
    }
  }

}
