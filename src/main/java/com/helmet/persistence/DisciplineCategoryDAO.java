package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.DisciplineCategory;
import com.helmet.bean.DisciplineCategoryUser;
import com.helmet.bean.DisciplineCategoryUserEntity;
import com.helmet.bean.DisciplineCategoryUserEntityAdmin;

public interface DisciplineCategoryDAO 
{
  public List<DisciplineCategory> getDisciplineCategories(boolean showOnlyActive);
  public List<DisciplineCategoryUser> getDisciplineCategoryUsers(boolean showOnlyActive);
  public List<DisciplineCategoryUserEntity> getDisciplineCategoryUserEntities(boolean showOnlyActive);
  public DisciplineCategory getDisciplineCategory(Integer disciplineCategoryId);
  public DisciplineCategoryUser getDisciplineCategoryUser(Integer disciplineCategoryId);
  public DisciplineCategoryUserEntity getDisciplineCategoryUserEntity(Integer disciplineCategoryId);
  public DisciplineCategoryUserEntityAdmin getDisciplineCategoryUserEntityAdmin(Integer disciplineCategoryId);
	public DisciplineCategory getDisciplineCategoryForName(String name);
	public DisciplineCategory getDisciplineCategoryForCode(String code);
	public int insertDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId);
  public int updateDisciplineCategory(DisciplineCategory disciplineCategory, Integer auditorId);
  public int updateDisciplineCategoryDisplayOrder(DisciplineCategory disciplineCategory, Integer auditorId);
	public int deleteDisciplineCategory(Integer disciplineCategoryId, Integer noOfChanges, Integer auditorId);
}
