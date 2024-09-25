//=========================================================================
// Transition showDetail.html to editDetail.html.
// @param void
// @return void
//=========================================================================
function transitToEditDetail() {
  let form = document.getElementById("showDetailForm");
  form.action = "/user/edit";
  form.submit();
}

//=========================================================================
// Set to birthDay field.
// @param void
// @return void
//=========================================================================
function setToBirthDayField() {
  let yearSelect = document.getElementById("yearSelect");
  let monthSelect = document.getElementById("monthSelect");
  let dateSelect = document.getElementById("dateSelect");
  let dt = new Date();
  dt.setFullYear(yearSelect.value.toString());
  dt.setMonth(monthSelect.value.toString());
  dt.setDate(dateSelect.value.toString());
  let newDate = dateSelect.value;
  let mon = monthSelect.value;
  // Fix number of month.
  if ((dt.getMonth() - Number(mon)) === 1) {
    dt.setMonth(dt.getMonth() - 1);
  }

  backupDate = Number(dt.getDate());
  dt.setDate(1);

  // Calculate last date of current month.
  dt.setDate((dt.getDate() - 1).toString());
  let lastDate = dt.getDate();
  dateSelect = document.getElementById("dateSelect");
  Array.from(dateSelect.options).forEach(dt_option => {
    if (Number(dt_option.value) <= lastDate) {
      dt_option.hidden = false;
    }
    if (Number(dt_option.value) > lastDate) {
      dt_option.hidden = true;
    }
  });

  yearSelect = document.getElementById("yearSelect");
  monthSelect = document.getElementById("monthSelect");
  dateSelect = document.getElementById("dateSelect");
  let birthDay = document.getElementById("birthDay");
  mon = monthSelect.value;
  birthDay.value = yearSelect.value
    + '-' + mon
    + '-' + newDate
    + 'T00:00';
  dateSelect.selectedIndex = lastDate - 1;
  Array.from(dateSelect.options).forEach(dt_option => {
    if (Number(dt_option.value) === Number(backupDate)) {
      dt_option.selected = true;
    }
  });
}

//=========================================================================
// Initial settings at year, month, date birthDay of select box.
// @param void
// @return void
//=========================================================================
function setToYYYYMMDDField() {
  let birthDay
    = document.getElementById("birthDay");
  //=========================================================================
  // Processing year
  //=========================================================================
  let yearSelect
      = document.getElementById("yearSelect");
    currentYear = new Date().getFullYear();
    year = new Date(birthDay.value).getFullYear();
    console.log("currentYear: " + currentYear)
    console.log("year: " + year)
  for (let i = 99; i >= 0; i--) {
    let yr_option = document.createElement("option");
    let procYear = currentYear - i;
    yr_option.textContent = procYear.toString();
    yr_option.value = procYear.toString();
    yearSelect.appendChild(yr_option);
    if (procYear === year) {
      yr_option.selected = true;
    }
  }

  //=========================================================================
  // Processing month
  //=========================================================================
  let monthSelect = document.getElementById("monthSelect");
  let month = new Date(birthDay.value).getMonth() + 1;
  Array.from(monthSelect.options).forEach(mt_option => {
    if (Number(mt_option.value) === month) {
      mt_option.selected = true;
    }
  });

  //=========================================================================
  // Processing date
  //=========================================================================
  let dateSelect
    = document.getElementById("dateSelect");
  birthDay
    = new Date(document.getElementById("birthDay").value)

  // Calculate last date of current month.
  let dt1 = new Date(birthDay);
  dt1.setFullYear(dt1.getFullYear());
  dt1.setMonth(dt1.getMonth() + 1);
  backupDate = Number(dt1.getDate());
  dt1.setDate(1)
  dt1.setDate(dt1.getDate() - 1);
  lastDate = dt1.getDate();
  birthDay
    = new Date(document.getElementById("birthDay").value);
  let dt2 = birthDay.getDate();
  Array.from(dateSelect.options).forEach(dt2_option => {
    dt2_option.hidden = Number(dt2_option.value) > lastDate;
    if (Number(dt2_option.value) === dt2) {
      dt2_option.hidden = false;
    }
    if (Number(dt2_option.value) > lastDate) {
      dt2_option.hidden = true;
    } else if (Number(dt2_option.value) <= lastDate) {
      dt2_option.hidden = false;
    }
    if (Number(dt2_option.value) === backupDate) {
      dt2_option.selected = true;
    }
  });
}

//=========================================================================
// Initial settings at year, month, date birthDay of select box.
// @param void
// @return void
//=========================================================================
function setToDefaultYYYYMMDDField() {
  let birthDay
    = document.getElementById("birthDay");
  birthDay.value = "1970-01-01T00:00";
  //=========================================================================
  // Processing year
  //=========================================================================
  let yearSelect
      = document.getElementById("yearSelect"),
    currentYear = new Date().getFullYear(),
    year = new Date(birthDay.value).getFullYear();
  for (let i = 99; i >= 0; i--) {
    let yr_option = document.createElement("option");
    let procYear = currentYear - i;
    yr_option.textContent = procYear.toString();
    yr_option.value = procYear.toString();
    yearSelect.appendChild(yr_option);
    if (procYear === year) {
      yr_option.selected = true;
    }
  }

  //=========================================================================
  // Processing month
  //=========================================================================
  let monthSelect = document.getElementById("monthSelect");
  let month = new Date(birthDay.value).getMonth() + 1;
  Array.from(monthSelect.options).forEach(mt_option => {
    if (Number(mt_option.value) === month) {
      mt_option.selected = true;
    }
  });

  //=========================================================================
  // Processing date
  //=========================================================================
  let dateSelect
    = document.getElementById("dateSelect");
  birthDay
    = new Date(document.getElementById("birthDay").value)

  // Calculate last date of current month.
  let dt1 = new Date(birthDay);
  dt1.setFullYear(dt1.getFullYear());
  dt1.setMonth(dt1.getMonth() + 1);
  dt1.setDate(1)
  dt1.setDate(dt1.getDate() - 1);
  lastDate = dt1.getDate();
  birthDay
    = new Date(document.getElementById("birthDay").value);
  let dt2 = birthDay.getDate();
  Array.from(dateSelect.options).forEach(dt2_option => {
    dt2_option.hidden = Number(dt2_option.value) > lastDate;
    if (Number(dt2_option.value) === dt2) {
      dt2_option.selected = true;
      dt2_option.hidden = false;
    }
    if (Number(dt2_option.value) > lastDate) {
      dt2_option.hidden = true;
    } else if (Number(dt2_option.value) <= lastDate) {
      dt2_option.hidden = false;
    }

  });
}
