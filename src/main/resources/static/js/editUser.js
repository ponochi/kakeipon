//=========================================================================
// Set to birthday field.
// @param void
// @return void
//=========================================================================
function setToBirthdayField() {
  let yearSelect = document.getElementById("yearSelect");
  let monthSelect = document.getElementById("monthSelect");
  let dateSelect = document.getElementById("dateSelect");
  let dt = new Date();
  dt.setFullYear(Number(yearSelect.value));
  dt.setMonth(Number(monthSelect.value));
  dt.setDate(Number(dateSelect.value));
  let newDate = dateSelect.value;
  dt.setDate(1);

  // Calculate last date of current month.
  dt.setDate(dt.getDate() - 1);
  let lastDate = dt.getDate();
  dateSelect = document.getElementById("dateSelect");
  Array.from(dateSelect.options).forEach(option => {
    if (Number(option.value) === lastDate) {
      option.selected = true;
    }
    option.disabled = Number(option.value) > lastDate;
  });

  yearSelect = document.getElementById("yearSelect");
  monthSelect = document.getElementById("monthSelect");
  dateSelect = document.getElementById("dateSelect");
  let birthday = document.getElementById("birthday");
  mon = monthSelect.value;
  birthday.value = yearSelect.value
    + '-' + mon
    + '-' + newDate
    + 'T00:00';
  dateSelect.value = newDate;
}

//=========================================================================
// Initial settings at year, month, date birthday of select box.
// @param void
// @return void
//=========================================================================
function setToYYYYMMDDField() {
  let birthday
    = document.getElementById("birthday");
  //=========================================================================
  // Processing year
  //=========================================================================
  let yearSelect
      = document.getElementById("yearSelect"),
    currentYear = new Date().getFullYear(),
    year = new Date(birthday.value).getFullYear();
  for (let i = 99; i >= 0; i--) {
    let option = document.createElement("option");
    let procYear = currentYear - i;
    option.textContent = procYear.toString();
    option.value = procYear.toString();
    yearSelect.appendChild(option);
    if (procYear === year) {
      option.selected = true;
    }
  }

  //=========================================================================
  // Processing month
  //=========================================================================
  let monthSelect = document.getElementById("monthSelect");
  let month = new Date(birthday.value).getMonth() + 1;
  Array.from(monthSelect.options).forEach(option => {
    if (Number(option.value) === month) {
      option.selected = true;
    }
  });

  //=========================================================================
  // Processing date
  //=========================================================================
  let dateSelect
    = document.getElementById("dateSelect");
  birthday
    = new Date(document.getElementById("birthday").value)

  // Calculate last date of current month.
  let dt1 = new Date(birthday);
  dt1.setFullYear(dt1.getFullYear());
  dt1.setMonth(dt1.getMonth() + 1);
  dt1.setDate(1)
  dt1.setDate(dt1.getDate() - 1);
  lastDate = dt1.getDate();
  birthday
    = new Date(document.getElementById("birthday").value);
  let dt2 = birthday.getDate();
  Array.from(dateSelect.options).forEach(option => {
    option.disabled = Number(option.value) > lastDate;
    if (Number(option.value) === dt2) {
      option.selected = true;
    }
  });
}

//=========================================================================
// Initial settings at year, month, date birthday of select box.
// @param void
// @return void
//=========================================================================
function setToDefaultYYYYMMDDField() {
  let birthday
    = document.getElementById("birthday");
  birthday.value = "1970-01-01T00:00";
  //=========================================================================
  // Processing year
  //=========================================================================
  let yearSelect
      = document.getElementById("yearSelect"),
    currentYear = new Date().getFullYear(),
    year = new Date(birthday.value).getFullYear();
  for (let i = 99; i >= 0; i--) {
    let option = document.createElement("option");
    let procYear = currentYear - i;
    option.textContent = procYear.toString();
    option.value = procYear.toString();
    yearSelect.appendChild(option);
    if (procYear === year) {
      option.selected = true;
    }
  }

  //=========================================================================
  // Processing month
  //=========================================================================
  let monthSelect = document.getElementById("monthSelect");
  let month = new Date(birthday.value).getMonth() + 1;
  Array.from(monthSelect.options).forEach(option => {
    if (Number(option.value) === month) {
      option.selected = true;
    }
  });

  //=========================================================================
  // Processing date
  //=========================================================================
  let dateSelect
    = document.getElementById("dateSelect");
  birthday
    = new Date(document.getElementById("birthday").value)

  // Calculate last date of current month.
  let dt1 = new Date(birthday);
  dt1.setFullYear(dt1.getFullYear());
  dt1.setMonth(dt1.getMonth() + 1);
  dt1.setDate(1)
  dt1.setDate(dt1.getDate() - 1);
  lastDate = dt1.getDate();
  birthday
    = new Date(document.getElementById("birthday").value);
  let dt2 = birthday.getDate();
  Array.from(dateSelect.options).forEach(option => {
    option.disabled = Number(option.value) > lastDate;
    if (Number(option.value) === dt2) {
      option.selected = true;
    }
  });
}
