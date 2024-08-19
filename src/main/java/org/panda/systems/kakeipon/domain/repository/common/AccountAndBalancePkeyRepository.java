package org.panda.systems.kakeipon.domain.repository.common;

import org.panda.systems.kakeipon.domain.model.common.AccountAndBalance;
import org.panda.systems.kakeipon.domain.model.common.AccountAndBalancePkey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAndBalancePkeyRepository
    extends JpaRepository<AccountAndBalance, AccountAndBalancePkey> {
}
