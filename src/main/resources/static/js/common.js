var shopId = window.sessionStorage.getItem("shopId");
var accountSourceId = window.sessionStorage.getItem("accountSourceId");
var accountDestinationId = window.sessionStorage.getItem("accountDestinationId");

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

//=========================================================
// Load event of window
// @param none
// @return none
//=========================================================
function windowLoad() {
}

window.addEventListener("load", windowLoad, false);
