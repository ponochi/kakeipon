//=========================================================
// Change event of Receiving and Payment Type
// @param none
// @return none
//=========================================================
function changedReceivingAndPaymentType() {
  let receivingAndPaymentType = document.getElementById("receivingAndPaymentType").value;

  window.sessionStorage.setItem("receivingAndPaymentType", receivingAndPaymentType);

  let accountSourceName = document.getElementById("accountSourceName");
  let accountSourceButton = document.getElementById("accountSourceButton");
  let accountDestinationName = document.getElementById("accountDestinationName");
  let accountDestinationButton = document.getElementById("accountDestinationButton");
  if (receivingAndPaymentType == 1) {
    accountSourceName.disabled = false;
    accountSourceButton.disabled = false;
    accountDestinationName.disabled = true;
    accountDestinationButton.disabled = true;
  } else if (receivingAndPaymentType == 2) {
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

//=========================================================
// Load event of window
// @param none
// @return none
//=========================================================
function windowLoad() {
  let receivingAndPaymentType = window.sessionStorage.getItem("receivingAndPaymentType");
  if (receivingAndPaymentType == null) {
    document.getElementById("receivingAndPaymentType").value = 1;
  } else {
    document.getElementById("receivingAndPaymentType").value = receivingAndPaymentType;
  }
  changedReceivingAndPaymentType();
}

window.addEventListener("load", windowLoad, false);
