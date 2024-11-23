/*!
* Start Bootstrap - Business Casual v7.0.9 (https://startbootstrap.com/theme/business-casual)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-business-casual/blob/master/LICENSE)
*/
// Highlights current date on contact page

document.addEventListener('DOMContentLoaded', () => {
    const bodyId = document.body.id;

    // Inicializar según el ID del cuerpo
    if (bodyId === 'hacerReserva') {
        ReservationHandler.init();
    } else if (bodyId === 'misReservas') {
        ReservationCancelHandler.init();
    } else if (bodyId === 'crearCuenta') {
        DateFieldHandler.init();
    } else if (bodyId === 'consultarReservas') { 
        ReservationQueryHandler.init();
    }
});

const ReservationHandler = {
    init: function () {
        const reservationForm = document.getElementById('formFecha');
        if (reservationForm) {
            this.initReservationDate();
            this.initCurrentDayHighlight();
        }
    },

    initReservationDate: function () {
        const fechaReservaInput = document.getElementById('fechaReserva');
        if (fechaReservaInput) {
            // Establecer la fecha mínima como hoy para reservas
            const today = new Date().toISOString().split('T')[0];
            fechaReservaInput.setAttribute('min', today);

            // Evento al cambiar la fecha seleccionada
            fechaReservaInput.addEventListener('change', this.handleReservationDateChange.bind(this));
        }
    },

    initCurrentDayHighlight: function () {
        const listHoursArray = document.body.querySelectorAll('.list-hours li');
        if (listHoursArray.length > 0) {
            // Resaltar el día actual en la lista de horarios
            listHoursArray[new Date().getDay()].classList.add('today');
        }
    },

    handleReservationDateChange: function (event) {
        event.preventDefault();
        const fechaSeleccionada = event.target.value;

        if (!fechaSeleccionada) {
            alert("Por favor, selecciona una fecha válida.");
            return;
        }

        // Llamada AJAX para obtener horarios disponibles
        fetch(`SvReserva?fechaReserva=${fechaSeleccionada}`, {
            method: 'GET',
            headers: {
                'Accept': 'text/html',
                'Content-Type': 'text/html'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener los horarios disponibles.');
                }
                return response.text();
            })
            .then(data => {
                const listaHorarios = document.getElementById("listaHorarios");
                if (listaHorarios) {
                    // Mostrar los horarios disponibles
                    listaHorarios.innerHTML = data;
                    this.addReservationButtonListeners();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Ocurrió un error al buscar los horarios.");
            });
    },

    addReservationButtonListeners: function () {
        const reservarBtns = document.querySelectorAll('.reservar-btn');
        reservarBtns.forEach(btn => {
            btn.addEventListener('click', this.handleReservationClick.bind(this));
        });
    },

    handleReservationClick: function (e) {
        e.preventDefault();
        const horaSeleccionada = e.target.getAttribute('data-hora');
        const fechaSeleccionada = document.getElementById('fechaReserva').value;

        if (!horaSeleccionada || !fechaSeleccionada) {
            alert("No se pudo procesar la reserva. Verifica la fecha y la hora seleccionada.");
            return;
        }

        const formData = new URLSearchParams();
        formData.append('horaSeleccionada', horaSeleccionada);
        formData.append('fechaSeleccionada', fechaSeleccionada);

        // Llamada AJAX para realizar la reserva
        fetch('SvReserva', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al realizar la reserva.');
                }
                return response.text();
            })
            .then(message => {
                alert(message);

                // Eliminar la fila correspondiente
                const fila = document.getElementById(`fila-${horaSeleccionada}`);
                if (fila) {
                    fila.remove();
                }

                // Mostrar confirmación
                this.showConfirmation(horaSeleccionada, fechaSeleccionada);
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Ocurrió un error al realizar la reserva.");
            });
    },

    showConfirmation: function (horaSeleccionada, fechaSeleccionada) {
        const contenedorBotones = document.getElementById('contenedorBotones') || document.createElement('div');
        contenedorBotones.id = 'contenedorBotones';

        // Crear el botón de confirmación
        const nuevoBoton = document.createElement('button');
        nuevoBoton.type = 'button';
        nuevoBoton.className = 'btn btn-success btn-xl mx-auto mt-3';
        nuevoBoton.textContent = `Reserva realizada: ${fechaSeleccionada} a las ${horaSeleccionada}`;

        contenedorBotones.appendChild(nuevoBoton);

        if (!document.getElementById('contenedorBotones')) {
            document.querySelector('.bg-faded').appendChild(contenedorBotones);
        }
    }
};


const DateFieldHandler = {
    init: function () {
        console.log("Iniciando funciones de manejo de fechas de nacimiento...");
        this.initBirthDateFields();
    },
    initBirthDateFields: function () {
        const birthDateInputs = document.querySelectorAll('.fecha-nacimiento');
        birthDateInputs.forEach(input => {
            const today = new Date().toISOString().split('T')[0];
            input.setAttribute('max', today);

            const minDate = new Date();
            minDate.setFullYear(minDate.getFullYear() - 100);
            input.setAttribute('min', minDate.toISOString().split('T')[0]);

            input.addEventListener('change', this.validateBirthDate);
        });
    },
    validateBirthDate: function (event) {
        const selectedDate = new Date(this.value);
        const today = new Date();

        if (selectedDate > today) {
            alert("La fecha ingresada no puede ser en el futuro.");
            this.value = '';
            return;
        }

        const age = today.getFullYear() - selectedDate.getFullYear();
        if (age > 100 || age < 10) {
            alert("Por favor verifica la fecha ingresada.");
        }
    }
};

