package org.panda.systems.kakeipon.app.account;

import org.panda.systems.kakeipon.domain.service.common.AccountAndBalanceService;
import org.panda.systems.kakeipon.domain.service.account.AccountDestinationService;
import org.panda.systems.kakeipon.domain.service.account.AccountSourceService;
import org.panda.systems.kakeipon.domain.service.common.ShopService;
import org.panda.systems.kakeipon.domain.service.spec.SpecificationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class AccountController {
  @Autowired
  AccountAndBalanceService accountAndBalanceService;
  @Autowired
  ShopService shopService;
  @Autowired
  SpecificationGroupService specificationGroupService;
  @Autowired
  AccountSourceService accountSourceService;
  @Autowired
  AccountDestinationService accountDestinationService;
}