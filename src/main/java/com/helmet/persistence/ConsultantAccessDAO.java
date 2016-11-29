package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ConsultantAccess;
import com.helmet.bean.ConsultantAccessUser;

public interface ConsultantAccessDAO {

	public List<ConsultantAccessUser> getConsultantAccessUsersForConsultant(Integer consultantId);
	public int insertConsultantAccess(ConsultantAccess consultantAccess, Integer auditorId);
	public int deleteConsultantAccess(Integer consultantAccessId, Integer noOfChanges, Integer auditorId);

}
