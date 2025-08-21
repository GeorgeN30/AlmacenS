document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registro-form');
    const tabla = document.getElementById('registro-tabla');

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        //Variables
        let ot = document.getElementById('ot').value;
        let cliente = document.getElementById('cliente').value;
        let equipos = document.getElementById('equipos').value;
        let metrologo = document.getElementById('metrologo').value;
        let firmaInput = document.getElementById('firma');
        let firma = '';
        let firmaUrl = '';
        if (firmaInput.files.length > 0) {
            const file = firmaInput.files[0];
            firma = file.name;
            firmaUrl = URL.createObjectURL(file);
        }
        let estado = 'S';
        let estadoColor = 'background-color:#dc3545;color:white;';

        let now = new Date();
        let fecha = now.toLocaleDateString();
        let hora = now.toLocaleTimeString();

        let switchId = 'switch-' + Math.random().toString(36).substr(2, 9);
        let switchHtml = `<div class='form-check form-switch'><input class='form-check-input' type='checkbox' id='${switchId}' checked></div>`;

        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${ot}</td>
            <td>${cliente}</td>
            <td>${equipos}</td>
            <td>${metrologo}</td>
            <td>${firmaUrl ? `<img src='${firmaUrl}' alt='Firma' style='max-width:60px;max-height:60px;'>` : ''}</td>
            <td class='estado-cell' style='${estadoColor}'>${estado}</td>
            <td>${switchHtml}</td>
            <td>${fecha}</td>
            <td>${hora}</td>
        `;
        tabla.querySelector('tbody') ? tabla.querySelector('tbody').insertBefore(row, tabla.querySelector('tbody').firstChild) : tabla.insertBefore(row, tabla.rows[1]);


        const switchElem = row.querySelector('.form-check-input');
        switchElem.addEventListener('change', function () {
            const estadoCell = row.querySelector('.estado-cell');
            if (this.checked) {
                estadoCell.textContent = 'S';
                estadoCell.style.backgroundColor = '#dc3545';
                estadoCell.style.color = 'white';
            } else {
                estadoCell.textContent = 'E';
                estadoCell.style.backgroundColor = '#28a745';
                estadoCell.style.color = 'white';
            }
        });
        form.reset();
    });
});