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

// Function to open the selected tab
function openTab(tabId) { 
    // Get the active tab from sessionStorage
    var activeTabId = sessionStorage.getItem("active");

    // Get references to tab buttons and tab contents
    var tabBtn1 = document.querySelector('.tab-btn1');
    var tabBtn2 = document.querySelector('.tab-btn2');
    var tabBtn3 = document.querySelector('.tab-btn3');
    var tabContentPending = document.getElementById('pending');
    var tabContentCreated = document.getElementById('created');
    var tabContentAllPolls = document.getElementById('allPolls');

    // Hide all tab contents
    tabContentPending.style.display = 'none';
    tabContentCreated.style.display = 'none';
    tabContentAllPolls.style.display = 'none';

    // Remove active class from all tab buttons
    tabBtn1.classList.remove('active');
    tabBtn2.classList.remove('active');
    tabBtn3.classList.remove('active');

    // Show the selected tab content and add active class to its button
    if (tabId === "pending") {
        tabContentPending.style.display = 'block';
        tabBtn1.classList.add('active');
    } else if (tabId === "created") {
        tabContentCreated.style.display = 'block';
        tabBtn2.classList.add('active');
    } else if (tabId === "allPolls") {
        tabContentAllPolls.style.display = 'block';
        tabBtn3.classList.add('active');
    }

    // Update the active tab variable in sessionStorage
    activeTabId = sessionStorage.setItem('active', tabId);
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