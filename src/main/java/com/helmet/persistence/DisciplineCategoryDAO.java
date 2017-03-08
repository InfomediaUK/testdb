package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.DisciplineCategory;
import com.helmet.bean.DisciplineCategoryUser;

public interface DisciplineCategoryDAO 
{
  public List<DisciplineCategory> getDisciplineCategories(boolean showOnlyActive);
  public DisciplineCategory getDisciplineCategory(Integer disciplineCategoryId);
  public DisciplineCategoryUser getDisciplineCategoryUser(Integer disciplineCategoryId);
	public DisciplineCategory getDisciplineCategoryForName(String name);
	public DisciplineCategory getDisciplineCategoryForCode(String code);
	public int insertDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId);
  public int updateDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId);
  public int updateDisciplineCategoryDisplayOrder(DisciplineCategory disciplineCategory, Integer auditorId);
	public int deleteDisciplineCategory(Integer disciplineCategoryId, Integer noOfChanges, Integer auditorId);
  public List<DisciplineCategoryUser> getDisciplineCategoryUsers(boolean showOnlyActive);
}
