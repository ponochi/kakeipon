package org.panda.systems.kakeipon.app.currency;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Table(name = "tbl_currency")
@Data
public class CurrencyForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_currency_seq", allocationSize = 1)
  @Column(name = "currency_id")
  private Long currencyId;

  @Column(name = "currency_name")
  private String currencyName;
}
