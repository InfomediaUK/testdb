package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.ConsultantAccessGroup;
import com.helmet.bean.ConsultantAccessGroupUser;

public interface ConsultantAccessGroupDAO {

	public List<ConsultantAccessGroupUser> getConsultantAccessGroupUsersForConsultant(Integer consultantId);
	public int insertConsultantAccessGroup(ConsultantAccessGroup consultantAccessGroup, Integer auditorId);
	public int deleteConsultantAccessGroup(Integer consultantAccessId, Integer noOfChanges, Integer auditorId);

}
