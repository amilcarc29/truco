var interval;
var partidasActivas = "";
var usuariosActivos = "";
var tituloCargado = false;
var juegoActual;
var juegoJson;

var usuarioSeleccionado;

var turno;
var partidas;
var intTanto;
var tantoMsgVis = false;

var manoActual = null;
var chicoActual = null;
var bazaActual = 1;

var ultimoTanto = null;

var seCantoEnvido = false;
var seCantoTruco = false;

var ganador = null;

$(document).ready(function() {

	loadUser();
	loadActions();
	notifyCheck();
	document.title = 'Player: ' + user.apodo;
	$("#loader").fadeOut("fast");

});

function back() {
	location.reload();
}

var esperandoPartida = false;
function loadUser() {
	$("#userApodo").text("Hola " + user.apodo);
	document.getElementById("userApodo").style.color = "#000000";
	document.getElementById("userApodo").style.fontWeight = "900";

	$("#userPuntaje").text("Tu Puntaje es " + user.puntaje);
	document.getElementById("userPuntaje").style.color = "#000000";
	document.getElementById("userPuntaje").style.fontWeight = "900";
	
	$("#userCategoria").text("Tu categoria es " + user.categoria.nombre);
	document.getElementById("userCategoria").style.color = "#000000";
	document.getElementById("userCategoria").style.fontWeight = "900";
	
	$("#userPganadas").text("Cantidad Partidas Ganadas " + user.partidasGanadas);
	document.getElementById("userPganadas").style.color = "#000000";
	document.getElementById("userPganadas").style.fontWeight = "900";
	
	$("#userPperdidas").text("Cantidad Partidas Jugadas " + user.partidasJugadas);
	document.getElementById("userPperdidas").style.color = "#000000";
	document.getElementById("userPperdidas").style.fontWeight = "900";
	
}

function cancelarEsperaLibre() {
	if (!esperandoPartida){
		
		$.alertable.alert('No estas buscando partidas Libres.').always(function() {	
		});
		
		return
	}
	
	var url = '/WebTruco/Juegos';
	var cancelarEsperaL = {
			action : 'cancelarEspera'
	};
	
	document.getElementById('usuarioEnEspera').innerHTML = "Removido de espera";
	$
		.ajax({
			type : "POST",
			url : url,
			data : cancelarEsperaL, // serializes the form's elements.
			success : function(data) {
				esperandoPartida = false;	
			
			}
		});	
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
	document.getElementById('usuarioEnEspera').innerHTML = "Buscando partida...";
	$
			.ajax({
				type : "POST",
				url : url,
				data : unirsePartida, // serializes the form's elements.
				success : function(data) {
					esperandoPartida = true;	

				}
			});

}
function unirsePartidaPareja(apodo) {
	$("#loader").fadeIn("fast");
	
	usuarioSeleccionado = apodo;

	var unirsePartida = {
		action : 'unirsePartidaPareja',
		apodo : usuarioSeleccionado
	};
		
	document.getElementById('usuarioEnEspera').innerHTML = "Buscando partida en Pareja..."
	$.ajax({
		type : "POST",
		url : url,
		data : unirsePartida, // serializes the form's elements.
		success : function(data) {
			$("#loader").fadeOut("fast");

		}
	});
}

