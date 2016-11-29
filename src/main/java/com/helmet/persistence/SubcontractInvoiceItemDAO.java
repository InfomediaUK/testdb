package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.SubcontractInvoiceItem;
import com.helmet.bean.SubcontractInvoiceItemHistory;
import com.helmet.bean.SubcontractInvoiceItemUser;

public interface SubcontractInvoiceItemDAO 
{
  public List<SubcontractInvoiceItem> getSubcontractInvoiceItemsForSubcontractInvoice(Integer subcontractInvoiceId);
  public List<SubcontractInvoiceItemUser> getSubcontractInvoiceItemUsersForSubcontractInvoice(Integer subcontractInvoiceId);
  public int insertSubcontractInvoiceItem(SubcontractInvoiceItem nhsBooking, Integer auditorId);
  public int deleteSubcontractInvoiceItem(SubcontractInvoiceItem nhsBooking, Integer auditorId);
  public int updateSubcontractInvoiceItem(SubcontractInvoiceItem nhsBooking, Integer auditorId);
  public SubcontractInvoiceItemUser getSubcontractInvoiceItemUser(Integer subcontractInvoiceItemId);
  public List<SubcontractInvoiceItemHistory> getSubcontractInvoiceItemHistoryForBankReqNum(String bankReqNum);
}
