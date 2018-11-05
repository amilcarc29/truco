var interval;
var partidasActivas = "";
var tituloCargado = false;
var juegoActual;
var tm;

$(document).ready(function() {
	loadUser();
	loopPartidas();
	loadActions();
	notifyCheck();
	
	document.title = 'Player: ' + user.apodo;
	
	
	
});
var esperandoPartida = false;
function loadUser() {
	$("#userApodo").text(user.apodo);

}
function unirsePartidaLibre() {

	if (esperandoPartida) {

		$.alertable.alert('Se encuentra en espera.').always(function() {

		});

		return;
	}
	var url = '/WebTruco/Juegos';
	var unirsePartida = {
		action : 'unirsePartida'
	};

	$.ajax({
		type : "POST",
		url : url,
		data : unirsePartida, // serializes the form's elements.
		success : function(data) {
			esperandoPartida = true;

		}
	});

}
function loopPartidas() {

	interval = setInterval(function() {
		buscarPartida();
	}, 5000);

}
function buscarPartida() {

	var url = '/WebTruco/Juegos';
	var buscarJuegos = {
		action : 'getJuegos'
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			partidasActivas = data;

			litarPartidas();
		}
	});

}
function litarPartidas() {
	var lista = " <ol>";

	for (var i = 0; i < partidasActivas.length; i++) {

		lista += "  <li><a href=\"#\" data-popup-open=\"popup-2\" onclick=\"abrirJuego("
				+ partidasActivas[i].idJuego
				+ ")\">"
				+ partidasActivas[i].idJuego + "</a></li>";

	}
	lista += " </ol>";
	document.getElementById('games').innerHTML = lista;
	if (!tituloCargado) {
		tituloCargado = true;
		document.getElementById('JuegosTit').innerHTML = "Mis Juegos!";

	}
	loadActions();

}
function abrirJuego(idJuego) {
	var url = '/WebTruco/Juegos';
	var buscarJuegos = {
		action : 'getCartas',
		idJuego : idJuego
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {

			cargarCartas(data, idJuego, 'games2');
			juegoActual = idJuego;
			verificarTurnoJuegoSel();
			 getCartasJugadas() ;
			
		}
	});

}

function clearInterval(){
	  clearTimeout(tm);
}

function reloadCartas(){
	
	var url = '/WebTruco/Juegos';
	var buscarJuegos = {
		action : 'getCartas',
		idJuego : juegoActual
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {

			cargarCartas(data, juegoActual,'games2');
		
			
		}
	});
}
function notifyMe() {
	// Comprobamos si el navegador soporta las notificaciones
	if (!("Notification" in window)) {
		alert("Este navegador no soporta las notificaciones del sistema");
	}

	// Comprobamos si ya nos habían dado permiso
	else {
		// Si esta correcto lanzamos la notificación
		var notification = new Notification("Es su turno " + user.apodo);
	}

	


}
function verificarTurnoJuegoSel() {
	tm =  setTimeout(function() {

		var url = '/WebTruco/Juegos';
		var buscarJuegos = {
			action : 'esMiturno',
			idJuego : juegoActual
		};

		$.ajax({
			type : "POST",
			url : url,
			data : buscarJuegos, // serializes the form's elements.
			success : function(data) {

				if (data.TURNO == "TRUE") {
					notifyMe();
				}

			}
		});
		 getCartasJugadas();
		verificarTurnoJuegoSel();
	}, 50000);

}

function cargarCartas(data, juego, div) {
	var cartas = "";

	for (var i = 0; i < data.length; i++) {

		cartas += "<img src='./img/" + data[i].palo + "/" + data[i].numero
				+ ".jpg' style='cursor:pointer' onClick='verificarTurno(" + data[i].idCarta + ", "
				+ juego + ")' >";
	}

	document.getElementById(div).innerHTML = cartas;
}

function verificarTurno(idCarta, idJuego) {

	var url = '/WebTruco/Juegos';

	var buscarJuegos = {
		action : 'esMiturno',
		idJuego : idJuego
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {

			if (data.TURNO == "TRUE") {
				jugar(idCarta, idJuego);
			} else {

				$.alertable.alert('No es su turno').always(function() {

				});

			}

		}
	});
}
function jugar(idCarta, idJuego) {

	var url = '/WebTruco/Juegos';

	var buscarJuegos = {
		action : 'JugarCarta',
		idJuego : idJuego,
		idCarta : idCarta
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			if (data.JUGADA == "TRUE") {
				reloadCartas();
			}
		}
	});

}



function getCartasJugadas() {

	var url = '/WebTruco/Juegos';

	var buscarJuegos = {
		action : 'getCartasJugadas',
		idJuego : juegoActual
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			cargarCartas(data,juegoActual, 'games3' );
		}
	});

	

}



function loadActions() {
	$(function() {
		// ----- OPEN
		$('[data-popup-open]')
				.on(
						'click',
						function(e) {
							var targeted_popup_class = jQuery(this).attr(
									'data-popup-open');
							$('[data-popup="' + targeted_popup_class + '"]')
									.fadeIn(350);
							e.preventDefault();
							if (targeted_popup_class == "popup-1") {
								document.getElementById('JuegosTit').innerHTML = "Cargando Juegos...";
								tituloCargado = false;
							}

						});

		// ----- CLOSE
		$('[data-popup-close]').on('click', function(e) {
			var targeted_popup_class = jQuery(this).attr('data-popup-close');
			$('[data-popup="' + targeted_popup_class + '"]').fadeOut(350);

			e.preventDefault();
		});
	});

}


function notifyCheck() {
	  // Comprobamos si el navegador soporta las notificaciones
	  if (!("Notification" in window)) {
	    alert("Este navegador no soporta las notificaciones del sistema");
	  }

	  // Comprobamos si ya nos habían dado permiso
	  else if (Notification.permission === "granted") {
	    // Si esta correcto lanzamos la notificación
	    var notification = new Notification("Buenas ! " + user.apodo);
	  }

	  // Si no, tendremos que pedir permiso al usuario
	  else if (Notification.permission !== 'denied') {
	    Notification.requestPermission(function (permission) {
	      // Si el usuario acepta, lanzamos la notificación
	      if (permission === "granted") {
	        var notification = new Notification("Gracias !");
	      }
	    });
	  }

	  // Finalmente, si el usuario te ha denegado el permiso y 
	  // quieres ser respetuoso no hay necesidad molestar más.
	}

