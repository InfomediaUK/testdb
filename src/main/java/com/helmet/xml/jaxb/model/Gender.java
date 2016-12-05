package com.helmet.xml.jaxb.model;

public enum Gender 
{
  MALE("Male", 'M'), FEMALE("Female", 'F');
  private String name;
  private char code;
  private Gender(String name, char code)
  {
    this.name = name;
    this.code = code;
  }
  public String getName()
  {
    return name;
  }
  public char getCode()
  {
    return code;
  }
};