function abrirJuego(idJuego) {
	$("#loader").fadeIn("fast");

	openGameClass();
	juegoActual = idJuego;
	loopRenderGame();
}
function loopRenderGame() {
	renderGame();
	getCartas();
	notificaTurno();
	
	partidas = setInterval(function() {

		if (ganador == null) {

			renderGame();
			getCartas();
			// notificaTurno();
			notificaTanto();

		} else {
			
			clearInterval(partidas);


			$.confirm({
				title : 'Termino el juego ',
				theme : 'supervan',
				content : ganador,
				buttons : {
					ok : function() {
						back();
					},
				}
			});

		}

		console.log("rendering game pid=>" + partidas);

	}, 10000);

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
function render(data) {
	// var txtStatus = "<p style='color: blue'>Chico " + data.chicos.length
	// + "\n, Mano " + data.chicos[data.chicos.length - 1].manos.length
	// + " \n " + "Puntos [p1 "
	// + data.chicos[data.chicos.length - 1].puntosChico[0].puntos
	// + ", p2 "
	// + data.chicos[data.chicos.length - 1].puntosChico[1].puntos
	// + "]</p>";
	// document.getElementById('status').innerHTML = txtStatus;
	if (data.ganador!=null){
		ganador = "Pareja ganadora "
			+ data.ganador.jugadores[0].usuario.apodo + " y "+
	data.ganador.jugadores[1].usuario.apodo;
		
				
	}else{

	
	
	
		seCantoEnvido = data.chicos[data.chicos.length - 1].manos[data.chicos[data.chicos.length - 1].manos.length - 1].seCantoEnvido;
		seCantoTruco = data.chicos[data.chicos.length - 1].manos[data.chicos[data.chicos.length - 1].manos.length - 1].seCantoTruco;
		
		bazaActual = data.chicos[data.chicos.length - 1].manos[data.chicos[data.chicos.length - 1].manos.length - 1].bazas.length;
	
		if ((manoActual != null)
				&& (manoActual != data.chicos[data.chicos.length - 1].manos.length)) {
			alertFinMano(manoActual);
			manoActual = data.chicos[data.chicos.length - 1].manos.length;
		} else {
			manoActual = data.chicos[data.chicos.length - 1].manos.length;
	
		}
	
		if ((chicoActual != null) && (chicoActual != data.chicos.length)) {
			alertFinChico(chicoActual);
			chicoActual = data.chicos.length;
		} else {
			chicoActual = data.chicos.length;
		}
	
		var jugNum = 1;
		//	for (var i = 0; i < data.parejas.length; i++) {
		//
		//		for (var x = 0; x < data.parejas[i].jugadores.length; x++) {
		//
		//			drawCartas(data.parejas[i].jugadores[x], juegoActual, jugNum,
		//					(data.chicos[data.chicos.length - 1].manos.length));
		//			jugNum++;
		//		}
		//	}
	
		jug = data.chicos[data.chicos.length - 1].manos[data.chicos[data.chicos.length - 1].manos.length - 1].jugadores;
		for (var x = 0; x < jug.length; x++) {
	
			drawCartas(jug[x], jugNum,
					(data.chicos[data.chicos.length - 1].manos.length));
			jugNum++;
		}
	
		renderPunt(data);
	
		$("#loader").fadeOut("fast");
	}
}

function buscarUsuarios() {
	
	var url = '/WebTruco/Juegos';
	var buscarUsuarios = {
			action : 'getUsuarios'
	};
	
	$.ajax({
		type : "POST",
		url : url,
		data : buscarUsuarios, // serializes the form's elements.
		success : function(data) {
			usuariosActivos = data;

			listarUsuarios();
		}
	});
}

function listarUsuarios() {
	var lista = " <ol>";

	for (var i = 0; i < usuariosActivos.length; i++) {

		lista += "  <li><a href=\"#\" data-popup-close=\"popup-2\"  onclick=\"unirsePartidaPareja("
				+ usuariosActivos[i].apodo
				+ ")\">"
				+ usuariosActivos[i].apodo + "</a></li>";

	}
	lista += " </ol>";
	document.getElementById('usuariosl').innerHTML = lista;
	if (!tituloCargado) {
		tituloCargado = true;
		if (usuariosActivos.length == 0) {
			document.getElementById('UsuariosTit').innerHTML = "No hay usuarios loggeados.";
		} else {
			document.getElementById('UsuariosTit').innerHTML = "Usuarios loggeados";
		}
	}
	loadActions();
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

		lista += "  <li><a href=\"#\" data-popup-close=\"popup-1\"  onclick=\"abrirJuego("
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

			drawCartasSinJugar(data);

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
			if (data.TURNO == true) {
				notifyMe();
			}

		}
	});
}

