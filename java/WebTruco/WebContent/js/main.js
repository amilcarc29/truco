var interval;
var partidasActivas = "";
var tituloCargado = false;
var juegoActual;
var juegoJson;

var partidas;
var juegoActual;

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

	$
			.ajax({
				type : "POST",
				url : url,
				data : unirsePartida, // serializes the form's elements.
				success : function(data) {
					esperandoPartida = true;
					document.getElementById('usuarioEnEspera').innerHTML = "Buscando partida...";

				}
			});

}

function loopPartidas() {

	partidas = setInterval(function() {
		buscarPartida();
	}, 5000);

}
function abrirJuego(idJuego) {
	juegoActual = idJuego;

	loopRenderGame();
}
function loopRenderGame() {

	partidas = setInterval(function() {
		renderGame();
		 getCartas();
		 
		// //notificaTurno();
		// getCartasJugadas(juegoActual);
		// getPuntos(juegoActual);
	}, 1000);

}

function renderGame() {

	var url = '/WebTruco/Juegos';
	var buscarJuegos = {
		action : 'getJuego',
		idJuego : juegoActual
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			render(data);
		}
	});

}
function render(data){
	var txtStatus  = "<p style='color: blue'>Chico " + data.chicos.length + "\n, Mano " + data.chicos[data.chicos.length-1].manos.length  + " \n " +
	"Puntos [p1 " + data.chicos[data.chicos.length-1].puntosChico[0].puntos +", p2 " +  data.chicos[data.chicos.length-1].puntosChico[1].puntos+"]</p>";
	document.getElementById('status').innerHTML =txtStatus ;
	var jugNum=1;
	for (var i =0; i<data.parejas.length;i++){
		
		for (var x =0; x<data.parejas[i].jugadores.length;x++){
			
				drawCartas(data.parejas[i].jugadores[x].cartas, juegoActual, 'jug'+ jugNum + "jug", false, jugNum % 2);
				jugNum++;
		}
	}
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

			listarPartidas();
		}
	});

}
function listarPartidas() {
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
		if (partidasActivas.length == 0) {
			document.getElementById('JuegosTit').innerHTML = "No hay juegos activos.";
		} else {
			document.getElementById('JuegosTit').innerHTML = "Mis Juegos!";
		}
	}
	loadActions();

}

function getCartas(idJuego) {
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

			drawCartas(data, idJuego, 'jug1', true, true);

		}
	});

}

function clearInterval() {

	clearTimeout(partidas);
	clearTimeout(juegoActual);
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
function notificaTurno() {

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
}

function drawCartas(data, juego, div, action, drop) {
	
	
	var cartas = "";
	
	 cartas = "<div class='divTable'><div class='divTableBody'><div class='divTableRow'>";

	 
	if (drop){
		for (var i = 0; i < data.length; i++) {

			cartas += "<div class='divTableCell'><img src='./img/" + data[i].palo + "/" + data[i].numero
					+ ".jpg' ";
			
			if(action){
				onClick=" style='cursor:pointer' 'verificarTurno("+ data[i].idCarta + ", " + juego + ")'";
			}
		
			
			cartas += "></div>";
		}
	}else{
			for (var i = 0; i < data.length; i++) {

				cartas += "<div class='divTableRow'><div class='divTableCell'><img src='./img/" + data[i].palo + "/" + data[i].numero
						+ ".jpg' ";
				
				if(action){
					onClick=" style='cursor:pointer' 'verificarTurno("+ data[i].idCarta + ", " + juego + ")'";
				}
			
				
				cartas += "></div></div>";
			}
	}

	cartas += "</div></div></div>";

	


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

			}
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
		Notification.requestPermission(function(permission) {
			// Si el usuario acepta, lanzamos la notificación
			if (permission === "granted") {
				var notification = new Notification("Gracias !");
			}
		});
	}

	// Finalmente, si el usuario te ha denegado el permiso y
	// quieres ser respetuoso no hay necesidad molestar más.
}
