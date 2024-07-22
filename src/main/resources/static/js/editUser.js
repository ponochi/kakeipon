//=========================================================
// Initial settings at year birthday of select box.
// @param void
// @return void
//=========================================================
function setBirthdayYear() {
    const year = new Date(
        document.getElementById("hdnBirthday").value)
        .getFullYear();
    const yearSelect = document.getElementById("yearSelect");
    const yearOpts = yearSelect.options;
    Array.from(yearOpts).forEach(option => {
        if (Number(option.value) === year) {
            option.selected = true;
        }
    });
}

//=========================================================
// Initial settings at month birthday of select box.
// @param void
// @return void
//=========================================================
function setBirthdayMonth() {
    let month = new Date(
        document.getElementById("hdnBirthday").value).getMonth();
    month = Number(month) + 1;
    const monthSelect = document.getElementById("monthSelect");
    const monthOpts = monthSelect.options;
    Array.from(monthOpts).forEach(option => {
        if (Number(option.value) === month) {
            option.selected = true;
        }
    });
}

//=========================================================
// Initial settings at date birthday of select box.
// @param void
// @return void
//=========================================================
function setBirthdayDate() {
    let date = new Date(
        document.getElementById("hdnBirthday").value).getDate();
    const dateSelect = document.getElementById("dateSelect");
    const dateOpts = dateSelect.options;
    Array.from(dateOpts).forEach(option => {
        if (Number(option.value) === date) {
            option.selected = true;
        }
    });
}

//=========================================================
// Initialize flag value of can date processing.
// @param void
// @return void
//=========================================================
function resetCanDateProc() {
    const canDateProc = document.getElementById("hdnCanProcDate");
    canDateProc.value = true;
}

window.addEventListener('load', () => {
    resetCanDateProc();
    setBirthdayYear();
    setBirthdayMonth();
    populateDays();
    setBirthdayDate();
});
window.addEventListener('load', resetCanDateProc, false);
window.addEventListener('load', setBirthdayYear, false);
window.addEventListener('load', setBirthdayMonth, false);
window.addEventListener('load', populateDays, false);
window.addEventListener('load', setBirthdayDate, false);

let yearSelect = document.getElementById("yearSelect");
yearSelect.addEventListener('load', () => {
    populateDays();
    setBirthdayDate();
});
yearSelect.addEventListener('load', populateDays, false);
yearSelect.addEventListener('load', setBirthdayDate, false);

let monthSelect = document.getElementById("monthSelect");
monthSelect.addEventListener('load', () => {
    populateDays();
    setBirthdayDate();
});
monthSelect.addEventListener('load', populateDays, false);
monthSelect.addEventListener('load', setBirthdayDate, false);
