/**
 * This function is called to all pages where the user is notified
 * of a successful operation or a failed operation.
 */
function redirectAfterDelay() {
    setTimeout(function () {
        window.location.href = '/';
    }, 3000);
}


function checkPasswordMatch() {
    let password1 = document.getElementById("password1").value;
    let password2 = document.getElementById("password2").value;

    if (password1 !== password2) {
        errorMessage.textContent = 'Passwords do not match';
    } else {
        errorMessage.textContent = '';
    }
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

