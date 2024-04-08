/**
 * This function is called to all pages where the user is notified
 * of a successful operation or a failed operation.
 */
function redirectAfterDelay() {
    setTimeout(function () {
        window.location.href = '/';
    }, 3000);
}

/**
 * checkPasswordMatch() is called when the user types in the password fields.
 * It checks if the passwords match and if they do, it checks if the password
 * is valid. If the password is valid, the registration-validation element is removed.
 * If the password is not valid, the registration-validation element is displayed.
 * If the passwords do not match, an error message is displayed.
 * If the password is not at least 5 characters long, a message is displayed.
 * If the password does not contain at least one lowercase letter, a message is displayed.
 * If the password does not contain at least one uppercase letter, a message is displayed.
 * If the password does not contain at least one number, a message is displayed.
 * 
 */
function checkPasswordMatch() {
    let password1 = document.getElementById("password1").value;
    let password2 = document.getElementById("password2").value;
    let element = document.getElementById("registration-validation");

    if (password1 !== password2) {
        errorMessage.textContent = 'Passwords do not match';
        element.scrollIntoView({ behavior: 'smooth', block: 'center' });
        return false;
    } else {
        errorMessage.textContent = '';
    }

    // Check password validity
    if (password1.length >= 5) {
        validityMessageLength.textContent = '';
    } else {
        validityMessageLength.textContent = 'Password must be at least 5 characters long';
        element.scrollIntoView({ behavior: 'smooth', block: 'center' });
        return false;
    }

    if (/[a-z]/.test(password1)) {
        validityMessageLowerCase.textContent = '';
    } else {
        validityMessageLowerCase.textContent = 'Password must contain at least one lowercase letter';
        element.scrollIntoView({ behavior: 'smooth', block: 'center' });
        return false;
    }

    if (/[A-Z]/.test(password1)) {
        validityMessageUpperCase.textContent = '';
    } else {
        validityMessageUpperCase.textContent = 'Password must contain at least one uppercase letter';
        element.scrollIntoView({ behavior: 'smooth', block: 'center' });
        return false;
    }

    if (/[0-9]/.test(password1)) {
        validityMessageNumber.textContent = '';

    } else {
        validityMessageNumber.textContent = 'Password must contain at least one number';
        element.scrollIntoView({ behavior: 'smooth', block: 'center' });
        return false;
    }
    element.style.display = 'none';
    return true;
}

/**
 * togglePasswordVisibility() is called when the checkbox is clicked.
 * If the checkbox is checked, the password is displayed, otherwise it is hidden.
 */
function togglePasswordVisibility() {
    let password1 = document.getElementById('password1');
    let password2 = document.getElementById('password2');
    let showPasswordCheckbox = document.getElementById('showPassword');
    if (showPasswordCheckbox.checked) {
        password1.type = 'text';
        password2.type = 'text';
    } else {
        password1.type = 'password';
        password2.type = 'password';
    }
}


document.addEventListener('DOMContentLoaded', function () {
    const dateTimeRangeInput = document.getElementById('dateTimeRange');
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    const startTimeInput = document.getElementById('startTime');
    const endTimeInput = document.getElementById('endTime');

    flatpickr(dateTimeRangeInput, {
        enableTime: true,
        time_24hr: false, // Use 24-hour time format
        mode: 'range', // Enable range mode for selecting a date and time range
        dateFormat: "Y-m-d H:i",
        onClose: function (selectedDates, dateStr, instance) {
            // Update input labels with selected date and time range values
            if (selectedDates.length === 2) {
                // Get the date parts
                let startDate = selectedDates[0];
                let endDate = selectedDates[1];

                // Format date parts with leading zero for single-digit days
                let startDateStr = formatDate(startDate);
                let endDateStr = formatDate(endDate);

                startDateInput.value = startDateStr;
                endDateInput.value = endDateStr;

                let startTime = selectedDates[0];
                let endTime = selectedDates[1];

                let startTimeFormatted = startTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: false });
                let endTimeFormatted = endTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', hour12: false });

                if (startTimeFormatted.substring(0, 2) === '24') {
                    startTimeInput.value = '00' + startTimeFormatted.substring(2);
                }
                else {
                    startTimeInput.value = startTimeFormatted;
                }

                if (endTimeFormatted.substring(0, 2) === '24') {
                    endTimeInput.value = '00' + endTimeFormatted.substring(2);
                }
                else {
                    endTimeInput.value = endTimeFormatted;
                }

            } else {
                startDateInput.value = '';
                endDateInput.value = '';
                startTimeInput.value = '';
                endTimeInput.value = '';
            }
        }
    });

    function formatDate(date) {
        let year = date.getFullYear();
        let month = ('0' + (date.getMonth() + 1)).slice(-2); // Add leading zero to month if needed
        let day = ('0' + date.getDate()).slice(-2); // Add leading zero to day if needed
        return year + '-' + month + '-' + day;
    }
});

