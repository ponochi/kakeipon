//=========================================================
// Load event of window
// @param none
// @return none
//=========================================================
function windowLoad() {
  if (shopId == null) {
    document.getElementById("shopId").value = 0;
  } else {
    document.getElementById("shopId").value = shopId;
  }

  if (accountSourceId == null) {
    document.getElementById("accountSource").value = 0;
  } else {
    document.getElementById("accountSource").value = accountSourceId;
  }

  if (accountDestinationId == null) {
    document.getElementById("accountDestination").value = 0;
  } else {
    document.getElementById("accountDestination").value = accountDestinationId;
  }

}

window.addEventListener("load", windowLoad, false);
