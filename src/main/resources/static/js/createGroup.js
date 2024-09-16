let balanceType
  = window.sessionStorage.getItem("balanceType");


let shopIdControl
  = document.getElementById("shopId");
let accountSourceIdControl
  = document.getElementById("accountSourceId");
let accountDestinationIdControl
  = document.getElementById("accountDestinationId");

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
  let specificationGroupId
    = document.getElementById("specificationGroupId");
  let userId
    = document.getElementById("userId");
  let shopId
    = document.getElementById("shopId");
  let accountSourceId
    = document.getElementById("accountSourceId");
  let accountDestinationId
    = document.getElementById("accountDestinationId");
  let balanceTypeId
    = document.getElementById("balanceType");
  if (shopId.value === 1 && accountSourceId.value === 1 && accountDestinationId.value === 1) {
    balanceType = 1;
    changedBalanceType();
  }
  window.sessionStorage.setItem("balanceeType", balanceTypeId.value);
}

function changedAccountSource() {
  let specificationGroupId
    = document.getElementById("specificationGroupId");
  let userId
    = document.getElementById("userId");
  let shopId
    = document.getElementById("shopId");
  let accountSourceId
    = document.getElementById("accountSourceId");
  let accountDestinationId
    = document.getElementById("accountDestinationId");
  let balanceTypeId
    = document.getElementById("balanceType");
  if (shopId.value === 1 && accountSourceId.value === 1 && accountDestinationId.value === 1) {
    balanceType = 1;
    changedBalanceType();
  }
  window.sessionStorage.setItem("balanceeType", balanceTypeId.value);
}

function changedAccountDestination() {
  let specificationGroupId
    = document.getElementById("specificationGroupId");
  let userId
    = document.getElementById("userId");
  let shopId
    = document.getElementById("shopId");
  let accountSourceId
    = document.getElementById("accountSourceId");
  let accountDestinationId
    = document.getElementById("accountDestinationId");
  let balanceTypeId
    = document.getElementById("balanceType");
  if (shopId.value === 1 && accountSourceId.value === 1 && accountDestinationId.value === 1) {
    balanceType = 1;
    changedBalanceType();
  }
  window.sessionStorage.setItem("balanceeType", balanceTypeId.value);
}