//code for creating poll
function addMedium() {
    let mediumName = document.getElementById("mediumInput").value.trim();
    if (mediumName == "") {
        return;
    }
    let par = document.getElementById("mediumsList");
    let node = document.createElement("p");
    node.classList.add('singleMedium');
    if (document.getElementById("mediumRemote").checked) {
        node.innerHTML = "(R) " + mediumName;
    }
    else {
        node.innerHTML = mediumName;
    }
    node.onclick = function () {
        this.parentElement.removeChild(this);

    };
    document.getElementById("mediumInput").value = "";
    par.appendChild(node);
}

function addMediumsUsersToForm() {
    //add mediums
    let mediums = document.getElementsByClassName("singleMedium");
    if (mediums.length == 0) {
        alert("Please add a medium");
        return;
    }
    let users = document.getElementsByClassName("singleUser");
    if (users.length == 0) {
        alert("Please add a user");
        return;
    }
    if (document.getElementById("startTime").value >= document.getElementById("endTime").value) {
        alert("Please make your start time before your end time");
        return;
    }
    if (document.getElementById("startDate").value >= document.getElementById("endDate").value) {
        alert("Please make your start date before your end date");
        return;
    }
    // add from our list
    for (var i = 0; i < mediums.length; i++) {
        let node = document.createElement("input");
        node.name = "m" + i;
        node.value = mediums[i].innerHTML;// "(r) __" or "___"
        node.hidden = true;
        document.getElementById("inputField").append(node);
    }

    //add users
    for (var i = 0; i < users.length; i++) {
        let node = document.createElement("input");
        node.name = "u" + i;
        node.value = users[i].innerHTML;// "(x) fName lName"
        node.hidden = true;
        document.getElementById("inputField").append(node);
    }


    //send
    document.getElementById("inputField").requestSubmit();
}

function getUsersList() {
    fetch('./userAll', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => updateAll(data));

}

function updateAll(data) {

    var div = document.getElementById("allUsersList");
    var html = '';

    // Assuming data is an array of objects with properties you want to display
    data.forEach(function (item) {
        html += '<div>';
        html += '<p>UID: ' + item.uid + '</p>';
        html += '<p>Name: ' + item.firstName + '</p>';
        html += '<p>Age: ' + item.lastName + '</p>';
        // Add more properties as needed
        html += '</div>';
    });

    div.innerHTML = html;
}

