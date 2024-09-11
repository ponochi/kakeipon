//=========================================================
// Change event of Receiving and Payment Date
// @param none
// @return none
//=========================================================
function changedReceivingAndPaymentDate() {
  let receivingAndPaymentDate
    = document.getElementById("receivingAndPaymentDate");
  window.sessionStorage.setItem("receivingAndPaymentDate", receivingAndPaymentDate.value);
}

//=========================================================
// Change event of Receiving and Payment Time
// @param none
// @return none
//=========================================================
function changedReceivingAndPaymentTime() {
  let receivingAndPaymentTime = document.getElementById("receivingAndPaymentTime");
  window.sessionStorage.setItem("receivingAndPaymentTime", receivingAndPaymentTime.value);
}

function changedShop() {
  let user
    = document.getElementById("userId");
  let shop
    = document.getElementById("shopId");
  let accountSource
    = document.getElementById("accountSourceId");
  let accountDestination
    = document.getElementById("accountDestinationId");
  let receivingAndPaymentType
    = document.getElementById("balanceType");
  if (shop.value === 1 && accountSource.value === 1 && accountDestination.value === 1) {
    balanceType = 1;
    changedBalanceType();
  }
  window.sessionStorage.setItem("balanceeType", balanceType);
}

function changedAccountSource() {
  let user
    = document.getElementById("userId");
  let shop
    = document.getElementById("shopId");
  let accountSource
    = document.getElementById("accountSourceId");
  let accountDestination
    = document.getElementById("accountDestinationId");
  let balanceType
    = document.getElementById("balanceType");
  if (shop.value === 1 && accountSource.value === 1 && accountDestination.value === 1) {
    balanceType = 1;
    changedBalanceType();
  }
  window.sessionStorage.setItem("balanceType", balanceType);
}

function changedAccountDestination() {
  let user
    = document.getElementById("userId");
  let shop
    = document.getElementById("shopId");
  let accountSource
    = document.getElementById("accountSourceId");
  let accountDestination
    = document.getElementById("accountDestinationId");
  let balanceType
    = document.getElementById("balanceType");
  if (shop.value === 1 && accountSource.value === 1 && accountDestination.value === 1) {
    balanceType = 1;
    changedBalanceType();
  }
  window.sessionStorage.setItem("balanceType", balanceType);
}

//=========================================================
// Change event of Balance Type
// @param none
// @return none
//=========================================================
function changedBalanceType() {
  let balanceTypeControl
    = document.getElementById("balanceType");
  let balanceTypeIdControl
    = document.getElementById("balanceTypeId");

  let searchShop
    = document.getElementById("searchShop");
  let searchAccountSource
    = document.getElementById("searchAccountSource");
  let searchAccountDestination
    = document.getElementById("searchAccountDestination");

  let userIdControl
    = document.getElementById("userId");
  let shopIdControl
    = document.getElementById("shopId");
  let accountSourceIdControl
    = document.getElementById("accountSourceId");
  let accountDestinationIdControl
    = document.getElementById("accountDestinationId");
  let accountAndBalanceIdControl
    = document.getElementById("accountAndBalanceId");

  window.sessionStorage.setItem(
    "balanceType", balanceTypeControl.value);

  let accountSourceName
    = document.getElementById("accountSourceName");
  let accountSourceButton
    = document.getElementById("accountSourceButton");
  let accountDestinationName
    = document.getElementById("accountDestinationName");
  let accountDestinationButton
    = document.getElementById("accountDestinationButton");

  if (balanceTypeControl.value == 1) {
    accountSourceName.disabled = false;
    accountSourceButton.disabled = false;
    accountDestinationName.disabled = true;
    accountDestinationButton.disabled = true;
  } else if (balanceTypeControl.value == 2) {
    accountSourceName.disabled = true;
    accountSourceButton.disabled = true;
    accountDestinationName.disabled = false;
    accountDestinationButton.disabled = false;
  } else {
    accountSourceName.disabled = false;
    accountSourceButton.disabled = false;
    accountDestinationName.disabled = false;
    accountDestinationButton.disabled = false;
  }

  balanceTypeIdControl.value = balanceTypeControl.value;

  //==== Set action of search shop form ====//
  searchShop.action = "/" + userIdControl.value;
  searchShop.action += "/" + accountAndBalanceIdControl.value;
  if (shopIdControl.value) {
    searchShop.action += "/" + shopIdControl.value;
  } else {
    searchShop.action += "/1";
  }
  if (accountSourceIdControl.value) {
    searchShop.action += "/" + accountSourceIdControl.value;
  } else {
    searchShop.action += "/1";
  }
  if (accountDestinationIdControl.value) {
    searchShop.action += "/" + accountDestinationIdControl.value;
  } else {
    searchShop.action += "/1";
  }
  searchShop.action += "/searchShop";

  //==== Set action of search account source form ====//
  searchAccountSource.action = "/" + userIdControl.value;
  searchAccountSource.action += "/" + accountAndBalanceIdControl.value;
  if (shopIdControl.value) {
    searchAccountSource.action += "/" + shopIdControl.value;
  } else {
    searchAccountSource.action += "/1";
  }
  if (accountSourceIdControl.value) {
    searchAccountSource.action += "/" + accountSourceIdControl.value;
  } else {
    searchAccountSource.action += "/1";
  }
  if (accountDestinationIdControl.value) {
    searchAccountSource.action += "/" + accountDestinationIdControl.value;
  } else {
    searchAccountSource.action += "/1";
  }
  searchAccountSource.action += "/searchAccountSource";

  //==== Set action of search account destination form ====//
  searchAccountDestination.action = "/" + userIdControl.value;
  searchAccountDestination.action += "/" + accountAndBalanceIdControl.value;
  if (shopIdControl.value) {
    searchAccountDestination.action += "/" + shopIdControl.value;
  } else {
    searchAccountDestination.action += "/1";
  }
  if (accountSourceIdControl.value) {
    searchAccountDestination.action += "/" + accountSourceIdControl.value;
  } else {
    searchAccountDestination.action += "/1";
  }
  if (accountDestinationIdControl.value) {
    searchAccountDestination.action += "/" + accountDestinationIdControl.value;
  } else {
    searchAccountDestination.action += "/1";
  }
  searchAccountDestination.action += "/searchAccountDestination";
}

