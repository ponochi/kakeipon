var shopId = window.sessionStorage.getItem("shopId");
var accountSourceId = window.sessionStorage.getItem("accountSourceId");
var accountDestinationId = window.sessionStorage.getItem("accountDestinationId");

var url = "url";
var showListUrl = "/spec/showList";
var createGroupUrl = "/spec/create/group";
var editGroupUrl = "/spec/edit/group";

//=========================================================
// Convert boolean value with a type of string literal
// to value of boolean type.
// @param "true" or "false" tyoe of string.
// @return true or false value, type of boolean.
//=========================================================
function toBoolean(boolStr) {
    return boolStr.toLowerCase() === "true";
}

function transitionCreateGroup() {
  var shopId = document.getElementById("shopId").value;
  var accountSourceId = document.getElementById("accountSource").value;
  var accountDestinationId = document.getElementById("accountDestination").value;
  window.sessionStorage.setItem("accountSourceId", accountSourceId);
  window.sessionStorage.setItem("accountDestinationId", accountDestinationId);
  location.href = "/shop/list";
}

function setShowListUrl() {
  window.sessionStorage.setItem([url], [showListUrl]);
}

function setCreateGroupUrl() {
  window.sessionStorage.setItem([url], [createGroupUrl]);
}

function setEditGroupUrl() {
  window.sessionStorage.setItem([url], [editGroupUrl]);
}


//=========================================================
// Load event of window
// @param none
// @return none
//=========================================================
function windowLoad() {
  let balanceType
    = window.sessionStorage.getItem("balanceType");

  let balanceTypesControl
    = document.getElementById("balanceTypes");
  let shopIdControl
    = document.getElementById("shopId");
  let accountSourceIdControl
    = document.getElementById("accountSourceId");
  let accountDestinationIdControl
    = document.getElementById("accountDestinationId");

  if (Number(shopIdControl.value) === 1
    && Number(accountSourceIdControl.value) === 1
    && Number(accountDestinationIdControl.value) === 1) {
    balanceType = 1;
  }

  if (balanceType == null) {
    balanceTypesControl.value = 1;
    window.sessionStorage.setItem("balanceType", 1);
  } else {
    balanceTypesControl.selected = 3;
  }

  changedBalanceType();
}

window.addEventListener("load", windowLoad, false);

window.addEventListener("load", windowLoad, false);