function alertFinMano(mano) {

	$.confirm({
		title : 'Termino la mano ',
		theme : 'supervan',
		content : 'Termino la mano ' + mano,
		buttons : {
			ok : function() {
				loopRenderGame();
			},
		}
	});

	seCantoEnvido = false;
	seCantoTruco = false;
}

function alertFinChico(chico) {

	$.confirm({
		title : 'Termino el chico ',
		theme : 'supervan',
		content : 'Termino  el chico ' + chico,
		buttons : {
			ok : function() {
				loopRenderGame();
			},
		}
	});

	seCantoEnvido = false;
	seCantoTruco = false;
}
function alertTanto(tanto) {

	if (tanto == "ENVIDO") {

		$.confirm({
			title : 'Cantaron',
			theme : 'supervan',
			content : 'Cantaron ' + tanto,
			buttons : {
				aceptar : function() {
					notificaTantoRespuesta("QUIERO ENVIDO");
				},
				cancel : function() {
					notificaTantoRespuesta("NO QUIERO ENVIDO");
				},
				envido : function() {
					notificaTantoRespuesta("ENVIDO ENVIDO");
				},
				realEnvido : function() {
					notificaTantoRespuesta("REAL ENVIDO");
				},
				faltaEnvido : function() {
					notificaTantoRespuesta("FALTA ENVIDO");

				}
			}
		});
		seCantoEnvido = true;

	} else if (tanto == "ENVIDO ENVIDO") {

		$.confirm({
			title : 'Cantaron',
			theme : 'supervan',
			content : 'Cantaron ' + tanto,
			buttons : {
				aceptar : function() {
					notificaTantoRespuesta("QUIERO ENVIDO");
				},
				cancel : function() {
					notificaTantoRespuesta("NO QUIERO ENVIDO");
				},
				realEnvido : function() {
					notificaTantoRespuesta("REAL ENVIDO");
				},
				faltaEnvido : function() {
					notificaTantoRespuesta("FALTA ENVIDO");

				}
			}
		});
		seCantoEnvido = true;

	} else if (tanto == "FALTA ENVIDO") {

		$.confirm({
			title : 'Cantaron',
			theme : 'supervan',
			content : 'Cantaron ' + tanto,
			buttons : {
				aceptar : function() {
					notificaTantoRespuesta("QUIERO ENVIDO");
				},
				cancel : function() {
					notificaTantoRespuesta("NO QUIERO ENVIDO");
				}
			}
		});
		seCantoEnvido = true;

	} else if (tanto == "REAL ENVIDO") {

		$.confirm({
			title : 'Cantaron',
			theme : 'supervan',
			content : 'Cantaron ' + tanto,
			buttons : {
				aceptar : function() {
					notificaTantoRespuesta("QUIERO ENVIDO");
				},
				cancel : function() {
					notificaTantoRespuesta("NO QUIERO ENVIDO");
				},

				faltaEnvido : function() {
					notificaTantoRespuesta("FALTA ENVIDO");

				}
			}
		});
		seCantoEnvido = true;

	} else if (tanto == "TRUCO") {

		$.confirm({
			title : 'Cantaron',
			theme : 'supervan',
			content : 'Cantaron ' + tanto,
			buttons : {
				aceptar : function() {
					notificaTantoRespuesta("QUIERO TRUCO");
				},
				cancel : function() {
					notificaTantoRespuesta("NO QUIERO TRUCO");

				},
				retTruco : function() {
					notificaTantoRespuesta("RE TRUCO");

				},
				valeCuatro : function() {
					notificaTantoRespuesta("VALE CUATRO");

				}
			}
		});
		seCantoTruco = true;

	} else if (tanto == "RE TRUCO") {

		$.confirm({
			title : 'Cantaron',
			theme : 'supervan',
			content : 'Cantaron ' + tanto,
			buttons : {
				aceptar : function() {
					notificaTantoRespuesta("QUIERO TRUCO");

				},
				cancel : function() {
					notificaTantoRespuesta("NO QUIERO TRUCO");

				},
				valeCuatro : function() {
					notificaTantoRespuesta("VALE CUATRO");

				}
			}
		});
		seCantoTruco = true;

	} else if (tanto == "VALE CUATRO") {

		$.confirm({
			title : 'Cantaron',
			theme : 'supervan',
			content : 'Cantaron ' + tanto,
			buttons : {
				aceptar : function() {
					notificaTantoRespuesta("QUIERO TRUCO");

				},
				cancel : function() {
					notificaTantoRespuesta("NO QUIERO TRUCO");

				}
			}
		});
		seCantoTruco = true;

	}

}
function notificaTantoRespuesta(tantoResp) {
	var url = '/WebTruco/Juegos';

	var buscarJuegos = {
		action : 'responderTanto',
		idJuego : juegoActual,
		jug : tantoResp
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			tantoMsgVis = false;
			loopRenderGame();
		}
	});
}
function notificaTanto() {

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

			if ((data.TANTO != null)) {

				if (!tantoMsgVis) {
					alertTanto(data.TANTO);
					tantoMsgVis = true;
					notificaTurno();
				}
			} else {

			}

		}
	});
}

