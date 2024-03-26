/**
 * This function is called to all pages where the user is notified
 * of a successful operation or a failed operation.
 */
function redirectAfterDelay() {
    setTimeout(function () {
        window.location.href = '/';
    }, 3000);
}


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('darkModeBtn').addEventListener('click', function() {
        document.body.classList.toggle('dark-mode');
    });
});


document.addEventListener('DOMContentLoaded', function() {
    const dateTimeRangeInput = document.getElementById('dateTimeRange');
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');
    const startTimeInput = document.getElementById('startTime');
    const endTimeInput = document.getElementById('endTime');

    flatpickr(dateTimeRangeInput, {
        enableTime: true,
        time_24hr: false,
        mode: 'range', // Enable range mode for selecting a date and time range
        dateFormat: "Y-m-d H:i",
        onClose: function(selectedDates, dateStr, instance) {
            // Update input labels with selected date and time range values
            if (selectedDates.length === 2) {
                startDateInput.value = selectedDates[0].toLocaleDateString();
                endDateInput.value = selectedDates[1].toLocaleDateString();
                startTimeInput.value = selectedDates[0].toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
                endTimeInput.value = selectedDates[1].toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
            } else {
                startDateInput.value = '';
                endDateInput.value = '';
                startTimeInput.value = '';
                endTimeInput.value = '';
            }
        }
    });
});

//code for creating poll
function addMedium(){
    let mediumName = document.getElementById("mediumInput").value.trim();
    if (mediumName==""){
        return;
    }
    let par = document.getElementById("mediumsList");
    let node = document.createElement("p");
    node.classList.add('singleMedium');
    if (document.getElementById("mediumRemote").checked){
        node.innerHTML = "(R) " + mediumName;
    }
    else {
        node.innerHTML = mediumName;
    }
    node.onclick = function () {
        this.parentElement.removeChild(this);
    };
    par.appendChild(node);
}

function addMediumsUsersToForm(){
    //add mediums
    let mediums = document.getElementsByClassName("singleMedium");
    if (mediums.length == 0){
        alert("Please add a medium");
        return;
    }
    let users = document.getElementsByClassName("singleUser");
    if (users.length == 0){
        alert("Please add a user");
        return;
    }
    
    // add from our list
    for (var i = 0; i < mediums.length; i++){
        let node = document.createElement("input");
        node.name = "m"+i;
        node.value = mediums[i].innerHTML;// "(r) __" or "___"
        node.hidden = true;
        document.getElementById("inputField").append(node);
    }

    //add users
    for (var i = 0; i < users.length; i++){
        let node = document.createElement("input");
        node.name = "u"+i;
        node.value = users[i].innerHTML;// "(x) fName lName"
        node.hidden = true;
        document.getElementById("inputField").append(node);
    }

    
    //send
    document.getElementById("inputField").requestSubmit();
}
function getUsers(){
    //get partial search
    let search = document.getElementById("usersInput").value.trim();
    if (search == ""){
        return;
    }
    //send partial search
    fetch('./userSearch', {
        method: 'POST', 
        body: search, 
        headers:{
        'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => updateUsers(data));
}

function updateUsers(data){
    //update list

    //clear old list
    document.getElementById("usersSearchResults").replaceChildren();

    //add search results
    for (let i = 0; i < data.length; i++){
        if (document.getElementById("uid" + data[i].uid) != null){
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

    window.onclick = function(event) {
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

