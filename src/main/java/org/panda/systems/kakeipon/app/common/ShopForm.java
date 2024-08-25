package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class ShopForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_shop_seq", allocationSize = 1)
  @Column(name = "shop_id")
  private Long shopId;

  @NotEmpty
  @Column
  private String shopName;

  @NotEmpty
  @Column
  private String branchName;

  @Column
  private String shopUrl;

  @Column
  private String phoneNumber;

  @Column
  private String email;

  @Column
  private Date openShopDate;

  @Column
  private Date closeShopDate;

  @Column
  private String shopMemo;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;
}
