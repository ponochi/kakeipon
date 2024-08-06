package org.panda.systems.kakeipon.app.common;

import java.io.Serializable;

public class ClassificationsForm implements Serializable {
  private static final long serialVersionUID = 1L;

  public ClassificationsForm() {
  }

  private Long firstClassId;
  private String firstClassName;

  private Long secondClassId;
  private String secondClassName;

  private Long thirdClassId;
  private String thirdClassName;

  public Long getFirstClassId() {
    return firstClassId;
  }

  public void setFirstClassId(Long firstClassId) {
    this.firstClassId = firstClassId;
  }

  public String getFirstClassName() {
    return firstClassName;
  }

  public void setFirstClassName(String firstClassName) {
    this.firstClassName = firstClassName;
  }

  public Long getSecondClassId() {
    return secondClassId;
  }

  public void setSecondClassId(Long secondClassId) {
    this.secondClassId = secondClassId;
  }

  public String getSecondClassName() {
    return secondClassName;
  }

  public void setSecondClassName(String secondClassName) {
    this.secondClassName = secondClassName;
  }

  public Long getThirdClassId() {
    return thirdClassId;
  }

  public void setThirdClassId(Long thirdClassId) {
    this.thirdClassId = thirdClassId;
  }

  public String getThirdClassName() {
    return thirdClassName;
  }

  public void setThirdClassName(String thirdClassName) {
    this.thirdClassName = thirdClassName;
  }

  public String getClasses() {
    return firstClassId + ":" + firstClassName +
        " > " + secondClassId + ":" + secondClassName +
        " > " + thirdClassId + ":" + thirdClassName;
  }
}
