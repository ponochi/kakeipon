//=========================================================
// Add number of year options and setting to options value.
// @param void
// @return void
//=========================================================
function populateYears() {
    const yearSelect =
        document.getElementById("yearSelect");
    if (yearSelect) {
        const date = new Date();
        const year = date.getFullYear();

        for (let i = 0; i <= 99; i++) {
            let option
                = document.createElement("option");
            option.textContent = ((year - 99) + i).toString();
            option.value = ((year - 99) + i).toString();
            yearSelect.appendChild(option);
        }
    } else {
        // ToDo: Implements error process
    }
}

//=========================================================
// Add number of date options and setting to options value,
// calculate last day of current year and month
// relationship. Hiding date value option field with after
// last date.
// @param void
// @return void
//=========================================================
function populateDays() {
    // Get 1st. day of next month.
    let newYear = document.getElementById("yearSelect").value;
    let newMonth = document.getElementById("monthSelect").value;
    let dt1 = new Date();
    dt1.setFullYear(Number(newYear));
    dt1.setMonth(newMonth);
    dt1.setDate(1);

    // Get last day of current month.
    let dt2 = new Date();
    dt2.setFullYear(dt1.getFullYear());
    dt2.setMonth(dt1.getMonth());
    dt2.setDate(dt1.getDate() - 1);
    let lastDate = dt2.getDate();

    // Hidden to after of last day.
    let dateSelect = document.getElementById("dateSelect");
    let dateOpts = dateSelect.options;
    Array.from(dateOpts).forEach(option => {
        if (Number(dateSelect.value) > lastDate) {
            if (Number(option.value) === lastDate) {
                option.selected = true;
            }
            dateSelect.value = lastDate;
        }

        option.hidden = Number(option.value) > lastDate;
    });
}
