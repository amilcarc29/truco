<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<%@include file="loginCheck.jsp"%>
<title>CodePen - Random Login Form</title>

<style>
@import url(http://fonts.googleapis.com/css?family=Exo:100,200,400);

@import
	url(http://fonts.googleapis.com/css?family=Source+Sans+Pro:700,400,300)
	;

body {
	margin: 0;
	padding: 0;
	background: #fff;
	color: #fff;
	font-family: Arial;
	font-size: 12px;
}

.body {
	position: absolute;
	top: -20px;
	left: -20px;
	right: -40px;
	bottom: -40px;
	width: auto;
	height: auto;
	background-image: url(img/playing-card.jpg);
	background-size: cover;
	-webkit-filter: blur(5px);
	z-index: 0;
}

.grad {
	position: absolute;
	top: -20px;
	left: -20px;
	right: -40px;
	bottom: -40px;
	width: auto;
	height: auto;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(0, 0
		, 0, 0)), color-stop(100%, rgba(0, 0, 0, 0.65)));
	/* Chrome,Safari4+ */
	z-index: 1;
	opacity: 0.7;
}

.header {
	position: absolute;
	top: calc(20% - 35px);
	left: calc(20% - 255px);
	z-index: 2;
}

.header div {
	float: left;
	color: #fff;
	font-family: 'Exo', sans-serif;
	font-size: 35px;
	font-weight: 200;
}

.header div span {
	color: #5379fa !important;
}

.login {
	position: absolute;
	top: calc(50% - 75px);
	left: calc(50% - 50px);
	height: 150px;
	width: 350px;
	padding: 10px;
	z-index: 2;
}

.divGame {
	z-index: 999999;
}

.login input[type=text] {
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid rgba(255, 255, 255, 0.6);
	border-radius: 2px;
	color: #fff;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 4px;
}

.login input[type=password] {
	width: 250px;
	height: 30px;
	background: transparent;
	border: 1px solid rgba(255, 255, 255, 0.6);
	border-radius: 2px;
	color: #fff;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 4px;
	margin-top: 10px;
}

.login input[type=button] {
	width: 260px;
	height: 35px;
	background: #fff;
	border: 1px solid #fff;
	cursor: pointer;
	border-radius: 2px;
	color: #a18d6c;
	font-family: 'Exo', sans-serif;
	font-size: 16px;
	font-weight: 400;
	padding: 6px;
	margin-top: 10px;
}

.login input[type=button]:hover {
	opacity: 0.8;
}

.login input[type=button]:active {
	opacity: 0.6;
}

.login input[type=text]:focus {
	outline: none;
	border: 1px solid rgba(255, 255, 255, 0.9);
}

.login input[type=password]:focus {
	outline: none;
	border: 1px solid rgba(255, 255, 255, 0.9);
}

.login input[type=button]:focus {
	outline: none;
}

::-webkit-input-placeholder {
	color: rgba(255, 255, 255, 0.6);
}

::-moz-input-placeholder {
	color: rgba(255, 255, 255, 0.6);
}
/* Outer */
.popup {
	width: 100%;
	height: 100%;
	display: none;
	position: fixed;
	top: 0px;
	left: 0px;
	background: rgba(0, 0, 0, 0.75);
	z-index: 9000;
}

.popup2 {
	width: 100%;
	height: 100%;
	display: none;
	position: fixed;
	top: 0px;
	left: 0px;
	background: rgba(0, 0, 0, 0.75);
	z-index: 10000;
}

/* Inner */
.popup-inner {
	max-width: 1000px;
	width: 90%;
	padding: 40px;
	position: absolute;
	top: 50%;
	left: 50%;
	-webkit-transform: translate(-50%, -50%);
	transform: translate(-50%, -50%);
	box-shadow: 0px 2px 6px rgba(0, 0, 0, 1);
	border-radius: 3px;
	background: #fff;
}

/* Close Button */
.popup-close {
	width: 30px;
	height: 30px;
	padding-top: 4px;
	display: inline-block;
	position: absolute;
	top: 0px;
	right: 0px;
	transition: ease 0.25s all;
	-webkit-transform: translate(50%, -50%);
	transform: translate(50%, -50%);
	border-radius: 1000px;
	background: rgba(0, 0, 0, 0.8);
	font-family: Arial, Sans-Serif;
	font-size: 20px;
	text-align: center;
	line-height: 100%;
	color: #fff;
}

.popup-close:hover {
	-webkit-transform: translate(50%, -50%) rotate(180deg);
	transform: translate(50%, -50%) rotate(180deg);
	background: rgba(0, 0, 0, 1);
	text-decoration: none;
}

/* css list with numeber circle background -------------- */
.numberlist {
	width: 450px;
}

.numberlist ol {
	counter-reset: li;
	list-style: none;
	*list-style: decimal;
	font: 15px 'trebuchet MS', 'lucida sans';
	padding: 0;
	margin-bottom: 4em;
}

.numberlist ol ol {
	margin: 0 0 0 2em;
}

.numberlist a {
	position: relative;
	display: block;
	padding: .4em .4em .4em 2em;
	*padding: .4em;
	margin: .5em 0;
	background: #FFF;
	color: #444;
	text-decoration: none;
	-moz-border-radius: .3em;
	-webkit-border-radius: .3em;
	border-radius: .3em;
}

.numberlist a:hover {
	background: #cbe7f8;
	text-decoration: underline;
}

