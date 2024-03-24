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

function addMediumsToForm(){
    let mediums = document.getElementsByClassName("singleMedium");
    if (mediums.length == 0){
        alert("Please add a medium");
        return;
    }
    
    // Do something you need
    for (var i = 0; i < mediums.length; i++){
        let node = document.createElement("input");
        node.name = "m"+i;
        node.value = mediums[i].innerHTML;
        node.hidden = true;
        document.getElementById("inputField").append(node);
    }
    console.log(document.getElementById("inputField").elements);
    document.getElementById("inputField").requestSubmit();
}
