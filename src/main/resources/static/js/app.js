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

