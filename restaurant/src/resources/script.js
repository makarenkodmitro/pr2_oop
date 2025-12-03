const API_URL = 'https://super-goldfish-rqv5v4x47453xgqv-8080.app.github.dev';
const messageDiv = document.getElementById('message');
const tableBody = document.getElementById('tableBody');

// Завантажити дані при старті
loadOrderItems();

function showMessage(text, type) {
    messageDiv.textContent = text;
    messageDiv.className = `message ${type} show`;
    setTimeout(() => {
        messageDiv.className = 'message';
    }, 3000);
}

async function loadOrderItems() {
    try {
        const response = await fetch(`${API_URL}/api/orderitems`, {
            method: 'GET',
            mode: 'cors'
        });

        if (response.ok) {
            const orderItems = await response.json();
            updateTable(orderItems);
        } else {
            const errorText = await response.text();
            showMessage('Помилка завантаження даних: ' + errorText, 'error');
            tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Помилка: ' + errorText + '</td></tr>';
        }
    } catch (error) {
        showMessage('Помилка з\'єднання з сервером', 'error');
        tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Помилка з\'єднання з сервером: ' + error.message + '</td></tr>';
    }
}

function updateTable(orderItems) {
    if (orderItems.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Немає даних</td></tr>';
        return;
    }

    tableBody.innerHTML = orderItems.map((item, index) => `
        <tr>
            <td>${index + 1}</td>
            <td>${item.dish}</td>
            <td>${item.quantity}</td>
        </tr>
    `).join('');
}