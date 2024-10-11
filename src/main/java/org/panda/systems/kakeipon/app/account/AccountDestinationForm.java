package org.panda.systems.kakeipon.app.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountDestinationForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private final AccountDestinationService accountDestinationService;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tblAccountSeq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "account_id")
  private Long accountDestinationId;

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
  public AccountDestinationForm() {

    this.accountDestinationService = null;
  }

  public AccountDestinationForm(
      AccountDestinationService accountDestinationService) {

    this.accountDestinationService = accountDestinationService;
  }

  public AccountDestinationForm setAccountDestinationFormByDB(Long accountDestinationId) {

    if (accountDestinationId == null) {
      this.setAccountDestinationId(Long.parseLong("1"));
    } else {
      this.setAccountDestinationId(accountDestinationId);
    }
    AccountDestination destination
        = accountDestinationService
        .findById(this.getAccountDestinationId());
    this.setAccountName(destination.getAccountName());
    this.setBranchName(destination.getBranchName());
    this.setEntryDate(destination.getEntryDate());
    this.setUpdateDate(destination.getUpdateDate());

    return this;
  }
}