function getUsers() {
    //get partial search
    let search = document.getElementById("usersInput").value.trim();
    if (search == "") {
        document.getElementById("usersSearchResults").replaceChildren();
        return;
    }
    //send partial search
    fetch('./userSearch', {
        method: 'POST',
        body: search,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => updateUsers(data));
}

function updateUsers(data) {
    //update list

    //clear old list
    document.getElementById("usersSearchResults").replaceChildren();

    //add search results
    for (let i = 0; i < data.length; i++) {
        if (document.getElementById("uid" + data[i].uid) != null) {
            //already on page somewhere (in search res, or selected)
            continue;
        }
        let par = document.getElementById("usersSearchResults");
        let node = document.createElement('p');

        node.classList.add('singleUser');
        node.id = "uid" + data[i].uid;
        node.innerHTML = "(" + data[i].uid + ") " + data[i].firstName + " " + data[i].lastName;

        par.classList.add('show');

        //on select
        node.onclick = function () {
            //clear search results
            document.getElementById("usersSearchResults").replaceChildren();
            //clear search box
            document.getElementById("usersInput").value = "";

            //add to selected list
            document.getElementById("usersList").appendChild(node);

            //make removeable
            node.onclick = function () {
                this.parentElement.removeChild(this);
            }
        };
        par.appendChild(node);
    }
    // This is to hide the dropdown Searchbox once the user clicks outside of it.
    window.onclick = function (event) {
        if (!event.target.matches('#userInput')) {
            var dropdowns = document.getElementsByClassName('dropdown-content');
            for (var i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
}

// Function to display the active tab content on page load
function displayActiveTab() {
    // Get the active tab from sessionStorage
    var activeTabId = sessionStorage.getItem("active");

    // If there's an active tab, display its content
    if (activeTabId) {
        openTab(activeTabId);
    } else {
        // If no active tab is set, default to displaying the first tab
        openTab("pending");
    }
}

// Call the displayActiveTab function when the page loads
window.addEventListener('load', displayActiveTab);

function refresh() {
    location.reload();
}

/**
 * Parases the datetime string and returns a date object.
 * @param {*} datetime The datetime string to parse.
 * @returns Date object with only the date part.
 */
function getDatePart(datetime) {
    return new Date(datetime.getFullYear(), datetime.getMonth(), datetime.getDate());
}

/**
 * Parases the datetime string and returns a time object.
 * @param {*} datetime  The datetime string to parse.
 * @returns Date object with only the time part.
 */
function getTimePart(datetime) {
    return new Date(datetime.getHours(), datetime.getMinutes(), datetime.getSeconds());
}

/**
 * Loads the table with the given start and end date.
 * Allows the user to select multiple cells by clicking and dragging the mouse.
 */
function tableOnLoad(timeArray) {
    let start = new Date(document.getElementById('startDate').value);
    let end = new Date(document.getElementById('endDate').value);
    let isMouseDown = false;
    
    // Get all tables
    let tables = document.querySelectorAll('[id^="timeBlocks"]');
    // Loop over each table
    for (let i = 0; i < tables.length; i++) {
        let table = tables[i];
        let headerRow = document.createElement('tr');

        // Create a header for each day
        for (let day = new Date(start.getTime()); day <= end; day.setUTCDate(day.getUTCDate() + 1)) {
            let dateHeader = document.createElement('th');
            dateHeader.textContent = (day.getUTCMonth() + 1) + '/' + day.getUTCDate(); // Display only month and day
            headerRow.appendChild(dateHeader);
        }
        table.appendChild(headerRow);

        // Create a row for each half-hour block
        let startTime = new Date(start.getTime());
        let endTime = new Date(start.getTime());
        endTime.setUTCHours(end.getUTCHours(), end.getUTCMinutes()); // Set the hours and minutes to match the end time

        while (startTime <= endTime) { // Adjust this condition to set the end time
            let row = document.createElement('tr');

            // Create a cell for each day
            for (let day = new Date(start.getTime()); day <= end; day.setUTCDate(day.getUTCDate() + 1)) {
                let cell = document.createElement('td');
                cell.textContent = startTime.getUTCHours().toString().padStart(2, '0') + ":" + startTime.getUTCMinutes().toString().padStart(2, '0');
                cell.style.border = '1px solid black'; // Add border to each cell

                cell.addEventListener('mousedown', function () { // Add mousedown event listener
                    isMouseDown = true;
                    this.classList.toggle('selected'); // Toggle 'selected' class
                    return false; // Prevent text selection
                });

                cell.addEventListener('mouseover', function () { // Add mouseover event listener
                    if (isMouseDown) {
                        this.classList.toggle('selected'); // Toggle 'selected' class
                    }
                });

                cell.addEventListener('mouseup', function () { // Add mouseup event listener
                    if (isMouseDown) {
                        isMouseDown = false;
                    }
                });
                row.appendChild(cell);
            }
            table.appendChild(row);
            startTime.setUTCMinutes(startTime.getUTCMinutes() + 30);
        }
    }
    populateCells(timeArray);
};

/**
 * Populates the cells in the table with the selected times.
 */
function populateCells(array) {
    if (array == null) {
        return;
    }
    const string = array;
    const allTables = document.querySelectorAll('[id^="timeBlocks"]');
    allTables.forEach((table, index) => {
        const numColumns = table.rows[0].cells.length; // Get the number of columns

        for (let j = 0; j < numColumns; j++) { // Iterate over each column
            for (let k = 1; k < table.rows.length; k++) { // Iterate over each row
                const cell = table.rows[k].cells[j];
                
                if (string[index][((table.rows.length - 1) * j) + (k - 1)] === '1') {
                    cell.classList.add('selected');
                }
            }
        }
    });
}

/**
 * Finalizes the poll by converting the selected cells into a string.
 */
function finalizePoll() {
    let responses = [];
    let tableArray = [];
    let allTables = document.querySelectorAll('[id^="timeBlocks"]');

    /**
     * Convert the NodeList to an array.
     */
    allTables.forEach(element => {
        tableArray.push(element);
    });

    // Iterate over each table
    for (let i = 0; i < tableArray.length; i++) {
        let response = {
            uid: Number,
            mid: Number,
            pid: Number,
            availabletime: String,
            remote: Boolean,
            medium: String,
        }
        let table = tableArray[i]; // Get the table
        let numColumns = table.rows[0].cells.length; // Get the number of columns
        let selectedCells = "";
    
        for (let j = 0; j < numColumns; j++) { // Iterate over each column
            for (let k = 1; k < table.rows.length; k++) { // Iterate over each row
                let cell = table.rows[k].cells[j];
                if (cell.classList.contains('selected')) {
                    selectedCells += '1';
                } else {
                    selectedCells += '0';
                }
            }
        }

        // Add the response to the responses array
        response['uid'] = Number(table.getAttribute('data-uid'));
        response['mid'] = Number(table.getAttribute('data-mid'));
        response['pid'] = Number(table.getAttribute('data-pid'));
        response['available_time'] = selectedCells;
        response['remote'] = Boolean(table.getAttribute('data-remote'));
        response['medium'] = table.getAttribute('data-name');
        responses.push(response);
    }
    fetch(`/poll/respond`, {
        // Sends a POST request to the PostControllerRest.java
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(responses),
    })
    .then(response => {
        // If the response is not ok, throw an error
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    })
    .then(data => {
        // If the response is ok, redirect to the success page
        console.log('Success:', data);
        window.location.href = '/success.html';
        
    })
    .catch((error) => {
        // If there is an error, log the error and redirect to the poll page
        console.error('Error:', error);
        window.location.href = `/polls/respond/${tableArray[0].getAttribute('data-pid')}`;
    });
}

function clearSession() {
    sessionStorage.clear();
}

function getResponse(blocks) {

    var dataMid = blocks.parentElement.getAttribute("data-mid");
    var dataPid = blocks.parentElement.getAttribute("data-pid");
    var dataUid = blocks.parentElement.getAttribute("data-uid");

    getResponseHelper(dataUid, dataMid, dataPid, select);
}

function updateDisplay(select) {
    var selectedOption = select.options[select.selectedIndex];
    var dataMid = selectedOption.getAttribute("data-mid");
    var dataPid = selectedOption.getAttribute("data-pid");
    var dataUid = selectedOption.getAttribute("data-uid");

    getResponseHelper(dataUid, dataMid, dataPid, select);
}


function getResponseHelper(uid, mid, pid, button) {
    var columnName = "available_time";
    fetch(`/poll/response/${uid}/${mid}/${pid}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            var times = data[columnName];
            document.getElementById('displayArea').innerText = times;
        })
        .catch(error => console.error('Error:', error));
}

