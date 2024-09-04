package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.app.account.AccountDestinationForm;
import org.panda.systems.kakeipon.app.account.AccountSourceForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountAndBalanceForm implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "tbl_account_and_balance_eq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "account_and_balance_id")
  private Long accountAndBalanceId;

  @Column(name = "account_source_id")
  private Long accountSourceId;

  @Column(name = "account_source_form")
  private AccountSourceForm accountSourceForm;

  @Column(name = "account_destination_id")
  private Long accountDestinationId;

  @Column(name = "account_destination_form")
  private AccountDestinationForm accountDestinationForm;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  public AccountSourceForm setAccountSourceToForm(AccountSource accountSource) {
    AccountSourceForm form = new AccountSourceForm();

    form.setAccountSourceId(accountSource.getAccountSourceId());
    form.setAccountName(accountSource.getAccountName());
    form.setBranchName(accountSource.getBranchName());
    form.setEntryDate(accountSource.getEntryDate());

    return form;
  }

  public AccountDestinationForm setAccountDestinationToForm(
      AccountDestination accountDestination) {
    AccountDestinationForm form = new AccountDestinationForm();

    form.setAccountDestinationId(accountDestination.getAccountDestinationId());
    form.setAccountName(accountDestination.getAccountName());
    form.setBranchName(accountDestination.getBranchName());
    form.setEntryDate(accountDestination.getEntryDate());
    form.setUpdateDate(accountDestination.getUpdateDate());

    return form;
  }
}
