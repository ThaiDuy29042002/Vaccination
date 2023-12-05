document.getElementById("submitButton").addEventListener("click", function() {
    // Lấy giá trị từ input
    var numberOfInjectionInput = document.getElementById("numberOfInjection");
    var numberOfInjectionValue = numberOfInjectionInput.value;

    // Kiểm tra nếu giá trị rỗng hoặc không hợp lệ (ví dụ: không phải số), thì gán giá trị mặc định là 0
    if (numberOfInjectionValue === "" || isNaN(numberOfInjectionValue)) {
        numberOfInjectionInput.value = "0";
    }

    // Gửi dữ liệu lên máy chủ
    document.getElementById("injectionResultForm").submit();
});