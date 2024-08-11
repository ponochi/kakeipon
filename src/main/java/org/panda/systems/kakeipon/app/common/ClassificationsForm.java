package org.panda.systems.kakeipon.app.common;

import org.panda.systems.kakeipon.domain.model.common.FirstClassification;
import org.panda.systems.kakeipon.domain.model.common.SecondClassification;
import org.panda.systems.kakeipon.domain.model.common.ThirdClassification;

import java.io.Serializable;
import java.util.List;

public class ClassificationsForm implements Serializable {
  private static final long serialVersionUID = 1L;

  public ClassificationsForm() {
  }

  private Long firstClassId;
  private String firstClassName;
  private FirstClassification firstClassification;
  private List<FirstClassification> firstClassifications;

  private Long secondClassId;
  private String secondClassName;
  private SecondClassification secondClassification;
  private List<SecondClassification> secondClassifications;

  private Long thirdClassId;
  private String thirdClassName;
  private ThirdClassification thirdClassification;
  private List<ThirdClassification> thirdClassifications;

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

  public FirstClassification getFirstClassification() {
    return firstClassification;
  }

  public void setFirstClassification(FirstClassification firstClassification) {
    this.firstClassification = firstClassification;
  }

//  public List<FirstClassification> getFirstClassifications() {
//    return firstClassifications;
//  }
//
//  public void setFirstClassifications(List<FirstClassification> firstClassifications) {
//    this.firstClassifications = firstClassifications;
//  }

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

  public SecondClassification getSecondClassification() {
    return secondClassification;
  }

  public void setSecondClassification(SecondClassification secondClassification) {
    this.secondClassification = secondClassification;
  }

  public List<SecondClassification> getSecondClassifications() {
    return secondClassifications;
  }

  public void setSecondClassifications(List<SecondClassification> secondClassifications) {
    this.secondClassifications = secondClassifications;
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

  public ThirdClassification getThirdClassification() {
    return thirdClassification;
  }

  public void setThirdClassification(ThirdClassification thirdClassification) {
    this.thirdClassification = thirdClassification;
  }

  public List<ThirdClassification> getThirdClassifications() {
    return thirdClassifications;
  }

  public void setThirdClassifications(List<ThirdClassification> thirdClassifications) {
    this.thirdClassifications = thirdClassifications;
  }

  public String toString() {
    return firstClassId + ":" + firstClassName +
        " > " + secondClassId + ":" + secondClassName +
        " > " + thirdClassId + ":" + thirdClassName;
  }
}