function openGameClass() {
	$("#mainButton").fadeOut("fast");
	document.getElementById("userApodo").innerHTML = "";
	document.getElementById('usuarioEnEspera').innerHTML = "";
	$("#bodyDiv").removeClass("body");
	$("#gradDiv").removeClass("grad");
}
function closeGameClass() {
	$("#mainButton").fadeIn("fast");

	document.getElementById("userApodo").innerHTML = "";

	$("#bodyDiv").addClass("body");
	$("#gradDiv").addClass("grad");

}
function drawCartasSinJugar(data) {

	var i = 0;

	var cartasImg = [];
	var imgtmp = "";

	while (i < data.length) {
		imgtmp = "<img src='./img/" + data[i].palo + "/" + data[i].numero
				+ ".jpg' style='cursor: pointer;' ";

		imgtmp += " onclick='jugarCarta(" + data[i].idCarta + ")' ";
		imgtmp += ">";

		cartasImg[i] = imgtmp;
		i++;

	}

	for (var x = i; x < 9; x++) {

		cartasImg[x] = "";
	}
	var tableDiv = "";
	tableDiv += '<div class="divTable">';
	tableDiv += '<div class="divTableBody">';

	tableDiv += '<div class="divTableRow">';
	tableDiv += '	<div class="divTableCell">';
	tableDiv += '		<div class="misCartas">Mis Cartas</div>';
	tableDiv += '	</div>';
	tableDiv += '</div>';
	tableDiv += '<div class="divTableRow">';
	tableDiv += '	<div class="divTableCell">' + cartasImg[0] + '</div>';
	tableDiv += '	<div class="divTableCell">' + cartasImg[1] + '</div>';
	tableDiv += '	<div class="divTableCell">' + cartasImg[2] + '</div>';
	tableDiv += '</div>';
	tableDiv += '</div>';
	tableDiv += '</div>';

	document.getElementById("misCartas").innerHTML = tableDiv;

}

