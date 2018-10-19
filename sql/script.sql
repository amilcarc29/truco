USE [db_seminario]
GO
/****** Object:  Table [dbo].[Baza]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Baza](
	[idBaza] [int] IDENTITY(1,1) NOT NULL,
	[idMano] [int] NULL,
	[idJugadaMayor] [int] NULL,
 CONSTRAINT [PK_Baza] PRIMARY KEY CLUSTERED 
(
	[idBaza] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Carta]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carta](
	[idCarta] [int] IDENTITY(1,1) NOT NULL,
	[numero] [int] NOT NULL,
	[palo] [nvarchar](50) NOT NULL,
	[pesoTruco] [int] NOT NULL,
	[pesoEnvido] [int] NOT NULL,
 CONSTRAINT [PK_Carta] PRIMARY KEY CLUSTERED 
(
	[idCarta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categoria]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categoria](
	[idCategoria] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[score] [int] NOT NULL,
	[minimoPartidas] [int] NOT NULL,
	[minimoPuntos] [int] NOT NULL,
	[promedioMinimo] [float] NOT NULL,
 CONSTRAINT [PK_Categoria] PRIMARY KEY CLUSTERED 
(
	[idCategoria] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Chico]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Chico](
	[idChico] [int] IDENTITY(1,1) NOT NULL,
	[idJuego] [int] NULL,
	[idParejaGanadora] [int] NULL,
	[puntosXGanar] [int] NOT NULL,
 CONSTRAINT [PK_Chico] PRIMARY KEY CLUSTERED 
(
	[idChico] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[id_cliente] [int] IDENTITY(1,1) NOT NULL,
	[id_tipoCliente] [int] NOT NULL,
	[cuit] [nvarchar](15) NULL,
	[dni] [nvarchar](10) NULL,
	[nombre] [nvarchar](50) NOT NULL,
	[razonSocial] [nvarchar](50) NULL,
	[email] [nvarchar](50) NOT NULL,
	[telefono] [nvarchar](50) NULL,
	[sexo] [nchar](1) NULL,
	[domicilio] [nvarchar](100) NULL,
	[codigoPostal] [int] NULL,
	[activo] [bit] NULL,
	[moroso] [bit] NULL,
	[fechaDeAlta] [date] NULL,
	[contacto] [nvarchar](100) NULL,
 CONSTRAINT [pk_idCliente] PRIMARY KEY CLUSTERED 
(
	[id_cliente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Envite]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Envite](
	[idEnvite] [int] IDENTITY(1,1) NOT NULL,
	[idMano] [int] NOT NULL,
	[idJugador] [int] NOT NULL,
	[tipo] [nvarchar](50) NOT NULL,
	[puntos] [int] NOT NULL,
	[ganador] [nvarchar](50) NULL,
 CONSTRAINT [PK_Envite] PRIMARY KEY CLUSTERED 
(
	[idEnvite] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Grupo]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Grupo](
	[idGrupo] [int] IDENTITY(1,1) NOT NULL,
	[idUsuarioAdmin] [int] NOT NULL,
	[nombre] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Grupo] PRIMARY KEY CLUSTERED 
(
	[idGrupo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HistorialJugadas]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HistorialJugadas](
	[idHistorialJugadas] [int] IDENTITY(1,1) NOT NULL,
	[idJuego] [int] NOT NULL,
	[idEnvite] [int] NOT NULL,
	[idTruco] [int] NOT NULL,
	[idJugada] [int] NOT NULL,
	[Fecha] [date] NOT NULL,
 CONSTRAINT [PK_HistorialJugadas] PRIMARY KEY CLUSTERED 
(
	[idHistorialJugadas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Juego]    Script Date: 10/18/2018 11:34:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Juego](
	[idJuego] [int] IDENTITY(1,1) NOT NULL,
	[idPareja1] [int] NOT NULL,
	[idPareja2] [int] NOT NULL,
	[idParejaGanadora] [int] NULL,
	[tipoDeJuego] [nvarchar](50) NULL,
	[fecha] [date] NOT NULL,
	[activo] [bit] NOT NULL,
 CONSTRAINT [PK_Juego] PRIMARY KEY CLUSTERED 
(
	[idJuego] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Jugada]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Jugada](
	[idJugada] [int] IDENTITY(1,1) NOT NULL,
	[idJugador] [int] NOT NULL,
	[idCarta] [int] NOT NULL,
	[idBaza] [int] NOT NULL,
 CONSTRAINT [PK_Jugada] PRIMARY KEY CLUSTERED 
(
	[idJugada] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Jugador]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Jugador](
	[idJugador] [int] IDENTITY(1,1) NOT NULL,
	[idUsuario] [int] NOT NULL,
	[idPareja] [int] NULL,
	[idMiembro] [int] NULL,
	[tipo] [nvarchar](50) NOT NULL,
	[idJuego] [int] NULL,
 CONSTRAINT [PK_Jugador] PRIMARY KEY CLUSTERED 
(
	[idJugador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[JugadorCarta]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[JugadorCarta](
	[idJugadorCarta] [int] IDENTITY(1,1) NOT NULL,
	[idJugador] [int] NOT NULL,
	[idCarta] [int] NOT NULL,
	[cartaJugada] [bit] NULL,
 CONSTRAINT [PK_JugadorCarta] PRIMARY KEY CLUSTERED 
(
	[idJugadorCarta] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Mano]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Mano](
	[idMano] [int] IDENTITY(1,1) NOT NULL,
	[idChico] [int] NULL,
 CONSTRAINT [PK_Mano] PRIMARY KEY CLUSTERED 
(
	[idMano] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Miembro]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Miembro](
	[idMiembro] [int] IDENTITY(1,1) NOT NULL,
	[idGrupo] [int] NOT NULL,
	[idUsuario] [int] NOT NULL,
	[puntaje] [int] NOT NULL,
	[enGrupo] [bit] NOT NULL,
 CONSTRAINT [PK_Miembro] PRIMARY KEY CLUSTERED 
(
	[idMiembro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pareja]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pareja](
	[idPareja] [int] IDENTITY(1,1) NOT NULL,
	[idJugador1] [int] NULL,
	[idJugador2] [int] NULL,
 CONSTRAINT [PK_Pareja] PRIMARY KEY CLUSTERED 
(
	[idPareja] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Puntuacion]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Puntuacion](
	[idPuntuacion] [int] IDENTITY(1,1) NOT NULL,
	[idChico] [int] NOT NULL,
	[idPareja] [int] NOT NULL,
	[puntos] [int] NOT NULL,
 CONSTRAINT [PK_Puntuacion] PRIMARY KEY CLUSTERED 
(
	[idPuntuacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Truco]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Truco](
	[idTruco] [int] IDENTITY(1,1) NOT NULL,
	[idMano] [int] NOT NULL,
	[idJugador] [int] NOT NULL,
	[tipo] [nvarchar](50) NOT NULL,
	[puntos] [int] NOT NULL,
	[ganador] [nvarchar](50) NULL,
 CONSTRAINT [PK_Truco] PRIMARY KEY CLUSTERED 
(
	[idTruco] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 10/18/2018 11:34:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[idUsuario] [int] IDENTITY(1,1) NOT NULL,
	[partidasGanadas] [int] NOT NULL,
	[partidasJugadas] [int] NOT NULL,
	[puntaje] [float] NOT NULL,
	[apodo] [nvarchar](50) NOT NULL,
	[pass] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[idCategoria] [int] NOT NULL,
	[activo] [bit] NOT NULL,
 CONSTRAINT [PK_Usuarios] PRIMARY KEY CLUSTERED 
(
	[idUsuario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Cliente] ADD  DEFAULT ('1') FOR [activo]
GO
ALTER TABLE [dbo].[Cliente] ADD  CONSTRAINT [DF__Cliente__moroso__117F9D94]  DEFAULT ('0') FOR [moroso]
GO
ALTER TABLE [dbo].[Cliente] ADD  DEFAULT (getdate()) FOR [fechaDeAlta]
GO
ALTER TABLE [dbo].[JugadorCarta] ADD  DEFAULT ((0)) FOR [cartaJugada]
GO
ALTER TABLE [dbo].[Baza]  WITH CHECK ADD FOREIGN KEY([idMano])
REFERENCES [dbo].[Mano] ([idMano])
GO
ALTER TABLE [dbo].[Chico]  WITH CHECK ADD FOREIGN KEY([idJuego])
REFERENCES [dbo].[Juego] ([idJuego])
GO
ALTER TABLE [dbo].[Envite]  WITH CHECK ADD FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Envite]  WITH CHECK ADD FOREIGN KEY([idMano])
REFERENCES [dbo].[Mano] ([idMano])
GO
ALTER TABLE [dbo].[Grupo]  WITH CHECK ADD FOREIGN KEY([idUsuarioAdmin])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Grupo]  WITH CHECK ADD FOREIGN KEY([idUsuarioAdmin])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[HistorialJugadas]  WITH CHECK ADD FOREIGN KEY([idEnvite])
REFERENCES [dbo].[Envite] ([idEnvite])
GO
ALTER TABLE [dbo].[HistorialJugadas]  WITH CHECK ADD FOREIGN KEY([idJuego])
REFERENCES [dbo].[Juego] ([idJuego])
GO
ALTER TABLE [dbo].[HistorialJugadas]  WITH CHECK ADD FOREIGN KEY([idJugada])
REFERENCES [dbo].[Jugada] ([idJugada])
GO
ALTER TABLE [dbo].[HistorialJugadas]  WITH CHECK ADD FOREIGN KEY([idTruco])
REFERENCES [dbo].[Truco] ([idTruco])
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD FOREIGN KEY([idPareja1])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD  CONSTRAINT [FK__Juego__idPareja1__60A75C0F] FOREIGN KEY([idPareja1])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego] CHECK CONSTRAINT [FK__Juego__idPareja1__60A75C0F]
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD  CONSTRAINT [FK__Juego__idPareja1__6B24EA82] FOREIGN KEY([idPareja1])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego] CHECK CONSTRAINT [FK__Juego__idPareja1__6B24EA82]
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD FOREIGN KEY([idPareja2])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD  CONSTRAINT [FK__Juego__idPareja2__619B8048] FOREIGN KEY([idPareja2])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego] CHECK CONSTRAINT [FK__Juego__idPareja2__619B8048]
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD  CONSTRAINT [FK__Juego__idPareja2__6C190EBB] FOREIGN KEY([idPareja2])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego] CHECK CONSTRAINT [FK__Juego__idPareja2__6C190EBB]
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD FOREIGN KEY([idParejaGanadora])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Jugada]  WITH CHECK ADD FOREIGN KEY([idBaza])
REFERENCES [dbo].[Baza] ([idBaza])
GO
ALTER TABLE [dbo].[Jugada]  WITH CHECK ADD FOREIGN KEY([idCarta])
REFERENCES [dbo].[Carta] ([idCarta])
GO
ALTER TABLE [dbo].[Jugada]  WITH CHECK ADD FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD FOREIGN KEY([idJuego])
REFERENCES [dbo].[Juego] ([idJuego])
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD FOREIGN KEY([idMiembro])
REFERENCES [dbo].[Miembro] ([idMiembro])
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idMiemb__59063A47] FOREIGN KEY([idMiembro])
REFERENCES [dbo].[Miembro] ([idMiembro])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idMiemb__59063A47]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD FOREIGN KEY([idPareja])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idParej__5BE2A6F2] FOREIGN KEY([idPareja])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idParej__5BE2A6F2]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[JugadorCarta]  WITH CHECK ADD FOREIGN KEY([idCarta])
REFERENCES [dbo].[Carta] ([idCarta])
GO
ALTER TABLE [dbo].[JugadorCarta]  WITH CHECK ADD FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Mano]  WITH CHECK ADD FOREIGN KEY([idChico])
REFERENCES [dbo].[Chico] ([idChico])
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD FOREIGN KEY([idGrupo])
REFERENCES [dbo].[Grupo] ([idGrupo])
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD  CONSTRAINT [FK__Miembro__idGrupo__5165187F] FOREIGN KEY([idGrupo])
REFERENCES [dbo].[Grupo] ([idGrupo])
GO
ALTER TABLE [dbo].[Miembro] CHECK CONSTRAINT [FK__Miembro__idGrupo__5165187F]
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD  CONSTRAINT [FK__Miembro__idUsuar__52593CB8] FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Miembro] CHECK CONSTRAINT [FK__Miembro__idUsuar__52593CB8]
GO
ALTER TABLE [dbo].[Pareja]  WITH CHECK ADD FOREIGN KEY([idJugador2])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Pareja]  WITH CHECK ADD FOREIGN KEY([idJugador1])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Pareja]  WITH CHECK ADD  CONSTRAINT [FK__Pareja__idJugado__5CD6CB2B] FOREIGN KEY([idJugador1])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Pareja] CHECK CONSTRAINT [FK__Pareja__idJugado__5CD6CB2B]
GO
ALTER TABLE [dbo].[Pareja]  WITH CHECK ADD  CONSTRAINT [FK__Pareja__idJugado__5DCAEF64] FOREIGN KEY([idJugador2])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Pareja] CHECK CONSTRAINT [FK__Pareja__idJugado__5DCAEF64]
GO
ALTER TABLE [dbo].[Puntuacion]  WITH CHECK ADD FOREIGN KEY([idChico])
REFERENCES [dbo].[Chico] ([idChico])
GO
ALTER TABLE [dbo].[Puntuacion]  WITH CHECK ADD FOREIGN KEY([idPareja])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Truco]  WITH CHECK ADD  CONSTRAINT [FK__Truco__idJugador__160F4887] FOREIGN KEY([idMano])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Truco] CHECK CONSTRAINT [FK__Truco__idJugador__160F4887]
GO
ALTER TABLE [dbo].[Truco]  WITH CHECK ADD FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Truco]  WITH CHECK ADD FOREIGN KEY([idMano])
REFERENCES [dbo].[Mano] ([idMano])
GO
ALTER TABLE [dbo].[Usuario]  WITH CHECK ADD FOREIGN KEY([idCategoria])
REFERENCES [dbo].[Categoria] ([idCategoria])
GO
ALTER TABLE [dbo].[Usuario]  WITH CHECK ADD FOREIGN KEY([idCategoria])
REFERENCES [dbo].[Categoria] ([idCategoria])
GO
ALTER TABLE [dbo].[Cliente]  WITH CHECK ADD  CONSTRAINT [chk_sexo] CHECK  (([sexo]='M' OR [sexo]='F' OR [sexo]=NULL))
GO
ALTER TABLE [dbo].[Cliente] CHECK CONSTRAINT [chk_sexo]
GO
USE [master]
GO
ALTER DATABASE [db_seminario] SET  READ_WRITE 
GO
