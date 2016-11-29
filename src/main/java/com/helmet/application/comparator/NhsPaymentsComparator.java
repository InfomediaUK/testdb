package com.helmet.application.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.helmet.bean.nhs.PaymentLine;

public class NhsPaymentsComparator implements Comparator, Serializable
{

  /**
   * 
   */
  private static final long serialVersionUID = -4701671953459487203L;

  public int compare(Object o1, Object o2)
  {
    if (!(o1 instanceof PaymentLine))
      throw new ClassCastException();
    if (!(o2 instanceof PaymentLine))
      throw new ClassCastException();

    PaymentLine paymentLine1 = (PaymentLine) o1;
    PaymentLine paymentLine2 = (PaymentLine) o2;

    if (paymentLine1.getClientName().compareTo(paymentLine2.getClientName()) == 0)
    {
      // Equal ClientName now check Date
      if (paymentLine1.getDate().compareTo(paymentLine2.getDate()) == 0)
      {
        // Equal Date compare Backing Report Name.
        return paymentLine1.getDocumentName().compareTo(paymentLine2.getDocumentName());
      }
      else
      {
        // Date differs.
        return paymentLine1.getDate().compareTo(paymentLine2.getDate());
      }
    }
    else
    {
      // Client Name differs.
      return paymentLine1.getClientName().compareTo(paymentLine2.getClientName());
    }
  }

}
