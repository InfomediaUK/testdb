package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.EmailAction;

public interface EmailActionDAO 
{
	public List<EmailAction> getEmailActions(boolean showOnlyActive);
	public EmailAction getEmailAction(Integer emailActionId);
	public EmailAction getEmailActionForName(String name);
	public int insertEmailAction(EmailAction emailAction, Integer auditorId);
	public int updateEmailAction(EmailAction emailAction, Integer auditorId);
	public int deleteEmailAction(Integer emailActionId, Integer noOfChanges, Integer auditorId);
}