function drawCartas(data, jugNum, mano) {

	var cartasArr = [];
//	var inx = (3 * (mano - 1));
	var inx = 0;
	if (data.cartas[inx] != undefined)
		cartasArr[0] = data.cartas[inx];

	if (data.cartas[inx + 1] != undefined)
		cartasArr[1] = data.cartas[inx + 1];

	if (data.cartas[inx + 2] != undefined)
		cartasArr[2] = data.cartas[inx + 2];

	var cartasImg = [];
	var imgtmp = "";

	var i = 0;

	var cartas = cartasArr.length;

	// si son 0 todo una linea de back
	if (cartas == 0) {

		for (var n = 0; n < 3; n++) {

			imgtmp = "<img src='./img/EMPTY/BACK.png' height='70%' ";

			if (jugNum == 3)
				imgtmp += " class='rotateimg90' ";

			if (jugNum == 4)
				imgtmp += " class='rotateimg-90'  ";

			imgtmp += ">";

			cartasImg[i] = imgtmp;
			i++;
		}

	}

	while (i < cartasArr.length) {

		imgtmp = "<img src='./img/" + cartasArr[i].palo + "/"
				+ cartasArr[i].numero + ".jpg' height='70%' ";

		if (jugNum == 3)
			imgtmp += " class='rotateimg90' ";

		if (jugNum == 4)
			imgtmp += " class='rotateimg-90'  ";

		imgtmp += ">";

		cartasImg[i] = imgtmp;
		i++;

	}

	while ((i % 3) != 0) {

		imgtmp = "<img src='./img/EMPTY/BACK.png' height='70%' ";

		if (jugNum == 3)
			imgtmp += " class='rotateimg90' ";

		if (jugNum == 4)
			imgtmp += " class='rotateimg-90'  ";

		imgtmp += ">";

		cartasImg[i] = imgtmp;

		i++;

	}

	for (var x = i; x < 9; x++) {
		imgtmp = "<img src='./img/EMPTY/EMPTY.png' height='70%' ";
		if (jugNum == 3)
			imgtmp += " class='rotateimg90' ";

		if (jugNum == 4)
			imgtmp += " class='rotateimg-90'  ";

		imgtmp += ">";

		cartasImg[x] = imgtmp;
	}

	var tableDiv = "";
	if (jugNum == 1) {

		tableDiv += '<div class="divTable">';
		tableDiv += '<div class="divTableBody">';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell"></div>';
		tableDiv += '<div class="divTableCell">' + data.usuario.apodo
				+ '</div>';
		tableDiv += '<div class="divTableCell"></div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[0] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[1] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[2] + '</div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[3] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[4] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[5] + '</div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[6] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[7] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[8] + '</div>';
		tableDiv += '</div>';

		tableDiv += '</div>';
		tableDiv += '</div>';
	}

	if (jugNum == 3) {

		tableDiv += '<div class="divTable">';
		tableDiv += '<div class="divTableBody">';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[6] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[3] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[0] + '</div>';

		tableDiv += '<div class="divTableCell"></div>';

		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';

		tableDiv += '<div class="divTableCell">' + cartasImg[7] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[4] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[1] + '</div>';

		tableDiv += '<div class="divTableCell">' + data.usuario.apodo
				+ '</div>';

		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[8] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[5] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[2] + '</div>';
		tableDiv += '<div class="divTableCell"></div>';

		tableDiv += '</div>';
		tableDiv += '</div>';
		tableDiv += '</div>';
	}
	if (jugNum == 2) {

		tableDiv += '<div class="divTable">';
		tableDiv += '<div class="divTableBody">';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[8] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[7] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[6] + '</div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[5] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[4] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[3] + '</div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + cartasImg[2] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[1] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[0] + '</div>';
		tableDiv += '</div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell"></div>';
		tableDiv += '<div class="divTableCell">' + data.usuario.apodo
				+ '</div>';
		tableDiv += '<div class="divTableCell"></div>';
		tableDiv += '</div>';
		tableDiv += '</div>';
		tableDiv += '</div>';
	}

	if (jugNum == 4) {

		tableDiv += '<div class="divTable">';
		tableDiv += '<div class="divTableBody">';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell"></div>';

		tableDiv += '<div class="divTableCell">' + cartasImg[0] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[3] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[6] + '</div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell">' + data.usuario.apodo
				+ '</div>';

		tableDiv += '<div class="divTableCell">' + cartasImg[1] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[4] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[7] + '</div>';
		tableDiv += '</div>';
		tableDiv += '<div class="divTableRow">';
		tableDiv += '<div class="divTableCell"></div>';

		tableDiv += '<div class="divTableCell">' + cartasImg[2] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[5] + '</div>';
		tableDiv += '<div class="divTableCell">' + cartasImg[8] + '</div>';
		tableDiv += '</div>';
		tableDiv += '</div>';
		tableDiv += '</div>';
	}

	document.getElementById("jug" + jugNum + "jug").innerHTML = '<div class="divTable">'
			+ '<div class="divTableBody">' + tableDiv + '</div>' + '</div>';

}
function renderPunt(data) {
	var turno = "";
	for (var i = 0; i < data.parejas.length; i++) {

		for (var x = 0; x < data.parejas[i].jugadores.length; x++) {

			if (data.parejas[i].jugadores[x].tieneTurno) {
				turno = data.parejas[i].jugadores[x].usuario.apodo;
				break;
			}

		}

	}

	var divPnt = "";
	divPnt += '<div class="divTable">';
	divPnt += '<div class="divTableBody">';

	divPnt += '<div class="divTableRow">';
	divPnt += '<div class="divTableCell">Turno De ' + turno + '</div>';
	divPnt += '<div class="divTableCell">Pareja 1  '
			+ data.parejas[0].jugadores[0].usuario.apodo + " "
			+ data.parejas[0].jugadores[1].usuario.apodo + '</div>';
	divPnt += '<div class="divTableCell">Puntos </div>';
	divPnt += '</div>';

	divPnt += '<div class="divTableRow">';
	divPnt += '<div class="divTableCell">Usuario ' + user.apodo + '</div>';
	divPnt += '<div class="divTableCell">Pareja 2  '
			+ data.parejas[1].jugadores[0].usuario.apodo + " "
			+ data.parejas[1].jugadores[1].usuario.apodo + '</div>';
	divPnt += '<div class="divTableCell">Pareja 1 = '
			+ data.chicos[0].puntosChico[0].puntos + ', Pareja 2 = '
			+ data.chicos[0].puntosChico[1].puntos + ' </div>';
	divPnt += '</div>';

	if (data.chicos.length >= 2) {
		divPnt += '<div class="divTableRow">';
		divPnt += '<div class="divTableCell">Pareja 1 '
				+ data.parejas[0].jugadores[0].usuario.apodo + " "
				+ data.parejas[0].jugadores[1].usuario.apodo + '</div>';
		divPnt += '<div class="divTableCell">Pareja 1 ='
				+ data.chicos[1].puntosChico[0].puntos + ', Pareja 2 = '
				+ data.chicos[1].puntosChico[1].puntos + ' </div>';
		divPnt += '</div>';
	}

	if (data.chicos.length >= 3) {

		divPnt += '<div class="divTableRow">';
		divPnt += '<div class="divTableCell">Pareja 2  '
				+ data.parejas[1].jugadores[0].usuario.apodo + " "
				+ data.parejas[1].jugadores[1].usuario.apodo + ' </div>';
		divPnt += '<div class="divTableCell">Pareja 1 = '
				+ data.chicos[2].puntosChico[0].puntos + ', Pareja 2 = '
				+ data.chicos[2].puntosChico[1].puntos + ' </div>';
		divPnt += '</div>';
	}

	divPnt += '<div class="divTableRow">';
	divPnt += '<div class="divTableCell">  </div>';

	divPnt += '</div>';

	divPnt += '<div class="divTableRow">';
	divPnt += '<div class="divTableCell"></div>';
	divPnt += '<div class="divTableCell"></div>';
	divPnt += '</div>';

	divPnt += '<div class="divTableRow">';
	divPnt += '<div class="divTableCell">' + makeButton('ENVIDO') + '</div>';
	divPnt += '<div class="divTableCell"></div>';
	divPnt += '</div>';

	divPnt += '<div class="divTableRow">';
	divPnt += '<div class="divTableCell">' + makeButton('TRUCO') + '</div>';
	divPnt += '<div class="divTableCell"></div>';
	divPnt += '</div>';
	divPnt += '</div>';
	divPnt += '</div>';

	divPnt += '<div class="divTableRow">';
	divPnt += '<div class="divTableCell">' + makeButton('SALIR') + '</div>';
	divPnt += '<div class="divTableCell"></div>';
	divPnt += '</div>';
	divPnt += '</div>';
	divPnt += '</div>';

	document.getElementById("divPuntosDatos").innerHTML = divPnt;

}

