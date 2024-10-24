let balanceType
    = window.sessionStorage.getItem("balanceType");


const balanceTypeIdControl
    = document.getElementById("balanceTypeId");
const balanceTypeIdInputControl
    = document.getElementById("balanceTypeIdInput");
let trackerBalanceTypeIdInputControl
    = balanceTypeIdInputControl._valueTracker;
const shopIdControl
    = document.getElementById("shopId");
const accountSourceIdControl
    = document.getElementById("accountSourceId");
const accountDestinationIdControl
    = document.getElementById("accountDestinationId");
const saveOrUpdateGroupControl
    = document.getElementById("saveOrUpdateGroup");

let taxTypeIdControl
    = document.getElementById("taxTypeId");
let taxRateIdControl
    = document.getElementById("taxRateId");
let priceControl
    = document.getElementById("price");
let quantityControl
    = document.getElementById("quantity");

function resetBalanceType() {
    balanceTypeIdControl.selectedIndex = 0;
    window.sessionStorage.setItem("balanceType", "0");
    changedBalanceType();
}

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
        = document.getElementById("balanceTypeId");
    if (shopId.value === 1 && accountSourceId.value === 1 && accountDestinationId.value === 1) {
        balanceType = 1;
        changedBalanceType();
    }
    window.sessionStorage.setItem("balanceType", balanceTypeIdControl.value);
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
        = document.getElementById("balanceTypeId");
    if (shopId.value === 1 && accountSourceId.value === 1 && accountDestinationId.value === 1) {
        balanceType = 1;
        changedBalanceType();
    }
    window.sessionStorage.setItem("balanceType", balanceTypeIdControl.value);
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
        = document.getElementById("balanceTypeId");
    if (shopId.value === 1 && accountSourceId.value === 1 && accountDestinationId.value === 1) {
        balanceType = 1;
        changedBalanceType();
    }
    window.sessionStorage.setItem("balanceType", balanceTypeIdControl.value);
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
    let balanceTypeIdControl
        = document.getElementById("balanceTypeId");
    let accountSourceIdControl
        = document.getElementById("accountSourceId");
    let accountDestinationIdControl
        = document.getElementById("accountDestinationId");

    var trackerBalanceTypeIdInputControl
        = balanceTypeIdInputControl._valueTracker;

    if (trackerBalanceTypeIdInputControl) {
        trackerBalanceTypeIdInputControl.setValue(
            balanceTypeIdControl.options[
                balanceTypeIdControl.selectedIndex].value
        );
        window.sessionStorage.setItem(
            "balanceType", balanceTypeIdInputControl.value);
    }

    let accountSourceName
        = document.getElementById("accountSourceName");
    let accountSourceButton
        = document.getElementById("accountSourceButton");
    let accountDestinationName
        = document.getElementById("accountDestinationName");
    let accountDestinationButton
        = document.getElementById("accountDestinationButton");

    balanceTypeIdInputControl.value
        = balanceTypeIdControl.options[
        balanceTypeIdControl.selectedIndex].value;

    if (balanceTypeIdControl.selectedIndex === 1) {
        accountSourceName.disabled = false;
        accountSourceButton.disabled = false;
        accountDestinationName.disabled = true;
        accountDestinationButton.disabled = true;
    } else if (balanceTypeIdControl.selectedIndex === 2) {
        accountSourceName.disabled = true;
        accountSourceButton.disabled = true;
        accountDestinationName.disabled = false;
        accountDestinationButton.disabled = false;
    } else if (balanceTypeIdControl.selectedIndex === 3) {
        accountSourceName.disabled = false;
        accountSourceButton.disabled = false;
        accountDestinationName.disabled = false;
        accountDestinationButton.disabled = false;
    }

    //==== Set action of search shop form ====//
    searchShop.action = "/" + specificationGroupIdControl.value;
    searchShop.action += "/" + userIdControl.value;
    searchShop.action += "/" + accountAndBalanceIdControl.value;
    if (shopIdControl.value) {
        searchShop.action += "/" + shopIdControl.value;
    } else {
        searchShop.action += "/1";
    }
    if (balanceTypeIdControl.selectedIndex) {
        searchShop.action += "/";
        searchShop.action
            += balanceTypeIdControl.options[
            balanceTypeIdControl.selectedIndex].value;
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
    if (balanceTypeIdControl.selectedIndex) {
        searchAccountSource.action += "/";
        searchAccountSource.action
            += balanceTypeIdControl.options[
            balanceTypeIdControl.selectedIndex].value;
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
    if (balanceTypeIdControl.selectedIndex) {
        searchAccountDestination.action += "/";
        searchAccountDestination.action
            += balanceTypeIdControl.options[
            balanceTypeIdControl.selectedIndex].value;
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
    let taxTypeIdControl = document.getElementById("taxTypeId");
    let taxRateIdControl = document.getElementById("taxRateId");

    let price = document.getElementById("price");
    let quantity = document.getElementById("quantity");
    let taxRate = document.getElementById("taxRate");
    let tax = document.getElementById("tax");

    let str = taxRateIdControl.options[taxRateIdControl.selectedIndex].innerText;
    let rate = Number(str.substring(0, str.length - 1));

    if (Number(taxTypeIdControl.value) === 1) {
        tax.value
            = Math.round(
            price.value
            * quantity.value
            * rate / 100);
    } else if (Number(taxTypeIdControl.value) === 2) {
        tax.value
            = Math.round(
            price.value
            * quantity.value
            / ((rate + 100) / 100) * (rate / 100));
    } else {
        tax.value = 0;
    }
}

//
// function addSpecDetail() {
//   let createSpecDetail
//     = document.getElementById(
//       "createSpecDetail");
//   let specificationGroupId
//     = document.getElementById("specificationGroupId");
//   let shopIdControl
//     = document.getElementById("shopId");
//   let balanceTypeIdControl
//     = document.getElementById("balanceTypeId");
//   let accountSourceIdControl
//     = document.getElementById("accountSourceId");
//   let accountDestinationIdControl
//     = document.getElementById("accountDestinationId");
//
//   createSpecDetail.action = "/spec/create/detail";
//   createSpecDetail.action += "/";
//   createSpecDetail.action += specificationGroupId.value;
//   createSpecDetail.action += "/";
//   createSpecDetail.action += shopIdControl.value;
//   createSpecDetail.action += "/";
//   createSpecDetail.action += balanceTypeIdControl.selectedIndex;
//   createSpecDetail.action += "/";
//   createSpecDetail.action += accountSourceIdControl.value;
//   createSpecDetail.action += "/";
//   createSpecDetail.action += accountDestinationIdControl.value;
//
//   createSpecDetail.submit();
// }
//
// function savedetail() {
//   var form = document.querySelector("saveOfSpecification");
//   var action = form.setAttribute("action", "/spec/save");
//   document.querySelector("#saveOfSpecification").submit();
//   // window.location.method = "GET";
//   // window.location.href = "/spec/save";
// }
//
// function sessionClear() {
//   window.sessionStorage.clear();
// }

// function test() {
//   window.sessionStorage.setItem(
//     "balanceType",
//     balanceTypeIdInputControl.value);
//   let balanceType
//     = window.sessionStorage.getItem("balanceType")
//
//   console.log("balanceType in test() : "
//     + balanceType);
//
//   resetBalanceType
//     = window.sessionStorage.getItem("resetBalanceType");
//
//   let url
//     = window.location.href.split('/');
//
//   if (!balanceType) {
//     console.log("A");
//     balanceTypeIdControl.selectedIndex = 1;
//     window.sessionStorage.setItem(
//       "balanceType",
//       balanceTypeIdInputControl.value);
//   } else {
//     // console.log("B");
//     // if (resetBalanceType.toString() == "t") {
//     //   console.log("B*");
//     //   if (url.slice(3, 4) == "spec") {
//     //     if (url.slice(4, 5) == "create") {
//     //       if (url.slice(5, 6) == "group") {
//     //         console.log("B**");
//     //
//     //       }
//     //     } else if ((url.slice(4, 5) == "edit")
//     //       || (url.slice(4, 5) == "update")) {
//     //       if (url.slice(5, 6) == "group") {
//     //         console.log("B***");
//     //         let balanceType
//     //           = window.sessionStorage.getItem("balanceType")
//     //
//     //         console.log("balanceType in test() : "
//     //           + balanceType);
//     //         console.log("resetBalanceType in test() : "
//     //           + resetBalanceType);
//     //         if (resetBalanceType == "t") {
//     //           console.log("B****");
//     //
//     //         }
//     //
//     //       }
//     //     }
//     //   }
//     // } else if (resetBalanceType == "f") {
//     //   console.log("C");
//     //
//     // }
//   }
//   changedBalanceType();
// }

function goBackUrl(accountAndBalanceId, shopId, balanceTypeId, accountSourceId, accountDestinationId) {
    console.log("accountAndBalanceId = " + accountAndBalanceId);

    let urlStr = window.location.href;
    console.log(urlStr);
    const pattern1 = "\/spec\/create\/group";
    const pattern2 = "\/spec\/edit\/group";
    const pattern3 = "\/spec";
    const pattern4 = "\/spec\/create\/detail";
    const pattern5 = "\/spec\/edit\/detail";

    let transitUrlStr = sessionStorage.getItem("url");
    let start = transitUrlStr.indexOf(pattern3);
    let end = transitUrlStr.length;

    // let form = document.createElement('form');
    // form.method = 'post';               //通信方法はPOST
    // form.action = transitUrlStr.substring(start, end);   //情報の送り先URL
    // document.body.appendChild(form);

    console.log("urlStr.indexOf(pattern4) : " + urlStr.indexOf(pattern4));
    if (urlStr.indexOf(pattern4) > -1) {
        let transitUrl = pattern2 +
            "/" + specificationGroupId.value +
            "/" + userId.value +
            "/" + accountAndBalanceId +
            "/" + shopId +
            "/" + balanceTypeId +
            "/" + accountSourceId +
            "/" + accountDestinationId;

        window.location.replace(transitUrl);
//    form.submit();
    }
    if (urlStr.indexOf(pattern5) > -1) {
//    form.submit();
    }
}

//=========================================================
// Load event of window
// @param none
// @return none
//=========================================================
function windowLoad() {
    balanceType
        = window.sessionStorage.getItem("balanceType");
    let receivingAndPaymentDate
        = window.sessionStorage.getItem("receivingAndPaymentDate");
    let receivingAndPaymentTime
        = window.sessionStorage.getItem("receivingAndPaymentTime");

    let balanceTypeIdControl
        = document.getElementById("balanceTypeId");
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

    resetBalanceType
        = window.sessionStorage.getItem("resetBalanceType");

    url
        = window.location.href.split('/');

    trackerBalanceTypeIdInputControl
        = balanceTypeIdInputControl._valueTracker;

    balanceType
        = window.sessionStorage.getItem("balanceType");

    if (!balanceType) {
        if (!balanceTypeIdInputControl.value) {
            balanceTypeIdControl.selectedIndex = 1;
            window.sessionStorage.setItem("balanceType", "1");
        } else {
            balanceTypeIdControl.selectedIndex
                = balanceTypeIdInputControl.value;
            window.sessionStorage.setItem(
                "balanceType", balanceTypeIdInputControl.value);
        }
    } else {
        if (resetBalanceType === "f") {
            balanceTypeIdControl.selectedIndex
                = balanceTypeIdInputControl.value;
            window.sessionStorage.setItem(
                "balanceType", balanceTypeIdInputControl.value);
        } else if (resetBalanceType === "t") {
            balanceTypeIdControl.selectedIndex
                = balanceTypeIdInputControl.value;
            window.sessionStorage.setItem(
                "balanceType", balanceTypeIdInputControl.value);
            window.sessionStorage.setItem("resetBalanceType", "f");
        }
    }

    let urlStr = window.location.href;
    console.log(urlStr);
    const pattern1 = "\/spec\/create\/group\/";
    const pattern2 = "\/spec\/edit\/group\/";
    const pattern3 = "\/spec";
    const pattern4 = "\/spec\/create\/detail\/";
    const pattern5 = "\/spec\/edit\/detail\/";

    console.log(urlStr.indexOf(pattern1));
    if (urlStr.indexOf(pattern1) > -1) {
        sessionStorage.setItem('url', window.location.href);
    } else if (urlStr.indexOf(pattern2) > -1) {
        sessionStorage.setItem('url', window.location.href);
    } else if ((urlStr.lastIndexOf(pattern3) + pattern3.length === urlStr.length)
        && (pattern2.length <= urlStr.length)) {
        sessionStorage.setItem('url', "");
    }

    changedBalanceType();
}

window.addEventListener("load", windowLoad, false);
balanceTypeIdControl.addEventListener("change", changedBalanceType, false);
shopIdControl.addEventListener("change", changedShop, false);
accountSourceIdControl.addEventListener("change", changedAccountSource, false);
accountDestinationIdControl.addEventListener("change", changedAccountDestination, false);

taxTypeIdControl.addEventListener("change", calculateTax, false);
taxRateIdControl.addEventListener("change", calculateTax, false);
priceControl.addEventListener("change", calculateTax, false);
quantityControl.addEventListener("change", calculateTax, false);

// saveOrUpdateGroupControl.addEventListener("click", function () {
//   console.log(window.sessionStorage.getItem('balanceType'));
// }, false);
// saveOrUpdateGroupControl.addEventListener("click", test, false);