const ReservationCancelHandler = {
    init: function () {
        console.log("Iniciando funciones de cancelación...");
        const reservationForm = document.getElementById('formFecha');
        if (reservationForm) {
            this.initReservationDate();
        }
    },
    initReservationDate: function () {
        const fechaReservaInput = document.getElementById('fechaReserva');
        if (fechaReservaInput) {
            const today = new Date().toISOString().split('T')[0];
            fechaReservaInput.setAttribute('min', today);
            // Use arrow function to preserve 'this' context
            fechaReservaInput.addEventListener('change', (event) => this.handleReservationDateChange(event));
        }
    },
    handleReservationDateChange: function (event) {
        event.preventDefault();
        const fechaSeleccionada = event.target.value;
        if (!fechaSeleccionada) {
            alert("Por favor, selecciona una fecha válida.");
            return;
        }
        
        // Format the date correctly
        const formattedDate = new Date(fechaSeleccionada).toISOString().split('T')[0].replace(/-/g, '/');
        
        fetch(`SvEliminarReserva?fechaReserva=${formattedDate}`, {
            method: 'GET',
            headers: { 'Accept': 'text/html', 'Content-Type': 'text/html' }
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener las reservas disponibles.');
            }
            return response.text();
        })
        .then(data => {
            const listaReservas = document.getElementById("listaHorarios");
            if (listaReservas) {
                listaReservas.innerHTML = data;
                this.addCancelReservationButtonListeners();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Ocurrió un error al buscar las reservas.");
        });
    },
    addCancelReservationButtonListeners: function () {
        const cancelarBtns = document.querySelectorAll('.cancelar-btn');
        cancelarBtns.forEach(btn => {
            // Use arrow function to preserve 'this' context
            btn.addEventListener('click', (e) => this.handleCancelReservationClick(e));
        });
    },
    handleCancelReservationClick: function (e) {
        e.preventDefault();
        const idReserva = e.target.getAttribute('data-id');
        if (!idReserva) {
            alert("No se pudo procesar la cancelación. Verifica la información.");
            return;
        }
        if (!confirm("¿Estás seguro de que deseas cancelar esta reserva?")) {
            return;
        }
        fetch('SvEliminarReserva', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ idReserva })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al cancelar la reserva.');
            }
            return response.text();
        })
        .then(message => {
            alert(message);
            const fila = document.getElementById(`fila-${idReserva}`);
            if (fila) {
                fila.remove();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Ocurrió un error al cancelar la reserva.");
        });
    }
};

function buscarPorFecha(tipo) {
    var fechaReserva = document.getElementById('fechaReserva').value;
    if (!fechaReserva) {
        alert('Por favor, seleccione una fecha');
        return;
    }
    var formData = new FormData();
    formData.append('fechaReserva', fechaReserva);
    formData.append('tipoConsulta', tipo);
    realizarBusqueda(formData);
}

function buscarPorNombre() {
    var nombreUsuario = document.getElementById('nombreUsuario').value;
    if (!nombreUsuario) {
        alert('Por favor, ingrese un nombre');
        return;
    }
    var formData = new FormData();
    formData.append('nombreUsuario', nombreUsuario);
    realizarBusqueda(formData);
}

function buscarPorApellido() {
    var primerApellido = document.getElementById('primerApellido').value;
    if (!primerApellido) {
        alert('Por favor, ingrese un apellido');
        return;
    }
    var formData = new FormData();
    formData.append('primerApellido', primerApellido);
    realizarBusqueda(formData);
}

function buscarPorIdUsuario() {
    var idUsuario = document.getElementById('idUsuario').value;
    if (!idUsuario) {
        alert('Por favor, ingrese un ID de usuario');
        return;
    }
    var formData = new FormData();
    formData.append('idUsuario', idUsuario);
    realizarBusqueda(formData);
}

function buscarPorIdReserva() {
    var idReserva = document.getElementById('idReserva').value;
    if (!idReserva) {
        alert('Por favor, ingrese un ID de reserva');
        return;
    }
    var formData = new FormData();
    formData.append('idReserva', idReserva);
    realizarBusqueda(formData);
}

function realizarBusqueda(formData) {
    // Mostrar los datos que se están enviando
    const urlParams = new URLSearchParams();
    for (let [key, value] of formData.entries()) {
        urlParams.append(key, value);
    }

    fetch('SvConsultarReservas', {
        method: 'POST',
        body: urlParams,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        }
    })
    .then(response => {
        console.log('Estado de la respuesta:', response.status);
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        return response.text();
    })
    .then(data => {
        console.log('Datos recibidos:', data);
        document.getElementById('resultadoConsulta').innerHTML = data;
    })
    .catch(error => {
        console.error('Error completo:', error);
        alert('Ocurrió un error al realizar la búsqueda: ' + error.message);
    });

}

