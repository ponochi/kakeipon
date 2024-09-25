package org.panda.systems.kakeipon.domain.model.currency;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "currency")
@Data
public class CurrencyList implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "currency_seq", allocationSize = 1)
  @Column(name = "currency_id")
  private Long currencyId;

  @Column(name = "currency_name")
  private String currencyName;
}
