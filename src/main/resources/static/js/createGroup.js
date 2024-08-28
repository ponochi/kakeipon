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
  let shop
    = document.getElementById("shopId");
  let accountSource
    = document.getElementById("accountSourceId");
  let accountDestination
    = document.getElementById("accountDestinationId");
  let receivingAndPaymentType
    = document.getElementById("receivingAndPaymentType");
  if (shop.value === 1 && accountSource.value === 1 && accountDestination.value === 1) {
    receivingAndPaymentType = 1;
    changedReceivingAndPaymentType();
  }
  window.sessionStorage.setItem("receivingAndPaymentType", receivingAndPaymentType);
}

function changedAccountSource() {
  let shop
    = document.getElementById("shopId");
  let accountSource
    = document.getElementById("accountSourceId");
  let accountDestination
    = document.getElementById("accountDestinationId");
  let receivingAndPaymentType
    = document.getElementById("receivingAndPaymentType");
  if (shop.value === 1 && accountSource.value === 1 && accountDestination.value === 1) {
    receivingAndPaymentType = 1;
    changedReceivingAndPaymentType();
  }
  window.sessionStorage.setItem("receivingAndPaymentType", receivingAndPaymentType);
}

function changedAccountDestination() {
  let shop = document.getElementById("shopId");
  let accountSource
    = document.getElementById("accountSourceId");
  let accountDestination
    = document.getElementById("accountDestinationId");
  let receivingAndPaymentType
    = document.getElementById("receivingAndPaymentType");
  if (shop.value === 1 && accountSource.value === 1 && accountDestination.value === 1) {
    receivingAndPaymentType = 1;
    changedReceivingAndPaymentType();
  }
  window.sessionStorage.setItem("receivingAndPaymentType", receivingAndPaymentType);
}

//=========================================================
// Change event of Receiving and Payment Type
// @param none
// @return none
//=========================================================
function changedReceivingAndPaymentType() {
  let receivingAndPaymentTypeControl
    = document.getElementById("receivingAndPaymentType");

  window.sessionStorage.setItem(
    "receivingAndPaymentType", receivingAndPaymentTypeControl.value);

  let accountSourceName
    = document.getElementById("accountSourceName");
  let accountSourceButton
    = document.getElementById("accountSourceButton");
  let accountDestinationName
    = document.getElementById("accountDestinationName");
  let accountDestinationButton
    = document.getElementById("accountDestinationButton");

  if (receivingAndPaymentTypeControl.value == 1) {
    accountSourceName.disabled = false;
    accountSourceButton.disabled = false;
    accountDestinationName.disabled = true;
    accountDestinationButton.disabled = true;
  } else if (receivingAndPaymentTypeControl.value == 2) {
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

//=========================================================
// Load event of window
// @param none
// @return none
//=========================================================
function windowLoad() {
  let receivingAndPaymentType
    = window.sessionStorage.getItem("receivingAndPaymentType");
  let receivingAndPaymentDate
    = window.sessionStorage.getItem("receivingAndPaymentDate");
  let receivingAndPaymentTime
    = window.sessionStorage.getItem("receivingAndPaymentTime");

  let receivingAndPaymentTypeControl
    = document.getElementById("receivingAndPaymentType");
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
    receivingAndPaymentType = 1;
  }

  if (receivingAndPaymentType == null) {
    receivingAndPaymentTypeControl.value = 1;
    window.sessionStorage.setItem("receivingAndPaymentType", 1);
  } else {
    receivingAndPaymentTypeControl.value = receivingAndPaymentType;
  }
  changedReceivingAndPaymentType();
}

window.addEventListener("load", windowLoad, false);
shop.addEventListener("change", changedShop, false);
accountSource.addEventListener("change", changedAccountSource, false);
accountDestination.addEventListener("change", changedAccountDestination, false);
