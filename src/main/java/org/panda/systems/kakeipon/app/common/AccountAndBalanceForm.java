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

  private final AccountAndBalanceService accountAndBalanceService;
  private final AccountSourceService accountSourceService;
  private final AccountDestinationService accountDestinationService;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @SequenceGenerator(name = "account_and_balance_eq", allocationSize = 1)
  @PositiveOrZero
  @Column(name = "account_and_balance_id")
  private Long accountAndBalanceId;

  @Column(name = "account_source_id")
  private Long accountSourceId;

  @OneToOne
  @JoinColumn(name = "account_source_id", table = "account_info",
      insertable = false, updatable = false)
  @PrimaryKeyJoinColumn
  @Column(name = "account_source_id")
  private AccountSourceForm accountSourceForm;

  @Column(name = "account_destination_id")
  private Long accountDestinationId;

  @OneToOne
  @JoinColumn(name = "account_destination_id", table = "account_info",
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

    this.accountAndBalanceService = null;
    this.accountSourceService = null;
    this.accountDestinationService = null;
  }

  public AccountAndBalanceForm(
      AccountAndBalanceService accountAndBalanceService,
      AccountSourceService accountSourceService,
      AccountDestinationService accountDestinationService) {

    this.accountAndBalanceService = accountAndBalanceService;
    this.accountSourceService = accountSourceService;
    this.accountDestinationService = accountDestinationService;
  }

  public AccountAndBalanceForm setAccountAndBalanceFormAndAccountSourceFormAndAccountDestinationForm(
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
        accountSourceService)
        .setAccountSourceFormByDB(
            this.getAccountSourceId());
    this.setAccountSourceForm(accountSourceForm);

    if (accountDestinationId == null) {
      this.setAccountDestinationId(Long.parseLong("1"));
    } else {
      this.setAccountDestinationId(accountDestinationId);
    }
    AccountDestinationForm accountDestinationForm
        = new AccountDestinationForm(
        accountDestinationService)
        .setAccountDestinationFormByDB(
            this.getAccountDestinationId());
    this.setAccountDestinationForm(accountDestinationForm);

    if (accountAndBalance.getEntryDate() == null) {
      this.setEntryDate(LocalDateTime.now());
      this.setUpdateDate(accountAndBalance.getUpdateDate());
    } else {
      this.setEntryDate(accountAndBalance.getEntryDate());
      this.setUpdateDate(LocalDateTime.now());
    }

    accountAndBalanceService.saveAndFlush(this.toEntity());

    accountAndBalance
        = accountAndBalanceService.getById(
        accountAndBalanceService.getMaxAccountAndBalanceId());

    this.setAccountAndBalanceToForm(accountAndBalance);

    return this;
  }

  public AccountAndBalance toEntity() {
    AccountAndBalance entity = new AccountAndBalance();

    entity.setAccountAndBalanceId(this.getAccountAndBalanceId());
    entity.setAccountSourceId(this.getAccountSourceId());
    entity.setAccountDestinationId(this.getAccountDestinationId());
    if (this.getEntryDate() == null) {
      entity.setEntryDate(LocalDateTime.now());
      entity.setUpdateDate(this.getUpdateDate());
    } else {
      entity.setEntryDate(this.getEntryDate());
      entity.setUpdateDate(LocalDateTime.now());
    }

    return entity;
  }

  public AccountAndBalanceForm setAccountAndBalanceToForm(
      AccountAndBalance accountAndBalance) {

    this.setAccountAndBalanceId(accountAndBalance.getAccountAndBalanceId());
    this.setAccountSourceId(accountAndBalance.getAccountSourceId());
    this.setAccountDestinationId(accountAndBalance.getAccountDestinationId());
    if (accountAndBalance.getEntryDate() == null) {
      this.setEntryDate(LocalDateTime.now());
      this.setUpdateDate(accountAndBalance.getUpdateDate());
    } else {
      this.setEntryDate(accountAndBalance.getEntryDate());
      this.setUpdateDate(LocalDateTime.now());
    }

    return this;
  }

  public AccountAndBalanceForm setAccountAndBalanceToForm(
      AccountAndBalance accountAndBalance,
      AccountSourceForm accountSourceForm,
      AccountDestinationForm accountDestinationForm) {

    this.setAccountAndBalanceId(accountAndBalance.getAccountAndBalanceId());
    this.setAccountSourceId(accountAndBalance.getAccountSourceId());
    this.setAccountSourceForm(accountSourceForm);
    this.setAccountDestinationId(accountAndBalance.getAccountDestinationId());
    this.setAccountDestinationForm(accountDestinationForm);
    if (accountAndBalance.getEntryDate() == null) {
      this.setEntryDate(LocalDateTime.now());
      this.setUpdateDate(accountAndBalance.getUpdateDate());
    } else {
      this.setEntryDate(accountAndBalance.getEntryDate());
      this.setUpdateDate(LocalDateTime.now());
    }

    return this;
  }

  public AccountSourceForm setAccountSourceToForm(AccountSource accountSource) {
    AccountSourceForm form = new AccountSourceForm();

    form.setAccountSourceId(accountSource.getAccountSourceId());
    form.setAccountName(accountSource.getAccountName());
    form.setBranchName(accountSource.getBranchName());
    form.setEntryDate(accountSource.getEntryDate());
    if (accountSource.getEntryDate() == null) {
      form.setEntryDate(LocalDateTime.now());
      form.setUpdateDate(accountSource.getUpdateDate());
    } else {
      form.setEntryDate(accountSource.getEntryDate());
      form.setUpdateDate(LocalDateTime.now());
    }

    return form;
  }

  public AccountDestinationForm setAccountDestinationToForm(
      AccountDestination accountDestination) {
    AccountDestinationForm form = new AccountDestinationForm(
        accountDestinationService);

    form.setAccountDestinationId(accountDestination.getAccountDestinationId());
    form.setAccountName(accountDestination.getAccountName());
    form.setBranchName(accountDestination.getBranchName());
    if (accountDestination.getEntryDate() == null) {
      form.setEntryDate(LocalDateTime.now());
      form.setUpdateDate(accountDestination.getUpdateDate());
    } else {
      form.setEntryDate(accountDestination.getEntryDate());
      form.setUpdateDate(LocalDateTime.now());
    }

    return form;
  }
}
