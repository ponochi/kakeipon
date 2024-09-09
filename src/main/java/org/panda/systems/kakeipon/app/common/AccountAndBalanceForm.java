package org.panda.systems.kakeipon.app.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.panda.systems.kakeipon.app.account.AccountDestinationForm;
import org.panda.systems.kakeipon.app.account.AccountSourceForm;
import org.panda.systems.kakeipon.domain.model.account.AccountDestination;
import org.panda.systems.kakeipon.domain.model.account.AccountSource;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

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

  @OneToOne
  @JoinColumn(name = "account_source_id", table = "tbl_account_info",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "account_source_id")
  private AccountSourceForm accountSourceForm;

  @Column(name = "account_destination_id")
  private Long accountDestinationId;

  @OneToOne
  @JoinColumn(name = "account_destination_id", table = "tbl_account_info",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "account_destination_id")
  private AccountDestinationForm accountDestinationForm;

  @PastOrPresent
  @Column
  private LocalDateTime entryDate;

  @Column
  private LocalDateTime updateDate;

  // Default constructor
  public AccountAndBalanceForm() {

  }

  public AccountAndBalanceForm(AccountAndBalanceService accountAndBalanceService,
                               AccountSourceService accountSourceService,
                               AccountDestinationService accountDestinationService,
                               Long accountAndBalanceId,
                               Long accountSourceId,
                               Long accountDestinationId) {

    AccountAndBalance accountAndBalance = new AccountAndBalance();

    this.setAccountAndBalanceId(accountAndBalanceId);

    if (accountSourceId == null) {
      this.setAccountSourceId(Long.parseLong("1"));
    } else {
      this.setAccountSourceId(accountSourceId);
    }
    AccountSourceForm accountSourceForm
        = new AccountSourceForm(
            accountSourceService,
            this.getAccountSourceId());
    this.setAccountSourceForm(accountSourceForm);

    if (accountDestinationId == null) {
      this.setAccountDestinationId(Long.parseLong("1"));
    } else {
      this.setAccountDestinationId(accountDestinationId);
    }
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
            accountDestinationService,
            this.getAccountDestinationId());
    this.setAccountDestinationForm(accountDestinationForm);

    this.setEntryDate(LocalDateTime.now());

    accountAndBalanceService.saveAndFlush(this.toEntity());

    accountAndBalance
        = accountAndBalanceService.getById(
            accountAndBalanceService.getMaxAccountAndBalanceId());

    this.setAccountAndBalanceToForm(accountAndBalance);
  }

  public AccountAndBalance toEntity() {
    AccountAndBalance entity = new AccountAndBalance();

    entity.setAccountAndBalanceId(this.getAccountAndBalanceId());
    entity.setAccountSourceId(this.getAccountSourceId());
    entity.setAccountDestinationId(this.getAccountDestinationId());
    entity.setEntryDate(this.getEntryDate());
    entity.setUpdateDate(this.getUpdateDate());

    return entity;
  }

  public AccountAndBalanceForm setAccountAndBalanceToForm(AccountAndBalance accountAndBalance) {
    AccountAndBalanceForm form = new AccountAndBalanceForm();

    form.setAccountAndBalanceId(accountAndBalance.getAccountAndBalanceId());
    form.setAccountSourceId(accountAndBalance.getAccountSourceId());
    form.setAccountDestinationId(accountAndBalance.getAccountDestinationId());
    form.setEntryDate(accountAndBalance.getEntryDate());
    form.setUpdateDate(accountAndBalance.getUpdateDate());

    return form;
  }

  public AccountAndBalanceForm setAccountAndBalanceToForm(AccountAndBalance accountAndBalance,
                                                         AccountSourceForm accountSourceForm,
                                                         AccountDestinationForm accountDestinationForm) {
    AccountAndBalanceForm form = new AccountAndBalanceForm();

    form.setAccountAndBalanceId(accountAndBalance.getAccountAndBalanceId());
    form.setAccountSourceId(accountAndBalance.getAccountSourceId());
    form.setAccountSourceForm(accountSourceForm);
    form.setAccountDestinationId(accountAndBalance.getAccountDestinationId());
    form.setAccountDestinationForm(accountDestinationForm);
    form.setEntryDate(accountAndBalance.getEntryDate());
    form.setUpdateDate(accountAndBalance.getUpdateDate());

    return form;
  }

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
