package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Training;

public interface TrainingDAO 
{
	public List<Training> getTrainings(boolean showOnlyActive);
	public Training getTraining(Integer trainingId);
	public Training getTrainingForName(String name);
	public Training getTrainingForCode(String code);
	public int insertTraining(Training training, Integer auditorId);
  public int updateTraining(Training training, Integer auditorId);
  public int updateTrainingDisplayOrder(Training training, Integer auditorId);
	public int deleteTraining(Integer trainingId, Integer noOfChanges, Integer auditorId);
}
