package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.IdDocument;

public interface IdDocumentDAO 
{
	public List<IdDocument> getIdDocuments(boolean showOnlyActive);
	public IdDocument getIdDocument(Integer idDocumentId);
	public IdDocument getIdDocumentForName(String name);
	public IdDocument getIdDocumentForCode(String code);
	public int insertIdDocument(IdDocument idDocument, Integer auditorId);
  public int updateIdDocument(IdDocument idDocument, Integer auditorId);
  public int updateIdDocumentDisplayOrder(IdDocument idDocument, Integer auditorId);
	public int deleteIdDocument(Integer idDocumentId, Integer noOfChanges, Integer auditorId);
}
