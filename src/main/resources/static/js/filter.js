document.addEventListener("DOMContentLoaded", function() {
    const searchByMileageForm = document.getElementById("searchByMileageForm");
    const searchByMileageResultsDiv = document.getElementById("searchByMileageResults");

    searchByMileageForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const formData = new FormData(searchByMileageForm);

        fetch('/offers/searchByMileage?' + new URLSearchParams(formData).toString(), {
            method: 'GET',
        })
        .then(response => response.text())
        .then(html => {
            searchByMileageResultsDiv.innerHTML = html;
        })
        .catch(error => console.error(error));
    });
});


document.addEventListener("DOMContentLoaded", function() {
    const searchForm = document.getElementById("searchForm");
    const searchResultsDiv = document.getElementById("searchResults");

    searchForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const formData = new FormData(searchForm);

        fetch('/offers/searchByYear?' + new URLSearchParams(formData).toString(), {
            method: 'GET',
        })
        .then(response => response.text())
        .then(html => {
            searchResultsDiv.innerHTML = html;
        })
        .catch(error => console.error(error));
    });
});


