var interval ;
var partidasActivas="";
$(document).ready(function() {
	loadUser();
	loopPartidas();
});
var esperandoPartida = false;
function loadUser() {
	$("#userApodo").text(user.apodo);

}
function unirsePartidaLibre() {
	
	if (esperandoPartida){
		
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
function loopPartidas(){
	
	interval = setInterval(function() { 
		buscarPartida();
	}, 5000);
	
}
function buscarPartida() {
	var url = '/WebTruco/Juegos';
	var buscarJuegos = {
		action : 'buscarJuegos'
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			partidasActivas = data;
		}
	});

}




