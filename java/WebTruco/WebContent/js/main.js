var interval;
var partidasActivas = "";
var tituloCargado = false;


$(document).ready(function() {
	loadUser();
	loopPartidas();
	loadActions();
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
				+ partidasActivas[i].idJuego + ")\">"
				+ partidasActivas[i].idJuego + "</a></li>";

	}
	lista += " </ol>";
	document.getElementById('games').innerHTML = lista;
	if (!tituloCargado){
		tituloCargado = true;
		document.getElementById('JuegosTit').innerHTML = "Mis Juegos!";

	}
	loadActions();

}
function abrirJuego(idJuego){
	var url = '/WebTruco/Juegos';
	var buscarJuegos = {
		action : 'getCartas',
		idJuego: idJuego
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			
			cargarCartas(data, idJuego);
			
		}
	});
}
function cargarCartas(data, juego){
	var cartas = "";
	
	for (var i = 0 ; i <data.length;i++) { 
	   
	    
	    cartas += "<img src='./img/"+  data[i].palo + "/" +    data[i].numero + ".jpg' onClick='verificarTurno("+ data[i].idCarta+", "+juego+")' >";
	}
	
	document.getElementById('games2').innerHTML = cartas;
}

function verificarTurno(idCarta, idJuego){
	
	var url = '/WebTruco/Juegos';
	
	var buscarJuegos = {
		action : 'esMiturno',
		idJuego: idJuego
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			
			if(data.TURNO == true){
				jugar(idCarta, idJuego);
			}else{
				
				$.alertable.alert('No es su turno').always(function() {

				});

			}
				
		}
	});
}
function jugar(idCarta, idJuego){
	

	
}

function loadActions() {
	$(function() {
		// ----- OPEN
		$('[data-popup-open]').on('click', function(e) {
			var targeted_popup_class = jQuery(this).attr('data-popup-open');
			$('[data-popup="' + targeted_popup_class + '"]').fadeIn(350);
			e.preventDefault();
			if (targeted_popup_class=="popup-1") {
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