.numberlist a:before {
	content: counter(li);
	counter-increment: li;
	position: absolute;
	left: -1.3em;
	top: 50%;
	margin-top: -1.3em;
	background: #87ceeb;
	height: 2em;
	width: 2em;
	line-height: 2em;
	border: .3em solid #fff;
	text-align: center;
	font-weight: bold;
	-moz-border-radius: 2em;
	-webkit-border-radius: 2em;
	border-radius: 2em;
	color: #FFF;
}

/* DivTable.com */
.divTable {
	display: table;
	width: 100%;
	padding-left: 0%;
}
/* DivTable.com */
.divTableCenter {
	display: table;
	width: 100%;
}
.divTableRow {
	display: table-row;
}

.divTableHeading {
	background-color: #EEE;
	display: table-header-group;
}

.divTableCell, .divTableHead {
	display: table-cell;
	padding: 3px 10px;
}
.divTableCell2, .divTableHead {
	display: table-cell;
	padding: 3px 10px;
	width: 50%;
}
.divTableCell1, .divTableHead {
	display: table-cell;
	padding: 3px 10px;
	width: 100%;
}

.divTableHeading {
	background-color: #EEE;
	display: table-header-group;
	font-weight: bold;
}

.divTableFoot {
	background-color: #EEE;
	display: table-footer-group;
	font-weight: bold;
}

.divTableBody {
	display: table-row-group;
}

.rotateimg90 {
	-webkit-transform: rotate(90deg);
	-moz-transform: rotate(90deg);
	-ms-transform: rotate(90deg);
	-o-transform: rotate(90deg);
	transform: rotate(90deg);
}

.rotateimg-90 {
	-webkit-transform: rotate(-90deg);
	-moz-transform: rotate(-90deg);
	-ms-transform: rotate(-90deg);
	-o-transform: rotate(-90deg);
	transform: rotate(-90deg);
}

.imgBTable {
	background-image: url("./img/table.jpg");
	background-size: 100%;
}
.misCartas{
    font-size: 150%;


}
.loader {
    position: fixed;
    left: 0px;
    top: 0px;
    width: 100%;
    height: 100%;
    z-index: 999!important;
    background: url(./img/load.gif) 50% 50% no-repeat rgba(41, 37, 37, 0.89);
    background-size: 55px 55px;
   
}



</style>
<link rel="stylesheet" href="css/jquery.alertable.css">
<link rel="stylesheet" href="css/game.css">

<script src='js/jquery-3.3.1.min.js'></script>
<script src="js/prefixfree.min.js"></script>
<script src="js/jquery.alertable.js"></script>
<script src="js/main.js"></script>
<script>
	var user =
<%=session.getAttribute("userJson")%>
	;
</script>
</head>

<body>


	<div class="loader" id="loader"></div>
	<div class="body" id="bodyDiv"></div>
	<div class="grad" id="gradDiv"></div>
	<div class="header" id="headerDiv">


		<div id="main">
			<span id="userApodo"></span>

			<h6 id="usuarioEnEspera"></h6>

		</div>
		<div id="mainButton">

			<button class="blob-btn" onCLick="unirsePartidaLibre()">
				Unirse Partida Libre <span class="blob-btn__inner"> <span
					class="blob-btn__blobs"> <span class="blob-btn__blob"></span>
						<span class="blob-btn__blob"></span> <span class="blob-btn__blob"></span>
						<span class="blob-btn__blob"></span>
				</span>
				</span>
			</button>

			<button class="blob-btn" data-popup-open="popup-1">
				Mis Juegos <span class="blob-btn__inner"> <span
					class="blob-btn__blobs"> <span class="blob-btn__blob"></span>
						<span class="blob-btn__blob"></span> <span class="blob-btn__blob"></span>
						<span class="blob-btn__blob"></span>
				</span>
				</span>
			</button>
		</div>

	</div>
	<br>

	<div class="imgBTable">
		<div class="divTable">
			<div class="divTableBody">
				<div class="divTableRow">
					<div class="divTableCell">&nbsp;</div>
					<div id="jug3" class="divTableCell">&nbsp;</div>
					<div id="status" class="divTableCell">&nbsp;</div>
				</div>
				<div class="divTableRow">
					<div id="jug2" class="divTableCell">&nbsp;</div>
					<div class="divTableCell">
						<div class="divTable">
							<div class="divTableBody">
								<div class="divTableRow">
									<div class="divTableCell" id="divPuntosDatos">
									
									
									
									</div>
									<div id="jug3jug" class="divTableCell">&nbsp;</div>
									<div class="divTableCell" id ="misCartas">&nbsp;</div>
								</div>
								<div class="divTableRow">
									<div id="jug2jug" class="divTableCell">&nbsp;</div>
									<div class="divTableCell">

									</div>
									<div id="jug4jug" class="divTableCell">&nbsp;</div>
								</div>
								<div class="divTableRow">
									<div class="divTableCell">&nbsp;</div>
									<div id="jug1jug" class="divTableCell">&nbsp;</div>
									<div class="divTableCell" >&nbsp;</div>
								</div>
							</div>
						</div>
					</div>
					<div id="jug4" class="divTableCell">&nbsp;</div>
				</div>
				<div class="divTableRow">
					<div class="divTableCell">&nbsp;</div>
					<div id="jug1" class="divTableCell">&nbsp;</div>
					<div class="divTableCell">&nbsp;</div>
				</div>
			</div>
		</div>
	</div>



	<div class="popup" data-popup="popup-1">
		<div class="popup-inner">
			<h2 id="JuegosTit" style="color: black;">Cargando Juegos...</h2>


			<div id="games" class="numberlist"></div>




			<a class="popup-close" data-popup-close="popup-1" href="#">x</a>
		</div>
	</div>





</body>

</html>