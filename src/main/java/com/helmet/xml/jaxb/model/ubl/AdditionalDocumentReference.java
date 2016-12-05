package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(propOrder = { "id", "documentTypeCode", "attachment" })
public class AdditionalDocumentReference
{
  private String id;
  private DocumentTypeCode documentTypeCode;
  private Attachment attachment;

  @XmlElement(name = "ID", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  @XmlElement(name = "DocumentTypeCode", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public DocumentTypeCode getDocumentTypeCode()
  {
    return documentTypeCode;
  }

  public void setDocumentTypeCode(DocumentTypeCode documentTypeCode)
  {
    this.documentTypeCode = documentTypeCode;
  }

  @XmlElement(name = "Attachment", namespace = UblConstants.COMMON_AGGREGATE_COMPONENTS_2)
  public Attachment getAttachment()
  {
    return attachment;
  }

  public void setAttachment(Attachment attachment)
  {
    this.attachment = attachment;
  }


}
