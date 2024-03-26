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
        var text = document.getElementById("darkModeBtn");
            if (text.textContent === '🌙') {
                text.textContent = '☀';
            }
            else {
                text.textContent = '🌙';
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

function getUsersList() {
    fetch('./userAll', {
        method: 'GET', 
        headers:{
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
    data.forEach(function(item) {
        html += '<div>';
        html += '<p>UID: ' +  item.uid + '</p>';
        html += '<p>Name: ' + item.firstName + '</p>';
        html += '<p>Age: ' + item.lastName + '</p>';
        // Add more properties as needed
        html += '</div>';
    });

    div.innerHTML = html;
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
        let node = document.createElement("p");

        node.classList.add('singleUser');
        node.id = "uid" + data[i].uid;
        node.innerHTML = "(" + data[i].uid + ") " + data[i].firstName + " " + data[i].lastName;

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
}
