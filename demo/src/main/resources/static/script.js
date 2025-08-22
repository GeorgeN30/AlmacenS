document.addEventListener('DOMContentLoaded', function () {
    
    const form = document.getElementById('registro-form');
    const tbody = document.getElementById('registro-tabla');
    const searchInput = document.getElementById('search-input');
    /**
     * Agrega una fila a la tabla en la posición especificada.
     * @param {object} data - Los datos del registro.
     * @param {string} firmaUrl - La URL local de la imagen de la firma.
     * @param {string} posicion - Dónde agregar la fila ('inicio' o 'final').
     */
    function agregarFila(data, firmaUrl = '', posicion = 'final') {
        let estadoTexto = data.estado ? "S" : "E";
        let estadoColor = data.estado
            ? "background-color:#dc3545;color:white;"
            : "background-color:#28a745;color:white;";
        let switchId = 'switch-' + Math.random().toString(36).substr(2, 9);
        
        let switchHtml = `<div class='form-check form-switch'>
                              <input class='form-check-input' type='checkbox' role='switch' id='${switchId}' ${data.estado ? 'checked' : ''}>
                          </div>`;

        let row = document.createElement('tr');
        row.setAttribute('data-ot', data.ot.toLowerCase());
        row.innerHTML = `
            <td>${data.ot}</td>
            <td>${data.cliente}</td>
            <td>${data.equipos}</td>
            <td>${data.metrologo}</td>
            <td>${firmaUrl ? `<img src='${firmaUrl}' alt='Firma' style='max-width:60px;max-height:60px;'>` : (data.firmaPath || '')}</td>
            <td class='estado-cell' style='${estadoColor}'>${estadoTexto}</td>
            <td>${switchHtml}</td>
            <td>${new Date(data.fechaHora).toLocaleDateString()}</td>
            <td>${new Date(data.fechaHora).toLocaleTimeString()}</td>
        `;

        if (posicion === 'inicio') {
            tbody.prepend(row); // Agrega la fila al PRINCIPIO
        } else {
            tbody.appendChild(row); // Agrega la fila al FINAL
        }

        
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
    }

    searchInput.addEventListener('input', function () {
        const query = searchInput.value.toLowerCase();
        const rows = tbody.querySelectorAll('tr');

        rows.forEach(row => {
            const ot = row.getAttribute('data-ot');
            if (ot.includes(query)) {
                row.style.display = ''; // Muestra la fila
            } else {
                row.style.display = 'none'; // Oculta la fila
            }
        });
    });

    
    fetch("http://localhost:8080/api/registros")
        .then(response => response.json())
        .then(data => {
            
            data.forEach(registro => agregarFila(registro, '', 'final'));
        })
        .catch(error => console.error("Error cargando registros:", error));

    
    form.addEventListener('submit', function (e) {
        e.preventDefault();

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

        const registro = {
            ot: ot,
            cliente: cliente,
            equipos: equipos,
            metrologo: metrologo,
            firmaPath: firma,
            estado: true, 
            fechaHora: new Date().toISOString().slice(0, 19)
        };

        fetch("http://localhost:8080/api/registros", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(registro)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Error en la petición: " + response.status);
            }
            return response.json();
        })
        .then(data => {
            console.log("Registro guardado en backend:", data);
            
            agregarFila(data, firmaUrl, 'inicio');
            form.reset();
        })
        .catch(error => {
            console.error("Error al guardar registro:", error);
            alert("No se pudo guardar el registro.");
        });
    });
});