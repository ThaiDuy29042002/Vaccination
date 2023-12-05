function prepareModal() {
    const checkboxes = document.querySelectorAll('#table-view input[type="checkbox"]:checked');

    if (checkboxes.length > 0) {
        // Set up the modal for deletion confirmation
        $('#notificationModalLabel').text('Confirm Delete');
        $('#notificationModalBody').text('You want to delete this news?');
        $('#confirmDeleteButton').show();
        $('#notificationModal').modal('show');
    } else {
        // Set up the modal to notify the user
        $('#notificationModalLabel').text('Notification');
        $('#notificationModalBody').text('No news selected');
        $('#confirmDeleteButton').hide(); // hide the delete button
        $('#notificationModal').modal('show');
    }
}

$('#notificationModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);

    var deleteButton = $('#confirmDeleteButton');
    deleteButton.attr('onclick', 'deleteMultipleNews()');
});

function deleteMultipleNews() {
    const checkboxes = document.querySelectorAll('#table-view input[type="checkbox"]:checked');

    const idsToDelete = Array.from(checkboxes)
        .map(checkbox => checkbox.value)
        .filter(value => value !== "on" && !isNaN(value)) // This filters out 'on' and any non-numeric values
        .join(',');

    // Delete method
    window.location.href = `/delete/${idsToDelete}`;
}