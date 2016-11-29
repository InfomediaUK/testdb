package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Grade;

public interface GradeDAO {

	public List<Grade> getGradesForClient(Integer clientId, boolean showOnlyActive);
	public List<Grade> getGradesForJobProfile(Integer jobProfileId);
	public List<Grade> getGradesNotForJobProfile(Integer clientId, Integer jobProfileId);
	public List<Grade> getGradesNotForClientAgency(Integer clientId, Integer clientAgencyId);
	public List<Grade> getGradesNotForClientAgencyJobProfile(Integer jobProfileId, Integer clientAgencyJobProfileId);
	public Grade getGrade(Integer gradeId);
	public Grade getGradeForName(Integer clientId, String name);
	public int insertGrade(Grade grade, Integer auditorId);
	public int updateGrade(Grade grade, Integer auditorId);
	public int deleteGrade(Integer gradeId, Integer noOfChanges, Integer auditorId);
	public int updateGradeDisplayOrder(Integer gradeId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
