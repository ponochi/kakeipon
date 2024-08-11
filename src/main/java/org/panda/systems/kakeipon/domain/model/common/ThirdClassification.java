package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_third_class" )
@IdClass(ThirdClassificationEntityPkey.class)
public class ThirdClassification {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_third_class_seq", allocationSize = 1)
  private Long thirdClassId;

  @Id
  private Long secondClassId;

  @Id
  private Long firstClassId;

  private String thirdClassName;

  @Override
  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    if ( !(o instanceof ThirdClassification that) ) return false;
    if ( firstClassId == null || secondClassId == null || thirdClassId == null ) {
      return false;
    } else {
      return firstClassId.equals( that.firstClassId ) &&
             secondClassId.equals( that.secondClassId ) &&
             thirdClassId.equals( that.thirdClassId ) &&
             thirdClassName.equals( that.thirdClassName );
    }
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (firstClassId.intValue() + secondClassId.intValue() + thirdClassId.intValue());
    result = 31 * result + ( thirdClassName == null ? 0 : thirdClassName.hashCode() );
    return result;
  }

  public Long getThirdClassId() {
    return thirdClassId;
  }

  public void setThirdClassId(Long thirdClassId) {
    this.thirdClassId = thirdClassId;
  }

  public Long getSecondClassId() {
    return secondClassId;
  }

  public void setSecondClassId(Long secondClassId) {
    this.secondClassId = secondClassId;
  }

  public Long getFirstClassId() {
    return firstClassId;
  }

  public void setFirstClassId(Long firstClassId) {
    this.firstClassId = firstClassId;
  }

  public String getThirdClassName() {
    return thirdClassName;
  }

  public void setThirdClassName(String thirdClassName) {
    this.thirdClassName = thirdClassName;
  }

  @Override
  public String toString() {
    return "ThirdClassification{" +
        "firstClassId=" + firstClassId +
        ", secondClassId=" + secondClassId +
        ", thirdClassId=" + thirdClassId +
        ", thirdClassName='" + thirdClassName + '\'' +
        '}';
  }
}