function envido() {

	if (bazaActual > 1) {
		$.alertable.alert('No se puede cantar envido luego de la primera Baza')
				.always(function() {

				});
	} else {

		if (seCantoEnvido) {

			$.alertable.alert('Ya se canto envido en la mano').always(
					function() {

					});

		} else {
			$.confirm({
				title : 'Cantar Envido',
				theme : 'supervan',
				content : 'Cantar Envido',
				buttons : {
					Envido : function() {
						cantarTanto("ENVIDO");

					},
					RealEnvido : function() {
						cantarTanto("REAL ENVIDO");

					},
					FaltaEnvido : function() {
						cantarTanto("FALTA ENVIDO");

					},
					Cancelar : function() {
						loopRenderGame();
					}
				}
			});
		}
	}
}

function truco() {
	if (seCantoTruco) {

		$.alertable.alert('Ya se canto truco en la mano').always(
				function() {

				});

	} else{
		cantarTanto('TRUCO');

	}

}

function cantarTanto(jugada) {

	var url = '/WebTruco/Juegos';

	var buscarJuegos = {
		action : 'cantarTanto',
		idJuego : juegoActual,
		jug : jugada
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			if (data.TURNO == false) {
				$.alertable.alert('No es su turno').always(function() {

				});
			} else if (data.CANTARON == true) {
				$.alertable.alert('Ya se canto el tanto').always(function() {

				});
			} else {
				$.alertable.alert('Se canto ' + jugada).always(function() {
					renderGame();
					ultimoTanto = jugada;

					if (jugada != "TRUCO")
						loopRenderGame();
				});
			}
		}
	});

}

