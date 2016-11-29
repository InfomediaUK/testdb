package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.helmet.bean.SubcontractInvoiceUser;

public class SubcontractInvoiceUserComparator implements Comparator, Serializable
{

  /**
   * Sort SubcontractInvoiceUsers on:
   * ClientName (Trust)
   * SiteName (Hospital)
   * LocationName (Ward)
   * JobProfileNhsAssignment (NHS Assignment)
   * StartDate (Period Start)
   * EndDate (Period End)
   * Date (Tax Date)
   * SentDate (Can be NULL)
   */
  private static final long serialVersionUID = -4701671953459487203L;

  public int compare(Object o1, Object o2)
  {
    if (!(o1 instanceof SubcontractInvoiceUser))
    {
      throw new ClassCastException();
    }
    if (!(o2 instanceof SubcontractInvoiceUser))
    {
      throw new ClassCastException();
    }
    
    SubcontractInvoiceUser subcontractInvoice1 = (SubcontractInvoiceUser) o1;
    SubcontractInvoiceUser subcontractInvoice2 = (SubcontractInvoiceUser) o2;

    if (subcontractInvoice1.getClientName().compareTo(subcontractInvoice2.getClientName()) == 0)
    {
      // Equal ClientName now check SiteName.
      if (subcontractInvoice1.getSiteName().compareTo(subcontractInvoice2.getSiteName()) == 0)
      {
        // Equal SiteName compare LocationName.
        if (subcontractInvoice1.getLocationName().compareTo(subcontractInvoice2.getLocationName()) == 0)
        {
          // Equal LocationName compare JobProfileNhsAssignment.
          if (subcontractInvoice1.getJobProfileNhsAssignment().compareTo(subcontractInvoice2.getJobProfileNhsAssignment()) == 0)
          {
            // Equal JobProfileNhsAssignment compare StartDate.
            if (subcontractInvoice1.getStartDate().compareTo(subcontractInvoice2.getStartDate()) == 0)
            {
              // Equal StartDate compare EndDate.
              if (subcontractInvoice1.getEndDate().compareTo(subcontractInvoice2.getEndDate()) == 0)
              {
                // Equal EndDate compare (Tax) Date.
                if (subcontractInvoice1.getDate().compareTo(subcontractInvoice2.getDate()) == 0)
                {
                  // Equal (Tax) Date compare Sent Date. WARNING: It can be NULL...
                  if (subcontractInvoice1.getSentDate() == null && subcontractInvoice2.getSentDate() == null)
                  {
                    // Both NULL, return EQUAL.
                    return 0;
                  }
                  else if (subcontractInvoice1.getSentDate() != null && subcontractInvoice2.getSentDate() != null)
                  {
                    // Both NOT NULL, return Compare value.
                    return subcontractInvoice1.getSentDate().compareTo(subcontractInvoice2.getSentDate());
                  }
                  else if (subcontractInvoice1.getSentDate() == null)
                  {
                    // Sent Date 1 is NULL therefore 'earliest'...
                    return -1;
                  }
                  else
                  {
                    // Sent Date 2 is NULL therefore 'latest'...
                    return 1;
                  }
                }
                else
                {
                  // (Tax) Date differs.
                  return subcontractInvoice1.getDate().compareTo(subcontractInvoice2.getDate());
                }
              }
              else
              {
                // EndDate differs.
                return subcontractInvoice1.getEndDate().compareTo(subcontractInvoice2.getEndDate());
              }
            }
            else
            {
              // StartDate differs.
              return subcontractInvoice1.getStartDate().compareTo(subcontractInvoice2.getStartDate());
            }
          }
          else
          {
            // JobProfileNhsAssignment differs.
            return subcontractInvoice1.getJobProfileNhsAssignment().compareTo(subcontractInvoice2.getJobProfileNhsAssignment());
          }
        }
        else
        {
          // LocationName differs.
          return subcontractInvoice1.getLocationName().compareTo(subcontractInvoice2.getLocationName());
        }
      }
      else
      {
        // SiteName differs.
        return subcontractInvoice1.getSiteName().compareTo(subcontractInvoice2.getSiteName());
      }
    }
    else
    {
      // Client Name differs.
      return subcontractInvoice1.getClientName().compareTo(subcontractInvoice2.getClientName());
    }
  }

}
