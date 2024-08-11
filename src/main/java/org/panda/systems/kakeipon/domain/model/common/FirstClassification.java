package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;

import java.io.Serializable;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_first_class" )
public class FirstClassification implements Serializable {

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_first_class_seq", allocationSize = 1)
  private Long firstClassId;

  private String firstClassName;

  @Override
  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    if ( !( o instanceof FirstClassification) ) return false;
    FirstClassification that = (FirstClassification) o;
    if ( firstClassId == null ) {
      return false;
    } else {
      return firstClassId.equals( that.firstClassId ) &&
             firstClassName.equals( that.firstClassName );
    }
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (firstClassId.intValue());
    result = 31 * result + ( firstClassName == null ? 0 : firstClassName.hashCode() );
    return result;
  }

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

  public FirstClassification() {
  }

  public FirstClassification(Long firstClassId, String firstClassName) {
    this.firstClassId = firstClassId;
    this.firstClassName = firstClassName;
  }

  public FirstClassification(String firstClassName) {
    this.firstClassName = firstClassName;
  }

  public FirstClassification(Long firstClassId) {
    this.firstClassId = firstClassId;
  }
}