//=========================================================
// Change event of Balance Type
// @param none
// @return none
//=========================================================
function changedBalanceType() {

  let searchShop
    = document.getElementById("searchShop");
  let searchAccountSource
    = document.getElementById("searchAccountSource");
  let searchAccountDestination
    = document.getElementById("searchAccountDestination");

  let specificationGroupIdControl
    = document.getElementById("specificationGroupId");
  let accountAndBalanceIdControl
    = document.getElementById("accountAndBalanceId");
  let userIdControl
    = document.getElementById("userId");
  let shopIdControl
    = document.getElementById("shopId");
  let balanceTypeControl
    = document.getElementById("balanceType");
  // let balanceTypeIdControl
  //   = document.getElementById("balanceTypeId");
  let accountSourceIdControl
    = document.getElementById("accountSourceId");
  let accountDestinationIdControl
    = document.getElementById("accountDestinationId");

  window.sessionStorage.setItem(
    "balanceType", balanceTypeControl.selected);

  let accountSourceName
    = document.getElementById("accountSourceName");
  let accountSourceButton
    = document.getElementById("accountSourceButton");
  let accountDestinationName
    = document.getElementById("accountDestinationName");
  let accountDestinationButton
    = document.getElementById("accountDestinationButton");

  if (balanceTypeControl.selected == 1) {
    accountSourceName.disabled = false;
    accountSourceButton.disabled = false;
    accountDestinationName.disabled = true;
    accountDestinationButton.disabled = true;
  } else if (balanceTypeControl.selected == 2) {
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

  // balanceTypeIdControl.selected = balanceTypeControl.selected;

  //==== Set action of search shop form ====//
  searchShop.action = "/" + specificationGroupIdControl.value;
  searchShop.action += "/" + userIdControl.value;
  searchShop.action += "/" + accountAndBalanceIdControl.value;
  if (shopIdControl.value) {
    searchShop.action += "/" + shopIdControl.value;
  } else {
    searchShop.action += "/1";
  }
  if (balanceTypeControl.selected) {
    searchShop.action += "/" + balanceTypeControl.selected;
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
  searchAccountSource.action = "/" + specificationGroupIdControl.value;
  searchAccountSource.action += "/" + userIdControl.value;
  searchAccountSource.action += "/" + accountAndBalanceIdControl.value;
  if (shopIdControl.value) {
    searchAccountSource.action += "/" + shopIdControl.value;
  } else {
    searchAccountSource.action += "/1";
  }
  if (balanceTypeControl.selected) {
    searchAccountSource.action += "/" + balanceTypeControl.selected;
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
  searchAccountDestination.action = "/" + specificationGroupIdControl.value;
  searchAccountDestination.action += "/" + userIdControl.value;
  searchAccountDestination.action += "/" + accountAndBalanceIdControl.value;
  if (shopIdControl.value) {
    searchAccountDestination.action += "/" + shopIdControl.value;
  } else {
    searchAccountDestination.action += "/1";
  }
  if (balanceTypeControl.selected) {
    searchAccountDestination.action += "/" + balanceTypeControl.selected;
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

  let str = taxRate.options[taxRateControl.selectedIndex].innerText;
  let rate = Number(str.substring(0, str.length - 1));
  if (taxTypeControl.value === "1") {
    tax.value
      = Math.round(
        price.value
            * quantity.value
            * rate / 100);
  } else if (taxTypeControl.value === "2") {
    tax.value
      = Math.round(
        price.value
            * quantity.value
            / ((rate + 100) / 100) * (rate / 100));
  } else {
    tax.value = 0;
  }
}

function addSpecDetail() {
  let createSpecDetail
    = document.getElementById(
      "createSpecDetail");
  let specificationGroupId
    = document.getElementById("specificationGroupId");
  let shopIdControl
    = document.getElementById("shopId");
  let balanceTypeControl
    = document.getElementById("balanceType");
  let accountSourceIdControl
    = document.getElementById("accountSourceId");
  let accountDestinationIdControl
    = document.getElementById("accountDestinationId");

  createSpecDetail.action = "/spec/create/detail";
  createSpecDetail.action += "/";
  createSpecDetail.action += specificationGroupId.value;
  createSpecDetail.action += "/";
  createSpecDetail.action += shopIdControl.value;
  createSpecDetail.action += "/";
  createSpecDetail.action += balanceTypeControl.value;
  createSpecDetail.action += "/";
  createSpecDetail.action += accountSourceIdControl.value;
  createSpecDetail.action += "/";
  createSpecDetail.action += accountDestinationIdControl.value;

  createSpecDetail.submit();
}

function savedetail() {
  var form = document.querySelector("saveOfSpecification");
  var action = form.setAttribute("action", "/spec/save");
  document.querySelector("#saveOfSpecification").submit();
  // window.location.method = "GET";
  // window.location.href = "/spec/save";
}

function sessionClear() {
  window.sessionStorage.clear();
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

  balanceTypeControl.selected = 3;
console.log("balanceType: " + balanceType);
  if (!balanceType) {
    balanceTypeControl.selected = 1;
    window.sessionStorage.setItem("balanceType", 1);
  } else {
    balanceTypeControl.selected = balanceType;
  }
  changedBalanceType();
}

window.addEventListener("load", windowLoad, false);
shopIdControl.addEventListener("change", changedShop, false);
accountSourceIdControl.addEventListener("change", changedAccountSource, false);
accountDestinationIdControl.addEventListener("change", changedAccountDestination, false);
