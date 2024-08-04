package org.panda.systems.kakeipon.domain.model.common;

import jakarta.persistence.*;

// Income and Expenditure
//
@Entity
@Table( name = "tbl_third_class" )
public class ThirdClassification {

  @Id
  private Long firstClassId;

  @Id
  private Long secondClassId;

  @Id
  @GeneratedValue( strategy = GenerationType.IDENTITY )
  @SequenceGenerator(name = "tbl_third_class_seq", allocationSize = 1)
  private Long thirdClassId;

  private String thirdClassName;

  @Override
  public boolean equals( Object o ) {
    if ( this == o ) return true;
    if ( o == null || getClass() != o.getClass() ) return false;
    if ( !( o instanceof ThirdClassification) ) return false;
    ThirdClassification that = (ThirdClassification) o;
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
}
