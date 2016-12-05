package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlElement;

public class Attachment
{
  private EmbeddedDocumentBinaryObject embeddedDocumentBinaryObject;

  @XmlElement(name = "EmbeddedDocumentBinaryObject", namespace = UblConstants.COMMON_BASIC_COMPONENTS_2)
  public EmbeddedDocumentBinaryObject getEmbeddedDocumentBinaryObject()
  {
    return embeddedDocumentBinaryObject;
  }

  public void setEmbeddedDocumentBinaryObject(EmbeddedDocumentBinaryObject name)
  {
    this.embeddedDocumentBinaryObject = name;
  }

}
