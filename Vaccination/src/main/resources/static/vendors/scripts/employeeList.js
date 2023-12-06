
function selectAllCheckboxes() {
    var checkboxList = document.querySelectorAll("input[type='checkbox']");
    for (var j = 0; j < checkboxList.length; j++) {
        checkboxList[j].checked = document.getElementById("selectAll").checked;
    }
}

function checkCheckboxes() {
    var checkboxList = document.querySelectorAll("input[type=checkbox]:checked");
    var values = [];

    checkboxList.forEach(function (checkbox) {
        // Kiểm tra xem ô kiểm có phải là "selectAll" hay không
        if (checkbox.id !== "selectAll") {
            values.push(checkbox.value);
        }
    });
    var link = document.getElementById('deleteButton');
    link.href = "/deleteemployee?ids=" + values.join(',');
}
document.getElementById("deleteButton").addEventListener("click", checkCheckboxes);