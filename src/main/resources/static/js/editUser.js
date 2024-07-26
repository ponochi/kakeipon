//=========================================================================
// Set to birthday field.
// @param void
// @return void
//=========================================================================
function setToBirthdayField() {
    let yearSelect = document.getElementById("yearSelect");
    let monthSelect = document.getElementById("monthSelect");
    let dateSelect = document.getElementById("dateSelect");
//    let birthdayString = document.getElementById("birthdayString");
    let dt = new Date();
    dt.setFullYear(Number(yearSelect.value));
    dt.setMonth(Number(monthSelect.value));
    dt.setDate(Number(dateSelect.value));
    dt.setDate(1)

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
    let birthdayString = document.getElementById("birthdayString");
    if (monthSelect.value.length === 1) {
        mon = '0' + monthSelect.value;
    } else {
        mon = monthSelect.value;
    }
    birthdayString.value = yearSelect.value
        + '-' + mon
        + '-' + dateSelect.value
        + 'T00:00';
}

//=========================================================================
// Initial settings at year, month, date birthday of select box.
// @param void
// @return void
//=========================================================================
function setToYYYYMMDDField() {
    let birthdayString
        = document.getElementById("birthdayString");
    //=========================================================================
    // Processing year
    //=========================================================================
    let yearSelect
            = document.getElementById("yearSelect"),
        currentYear = new Date().getFullYear(),
        year = new Date(birthdayString.value).getFullYear();
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
    let month = new Date(birthdayString.value).getMonth() + 1;
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
        = new Date(document.getElementById("birthdayString").value)

    // Calculate last date of current month.
    let dt1 = new Date(birthday);
    dt1.setFullYear(dt1.getFullYear());
    dt1.setMonth(dt1.getMonth() + 1);
    dt1.setDate(1)
    dt1.setDate(dt1.getDate() - 1);
    lastDate = dt1.getDate();
    birthday
        = new Date(document.getElementById("birthdayString").value);
    let dt2 = birthday.getDate();
    Array.from(dateSelect.options).forEach(option => {
        option.disabled = Number(option.value) > lastDate;
        if (Number(option.value) === dt2) {
            option.selected = true;
        }
    });
}