function getDate(now) {
  return now.getFullYear() + "-"
    + ("0" + (now.getMonth() + 1)).slice(-2) + "-"
    + ("0" + now.getDate()).slice(-2);
}

function getTime(now) {
  return ("0" + now.getHours()).slice(-2) + ":"
    + ("0" + now.getMinutes()).slice(-2);
}

function calculateTax() {
  let taxTypeControl = document.getElementById("taxType");
  let taxRateControl = document.getElementById("taxRate");

  let price = document.getElementById("price");
  let quantity = document.getElementById("quantity");
  let taxRate = document.getElementById("taxRate");
  let tax = document.getElementById("tax");

  if (taxTypeControl.value === "1") {
    let str = taxRate.options[taxRateControl.selectedIndex].innerText;
    tax.value = Math.round(price.value * quantity.value * Number(str.substring(0, str.length - 1)) / 100);
  } else if (taxTypeControl.value === "2") {
    let str = taxRate.options[taxRateControl.selectedIndex].innerText;
    let rate = Number(str.substring(0, str.length - 1));
    tax.value
      = Math.round(
        price.value
            * quantity.value
            / ((rate + 100) / 100) * (rate / 100));
  } else {
    tax.value = 0;
  }
}

function addSpecificationDetail() {
  let createSpecDetail
    = document.getElementById(
      "createSpecDetail");
  createSpecDetail.action = "/spec/create/detail/"
    + groupId;
  createSpecDetail.submit();
}

function savedetail() {
  var form = document.querySelector("saveOfSpecification");
  var action = form.setAttribute("action", "/spec/save");
  document.querySelector("#saveOfSpecification").submit();
  // window.location.method = "GET";
  // window.location.href = "/spec/save";
}

//=========================================================
// Load event of window
// @param none
// @return none
//=========================================================
function windowLoad() {
  let balanceType
    = window.sessionStorage.getItem("balanceType");
  let receivingAndPaymentDate
    = window.sessionStorage.getItem("receivingAndPaymentDate");
  let receivingAndPaymentTime
    = window.sessionStorage.getItem("receivingAndPaymentTime");

  let balanceTypeControl
    = document.getElementById("balanceType");
  let receivingAndPaymentDateControl
    = document.getElementById("receivingAndPaymentDate");
  let receivingAndPaymentTimeControl
    = document.getElementById("receivingAndPaymentTime");
  let shopIdControl
    = document.getElementById("shopId");
  let accountSourceIdControl
    = document.getElementById("accountSourceId");
  let accountDestinationIdControl
    = document.getElementById("accountDestinationId");

  let now = new Date();
  let nowDate = getDate(now);
  let nowTime = getTime(now);

  if (receivingAndPaymentDateControl.validity.valid) {
    receivingAndPaymentDate
      = window.sessionStorage.getItem("receivingAndPaymentDate");

    if (!receivingAndPaymentDate) {
      receivingAndPaymentDateControl.value = nowDate;
      window.sessionStorage.setItem("receivingAndPaymentDate", nowDate);
    } else {
      receivingAndPaymentDateControl.value = receivingAndPaymentDate;
    }
  }

  if (receivingAndPaymentTimeControl.validity.valid) {
    receivingAndPaymentTime
      = window.sessionStorage.getItem("receivingAndPaymentTime");

    if (!receivingAndPaymentTime) {
      receivingAndPaymentTimeControl.value = nowTime;
      window.sessionStorage.setItem("receivingAndPaymentTime", nowTime);
    } else {
      receivingAndPaymentTimeControl.value = receivingAndPaymentTime;
    }
  }

  if (Number(shopIdControl.value) === 1
    && Number(accountSourceIdControl.value) === 1
    && Number(accountDestinationIdControl.value) === 1) {
    balanceType = 1;
  }

  if (balanceType == null) {
    balanceTypeControl.value = 1;
    window.sessionStorage.setItem("balanceType", 1);
  } else {
    balanceTypeControl.value = balanceType;
  }
  changedBalanceType();
}

window.addEventListener("load", windowLoad, false);
shop.addEventListener("change", changedShop, false);
accountSource.addEventListener("change", changedAccountSource, false);
accountDestination.addEventListener("change", changedAccountDestination, false);
