package com.helmet.xml.jaxb.model.ubl;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(propOrder = { "value", "mimeCode", "filename", "encodingCode" })
public class EmbeddedDocumentBinaryObject
{
  private String value;
  private String mimeCode;
  private String filename;
  private String encodingCode;

  @XmlValue
  public String getValue()
  {
    return value;
  }

  public void setValue(String id)
  {
    this.value = id;
  }

  @XmlAttribute(name = "mimeCode")
  public String getMimeCode()
  {
    return mimeCode;
  }

  public void setMimeCode(String listId)
  {
    this.mimeCode = listId;
  }

  @XmlAttribute(name = "filename")
  public String getFilename()
  {
    return filename;
  }

  public void setFilename(String filename)
  {
    this.filename = filename;
  }

  @XmlAttribute(name = "encodingCode")
  public String getEncodingCode()
  {
    return encodingCode;
  }

  public void setEncodingCode(String encodingCode)
  {
    this.encodingCode = encodingCode;
  }


}
