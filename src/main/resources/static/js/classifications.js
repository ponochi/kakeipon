//=========================================================================
// Initial settings at year, month, date birthday of select box.
// @param void
// @return void
//=========================================================================
function setToClassificationsField() {
  //=========================================================================
  // Processing firstClassification
  //=========================================================================
  firstClasses.forEach(firstClass => {
    let fc_option = document.createElement("option");
    fc_option.textContent = firstClass.firstClassName;
    fc_option.value = firstClass.firstClassId;
    firstClassSelect.appendChild(fc_option);
  });

  //=========================================================================
  // Processing secondClassification
  //=========================================================================
  let secondClassSelect
    = document.getElementById("secondClassSelect");

  secondClasses.forEach(secondClass => {
    let sc_option
      = document.createElement("option");
    sc_option.textContent = secondClass.secondClassName;
    sc_option.value = secondClass.secondClassId;
    secondClassSelect.appendChild(sc_option);

    sc_option.hidden = Number(secondClass.firstClassId) !== Number(firstClassSelect.value);
  });

  //=========================================================================
  // Processing thirdClassification
  //=========================================================================
  thirdClasses.forEach(thirdClass => {
    let tc_option
      = document.createElement("option");
    tc_option.textContent = thirdClass.thirdClassName;
    tc_option.value = thirdClass.thirdClassId;
    thirdClassSelect.appendChild(tc_option);

    tc_option.hidden = false;
    if (Number(thirdClass.secondClassId) !== Number(secondClassSelect.value)) {
      tc_option.hidden = true;
    }
  });
}

function setMajorAndMinorExpenseField() {
  //=========================================================================
  // Processing firstClassification
  //=========================================================================
  let firstClassSelect
    = document.getElementById("firstClassSelect");
  let firstClassId = firstClassSelect.selectedIndex + 1;

  //=========================================================================
  // Processing secondClassification
  //=========================================================================
  let secondClassId;

  let secondClassSelect
    = document.getElementById("secondClassSelect");

  let i = 0;
  let getMajorExpensesFlag = true;

  Array.from(secondClasses).forEach(secondClass => {
    if (secondClass.firstClassId !== firstClassId) {
      secondClassSelect.options[i].hidden = true;
    } else {
      if (getMajorExpensesFlag) {
        secondClassSelect.options[i].selected = true;
        secondClassId = secondClass.secondClassId;
        getMajorExpensesFlag = false;
      }
      secondClassSelect.options[i].hidden = false;
    }
    i++;
  });

  //=========================================================================
  // Processing thirdClassification
  //=========================================================================
  let thirdClassSelect
    = document.getElementById("thirdClassSelect");

  let j = 0;
  let getMinorExpensesFlag = true;

  Array.from(thirdClasses).forEach(thirdClass => {
    if (thirdClass.secondClassId !== secondClassId) {
      thirdClassSelect.options[j].hidden = true;
    } else {
      if (getMinorExpensesFlag) {
        thirdClassSelect.options[j].selected = true;
        getMinorExpensesFlag = false;
      }
      thirdClassSelect.options[j].hidden = false;
    }
    j++;
  });
}

function setMinorExpenseField() {
  //=========================================================================
  // Processing secondClassification
  //=========================================================================
  let secondClassSelect
    = document.getElementById("secondClassSelect");
  let secondClassId = secondClassSelect.selectedIndex + 1;

  //=========================================================================
  // Processing thirdClassification
  //=========================================================================
  let thirdClassSelect
    = document.getElementById("thirdClassSelect");

  let j = 0;
  let getMinorExpensesFlag = true;

  Array.from(thirdClasses).forEach(thirdClass => {
    if (thirdClass.secondClassId !== secondClassId) {
      thirdClassSelect.options[j].hidden = true;
    } else {
      if (getMinorExpensesFlag) {
        thirdClassSelect.options[j].selected = true;
        getMinorExpensesFlag = false;
      }
      thirdClassSelect.options[j].hidden = false;
    }
    j++;
  });
}

//=========================================================
// Convert boolean value with a type of string literal
// to value of boolean type.
// @param "true" or "false" type of string.
// @return true or false value, type of boolean.
//=========================================================
function toBoolean(boolStr) {
  return boolStr.toLowerCase() === "true";
}
