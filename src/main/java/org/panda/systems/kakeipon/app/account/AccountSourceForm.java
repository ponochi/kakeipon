package org.panda.systems.kakeipon.app.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "account_info")
@Data
public class AccountSourceForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final AccountSourceService accountSourceService;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "account_info_seq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "account_id")
  private Long accountSourceId;

  @NotEmpty
  @Column(name = "account_name")
  private String accountName;

  @Column(name = "branch_name")
  private String branchName;

  @PastOrPresent
  @Column(name = "entry_date")
  private LocalDateTime entryDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  // Default constructor
  public AccountSourceForm() {

    this.accountSourceService = null;
  }

  public AccountSourceForm(
      AccountSourceService accountSourceService) {

    this.accountSourceService = accountSourceService;
  }

  public AccountSourceForm setAccountSourceFormByDB(Long accountSourceId) {

    if (accountSourceId == null) {
      this.setAccountSourceId(Long.parseLong("1"));
    } else {
      this.setAccountSourceId(accountSourceId);
    }
    AccountSource source
        = accountSourceService.findById(this.getAccountSourceId());
    this.setAccountName(source.getAccountName());
    this.setBranchName(source.getBranchName());
    this.setEntryDate(source.getEntryDate());
    this.setUpdateDate(source.getUpdateDate());

    return this;
  }
}
