package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.TrainingCompanyUser;
import com.helmet.bean.TrainingCompanyUserEntity;
import com.helmet.bean.TrainingCompany;

public interface TrainingCompanyDAO 
{
  public List<TrainingCompany> getTrainingCompanies(boolean showOnlyActive);
  public List<TrainingCompanyUser> getTrainingCompanyUsers(boolean showOnlyActive);
	public TrainingCompany getTrainingCompany(Integer trainingCompanyId);
	public TrainingCompany getTrainingCompanyForName(String name);
	public TrainingCompany getTrainingCompanyForCode(String code);
	public TrainingCompanyUser getTrainingCompanyUser(Integer trainingCompanyId);
  public TrainingCompanyUserEntity getTrainingCompanyUserEntity(Integer agencyId);
	public int insertTrainingCompany(TrainingCompany trainingCompany, Integer auditorId);
	public int updateTrainingCompany(TrainingCompany trainingCompany, Integer auditorId);
	public int deleteTrainingCompany(Integer trainingCompanyId, Integer noOfChanges, Integer auditorId);
	public int updateTrainingCompanyDisplayOrder(Integer trainingCompanyId, Integer displayOrder, Integer noOfChanges, Integer auditorId);
}
