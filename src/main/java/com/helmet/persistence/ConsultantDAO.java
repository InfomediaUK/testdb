package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Consultant;
import com.helmet.bean.ConsultantEntity;

public interface ConsultantDAO {

	public List<Consultant> getConsultantsForAgency(Integer agencyId, boolean showOnlyActive);
  public Consultant getProspectConsultant(Integer agencyId);
	public Consultant getConsultantForLogin(Integer agencyId, String login);
	public Consultant getConsultant(Integer consultantId);
	public ConsultantEntity getConsultantEntity(Integer consultantId);
	public int insertConsultant(Consultant consultant, Integer auditorId);
	public int updateConsultant(Consultant consultant, Integer auditorId);
	public int updateConsultantPwd(Integer consultantId, String newPwd, String pwdHint, Integer noOfChanges, Integer auditorId);
	public int updateConsultantSecretWord(Integer consultantId, String newSecretWord, Integer noOfChanges, Integer auditorId);
	public int updateConsultantShowPageHelp(Consultant consultant, Integer auditorId);
	public int deleteConsultant(Integer consultantId, Integer noOfChanges, Integer auditorId);

}