function makeButton(key) {
	var but = '';

	if (key == 'TRUCO')
		but = '<button class="blob-btn"  onClick="truco()">';

	else if (key == 'SALIR')
		but = '<button class="blob-btn"  onClick="back()">';

	else if (key == 'ENVIDO')
		but = '<button class="blob-btn"  onClick="envido()">';

	but += key + ' <span class="blob-btn__inner"> <span';
	but += 'class="blob-btn__blobs"> <span class="blob-btn__blob"></span>';
	but += '<span class="blob-btn__blob"></span> <span class="blob-btn__blob"></span>';
	but += '	<span class="blob-btn__blob"></span>';
	but += '</span>';
	but += '</span>';
	but += '</button>';
	return but;
}

function jugarCarta(idCarta) {
	verificarTurno(jugar, idCarta);
}

function verificarTurno(callbk, idCarta, jugada) {

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

			if (data.CANTARON == true) {

				$.alertable.alert(
						'Cantaron tanto , antes de jugar espere que respondan')
						.always(function() {

						});

			} else if (data.TURNO == true) {
				if (idCarta != null)
					callbk(idCarta);
				else if (jugada != null)
					callbk(jugada);
				else
					callbk();

			} else {

				$.alertable.alert('No es su turno').always(function() {

				});

			}

		}
	});
}

function jugar(idCarta) {
	$("#loader").fadeIn("fast");
	var url = '/WebTruco/Juegos';

	var buscarJuegos = {
		action : 'JugarCarta',
		idJuego : juegoActual,
		idCarta : idCarta
	};

	$.ajax({
		type : "POST",
		url : url,
		data : buscarJuegos, // serializes the form's elements.
		success : function(data) {
			if (data.JUGADA == "TRUE") {
				renderGame();
				getCartas();

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
		// var notification = new Notification("Buenas ! " + user.apodo);
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
function logout() {
	var url = '/WebTruco/Logout';
	$.ajax({
		type : "POST",
		url : url,
		success : function(data) {
			window.location.replace("/WebTruco/index.jsp");
		}
	});
}