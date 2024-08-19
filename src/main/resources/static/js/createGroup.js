//=========================================================
// Global variables
//=========================================================
function transitionSearchShop() {
  var shopId = window.sessionStorage.getItem("shopId").value;
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
let searchShopButton = document.getElementById("searchShop");
searchShopButton.addEventListener("click", transitionSearchShop, false);