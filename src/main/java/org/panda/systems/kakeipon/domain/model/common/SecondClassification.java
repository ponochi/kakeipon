package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;

import java.io.Serializable;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_second_class" )
public class SecondClassification implements Serializable {

  @Id
  private Long firstClassId;

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_second_class_seq", allocationSize = 1)
  private Long secondClassId;

  private String secondClassName;

  @Override
  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    if ( !( o instanceof SecondClassification) ) return false;
    SecondClassification that = (SecondClassification) o;
    if ( firstClassId == null || secondClassId == null ) {
      return false;
    } else {
      return firstClassId.equals( that.firstClassId ) &&
             secondClassId.equals( that.secondClassId ) &&
             secondClassName.equals( that.secondClassName );
    }
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + (firstClassId.intValue() + secondClassId.intValue());
    result = 31 * result + ( secondClassName == null ? 0 : secondClassName.hashCode() );
    return result;
  }
}
