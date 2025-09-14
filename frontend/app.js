document.addEventListener('DOMContentLoaded', () => {
    const messageList = document.getElementById('messages');
    const form = document.getElementById('message-form');

    if (messageList) {
        fetchMessages();
    }

    if (form){
        form.addEventListener('submit', async (e) => {
            e.preventDefault();

            const text = document.getElementById('text').value.trim();
            const sender = document.getElementById('sender').value.trim();

            try {
                const response = await fetch('http://localhost:8080/api/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: new URLSearchParams({text, sender})
                });

                if (response.ok) {
                    window.location.href = 'index.html';
                } else {
                    alert('Failed to submit the message.');
                }
            } catch (err) {
                console.error('Error submitting message:', err);
                alert('Error submitting message.');
            }
        });
    }
});

async function fetchMessages() {
    try {
        const response = await fetch('http://localhost:8080/api');
        const messages = await response.json();

        const messageList = document.getElementById('messages');
        messageList.innerHTML = '';

        messages.forEach(message => {
            const card = document.createElement('div');
            card.className = 'card';
            card.style.width = '18rem';

            card.innerHTML = `
                <div class="card-body">
                    <p class="card-text fw-bold">To: ${message.receiver}</p>
                    <p class="card-text">${message.message}</p>
                    <p class="card-text">${message.date}</p>
                </div>
            `;

            messageList.appendChild(card);
        });
    } catch (error) {
        console.error('Failed to fetch messages:', error);
    }
}


