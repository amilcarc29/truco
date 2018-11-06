scriptTruco.sql
l

Tipo
SQL
Tamaño
42 KB (43.278 bytes)
Almacenamiento usado
0 bytesPropiedad de undefined
Ubicación
TPO
Propietario
lucas terrussi
Modificado
17:59 por lucas terrussi
Creación
17:59
Añadir descripción
Los lectores pueden descargar
USE [master]
GO
/****** Object:  Database [db_seminario]    Script Date: 5/11/2018 5:59:06 p. m. ******/
CREATE DATABASE [db_seminario]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'db_seminario', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\db_seminario.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'db_seminario_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\db_seminario_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [db_seminario] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [db_seminario].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [db_seminario] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [db_seminario] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [db_seminario] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [db_seminario] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [db_seminario] SET ARITHABORT OFF 
GO
ALTER DATABASE [db_seminario] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [db_seminario] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [db_seminario] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [db_seminario] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [db_seminario] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [db_seminario] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [db_seminario] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [db_seminario] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [db_seminario] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [db_seminario] SET  DISABLE_BROKER 
GO
ALTER DATABASE [db_seminario] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [db_seminario] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [db_seminario] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [db_seminario] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [db_seminario] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [db_seminario] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [db_seminario] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [db_seminario] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [db_seminario] SET  MULTI_USER 
GO
ALTER DATABASE [db_seminario] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [db_seminario] SET DB_CHAINING OFF 
GO
ALTER DATABASE [db_seminario] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [db_seminario] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [db_seminario] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'db_seminario', N'ON'
GO
ALTER DATABASE [db_seminario] SET QUERY_STORE = OFF
GO
USE [db_seminario]
GO
ALTER DATABASE SCOPED CONFIGURATION SET IDENTITY_CACHE = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [db_seminario]
GO
/****** Object:  Table [dbo].[Baza]    Script Date: 5/11/2018 5:59:06 p. m. ******/
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
/****** Object:  Table [dbo].[Carta]    Script Date: 5/11/2018 5:59:06 p. m. ******/
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
/****** Object:  Table [dbo].[Categoria]    Script Date: 5/11/2018 5:59:06 p. m. ******/
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
/****** Object:  Table [dbo].[Chico]    Script Date: 5/11/2018 5:59:06 p. m. ******/
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
/****** Object:  Table [dbo].[Envite]    Script Date: 5/11/2018 5:59:06 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Envite](
	[idEnvite] [int] IDENTITY(1,1) NOT NULL,
	[idMano] [int] NOT NULL,
	[idJugador] [int] NULL,
	[puntosQuiero] [int] NOT NULL,
	[puntosNoQuiero] [int] NOT NULL,
 CONSTRAINT [PK_Envite] PRIMARY KEY CLUSTERED 
(
	[idEnvite] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Grupo]    Script Date: 5/11/2018 5:59:06 p. m. ******/
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
/****** Object:  Table [dbo].[HistorialJugadas]    Script Date: 5/11/2018 5:59:06 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HistorialJugadas](
	[idHistorialJugadas] [int] IDENTITY(1,1) NOT NULL,
	[idJuego] [int] NOT NULL,
	[idChico] [int] NOT NULL,
	[idTipoJugada] [int] NOT NULL,
	[Fecha] [date] NOT NULL,
 CONSTRAINT [PK_HistorialJugadas] PRIMARY KEY CLUSTERED 
(
	[idHistorialJugadas] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Juego]    Script Date: 5/11/2018 5:59:06 p. m. ******/
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
/****** Object:  Table [dbo].[Jugada]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
/****** Object:  Table [dbo].[Jugador]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
	[cantoTruco] [bit] NULL,
	[cantoEnvido] [bit] NULL,
	[tieneTurno] [bit] NULL,
	[orden] [int] NULL,
	[tieneQueContestar] [bit] NULL,
 CONSTRAINT [PK_Jugador] PRIMARY KEY CLUSTERED 
(
	[idJugador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[JugadorCarta]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
/****** Object:  Table [dbo].[Mano]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
/****** Object:  Table [dbo].[Miembro]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
/****** Object:  Table [dbo].[Pareja]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
/****** Object:  Table [dbo].[Puntuacion]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
/****** Object:  Table [dbo].[TipoJugada]    Script Date: 5/11/2018 5:59:07 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TipoJugada](
	[idTipoJugada] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [nvarchar](50) NULL,
	[puntos] [int] NULL,
	[mensaje] [nvarchar](50) NULL,
 CONSTRAINT [PK_TipoJugada] PRIMARY KEY CLUSTERED 
(
	[idTipoJugada] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Truco]    Script Date: 5/11/2018 5:59:07 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Truco](
	[idTruco] [int] IDENTITY(1,1) NOT NULL,
	[idMano] [int] NULL,
	[idJugador] [int] NULL,
	[puntosQuiero] [int] NULL,
	[puntosNoQuiero] [int] NULL,
 CONSTRAINT [PK_Truco] PRIMARY KEY CLUSTERED 
(
	[idTruco] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 5/11/2018 5:59:07 p. m. ******/
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
ALTER TABLE [dbo].[Jugador] ADD  CONSTRAINT [DF_Jugador_tieneTurno]  DEFAULT ((0)) FOR [cantoTruco]
GO
ALTER TABLE [dbo].[JugadorCarta] ADD  DEFAULT ((0)) FOR [cartaJugada]
GO
ALTER TABLE [dbo].[Baza]  WITH CHECK ADD FOREIGN KEY([idMano])
REFERENCES [dbo].[Mano] ([idMano])
GO
ALTER TABLE [dbo].[Chico]  WITH CHECK ADD FOREIGN KEY([idJuego])
REFERENCES [dbo].[Juego] ([idJuego])
GO
ALTER TABLE [dbo].[Envite]  WITH CHECK ADD  CONSTRAINT [FK__Envite__idJugado__5EBF139D] FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Envite] CHECK CONSTRAINT [FK__Envite__idJugado__5EBF139D]
GO
ALTER TABLE [dbo].[Envite]  WITH CHECK ADD  CONSTRAINT [FK__Envite__idMano__5FB337D6] FOREIGN KEY([idMano])
REFERENCES [dbo].[Mano] ([idMano])
GO
ALTER TABLE [dbo].[Envite] CHECK CONSTRAINT [FK__Envite__idMano__5FB337D6]
GO
ALTER TABLE [dbo].[Grupo]  WITH CHECK ADD FOREIGN KEY([idUsuarioAdmin])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Grupo]  WITH CHECK ADD FOREIGN KEY([idUsuarioAdmin])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[HistorialJugadas]  WITH CHECK ADD FOREIGN KEY([idChico])
REFERENCES [dbo].[Chico] ([idChico])
GO
ALTER TABLE [dbo].[HistorialJugadas]  WITH CHECK ADD FOREIGN KEY([idJuego])
REFERENCES [dbo].[Juego] ([idJuego])
GO
ALTER TABLE [dbo].[HistorialJugadas]  WITH CHECK ADD FOREIGN KEY([idTipoJugada])
REFERENCES [dbo].[TipoJugada] ([idTipoJugada])
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD  CONSTRAINT [FK__Juego__idPareja1__60A75C0F] FOREIGN KEY([idPareja1])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego] CHECK CONSTRAINT [FK__Juego__idPareja1__60A75C0F]
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD FOREIGN KEY([idPareja1])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD  CONSTRAINT [FK__Juego__idPareja1__6B24EA82] FOREIGN KEY([idPareja1])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego] CHECK CONSTRAINT [FK__Juego__idPareja1__6B24EA82]
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD  CONSTRAINT [FK__Juego__idPareja2__619B8048] FOREIGN KEY([idPareja2])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Juego] CHECK CONSTRAINT [FK__Juego__idPareja2__619B8048]
GO
ALTER TABLE [dbo].[Juego]  WITH CHECK ADD FOREIGN KEY([idPareja2])
REFERENCES [dbo].[Pareja] ([idPareja])
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
ALTER TABLE [dbo].[Jugada]  WITH CHECK ADD  CONSTRAINT [FK__Jugada__idJugado__6EF57B66] FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Jugada] CHECK CONSTRAINT [FK__Jugada__idJugado__6EF57B66]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idJuego__6FE99F9F] FOREIGN KEY([idJuego])
REFERENCES [dbo].[Juego] ([idJuego])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idJuego__6FE99F9F]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idMiemb__59063A47] FOREIGN KEY([idMiembro])
REFERENCES [dbo].[Miembro] ([idMiembro])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idMiemb__59063A47]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idMiemb__70DDC3D8] FOREIGN KEY([idMiembro])
REFERENCES [dbo].[Miembro] ([idMiembro])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idMiemb__70DDC3D8]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idParej__5BE2A6F2] FOREIGN KEY([idPareja])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idParej__5BE2A6F2]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idParej__72C60C4A] FOREIGN KEY([idPareja])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idParej__72C60C4A]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idUsuar__74AE54BC] FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idUsuar__74AE54BC]
GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idUsuar__75A278F5] FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idUsuar__75A278F5]
GO
ALTER TABLE [dbo].[JugadorCarta]  WITH CHECK ADD FOREIGN KEY([idCarta])
REFERENCES [dbo].[Carta] ([idCarta])
GO
ALTER TABLE [dbo].[JugadorCarta]  WITH CHECK ADD  CONSTRAINT [FK__JugadorCa__idJug__778AC167] FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[JugadorCarta] CHECK CONSTRAINT [FK__JugadorCa__idJug__778AC167]
GO
ALTER TABLE [dbo].[Mano]  WITH CHECK ADD FOREIGN KEY([idChico])
REFERENCES [dbo].[Chico] ([idChico])
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD  CONSTRAINT [FK__Miembro__idGrupo__5165187F] FOREIGN KEY([idGrupo])
REFERENCES [dbo].[Grupo] ([idGrupo])
GO
ALTER TABLE [dbo].[Miembro] CHECK CONSTRAINT [FK__Miembro__idGrupo__5165187F]
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD FOREIGN KEY([idGrupo])
REFERENCES [dbo].[Grupo] ([idGrupo])
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD  CONSTRAINT [FK__Miembro__idUsuar__52593CB8] FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
GO
ALTER TABLE [dbo].[Miembro] CHECK CONSTRAINT [FK__Miembro__idUsuar__52593CB8]
GO
ALTER TABLE [dbo].[Miembro]  WITH CHECK ADD FOREIGN KEY([idUsuario])
REFERENCES [dbo].[Usuario] ([idUsuario])
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
ALTER TABLE [dbo].[Pareja]  WITH CHECK ADD  CONSTRAINT [FK__Pareja__idJugado__7D439ABD] FOREIGN KEY([idJugador2])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Pareja] CHECK CONSTRAINT [FK__Pareja__idJugado__7D439ABD]
GO
ALTER TABLE [dbo].[Pareja]  WITH CHECK ADD  CONSTRAINT [FK__Pareja__idJugado__7E37BEF6] FOREIGN KEY([idJugador1])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Pareja] CHECK CONSTRAINT [FK__Pareja__idJugado__7E37BEF6]
GO
ALTER TABLE [dbo].[Puntuacion]  WITH CHECK ADD FOREIGN KEY([idChico])
REFERENCES [dbo].[Chico] ([idChico])
GO
ALTER TABLE [dbo].[Puntuacion]  WITH CHECK ADD FOREIGN KEY([idPareja])
REFERENCES [dbo].[Pareja] ([idPareja])
GO
ALTER TABLE [dbo].[Truco]  WITH CHECK ADD  CONSTRAINT [FK__Truco__idJugador__03F0984C] FOREIGN KEY([idJugador])
REFERENCES [dbo].[Jugador] ([idJugador])
GO
ALTER TABLE [dbo].[Truco] CHECK CONSTRAINT [FK__Truco__idJugador__03F0984C]
GO
ALTER TABLE [dbo].[Truco]  WITH CHECK ADD  CONSTRAINT [FK__Truco__idMano__04E4BC85] FOREIGN KEY([idMano])
REFERENCES [dbo].[Mano] ([idMano])
GO
ALTER TABLE [dbo].[Truco] CHECK CONSTRAINT [FK__Truco__idMano__04E4BC85]
GO
ALTER TABLE [dbo].[Usuario]  WITH CHECK ADD FOREIGN KEY([idCategoria])
REFERENCES [dbo].[Categoria] ([idCategoria])
GO
ALTER TABLE [dbo].[Usuario]  WITH CHECK ADD FOREIGN KEY([idCategoria])
REFERENCES [dbo].[Categoria] ([idCategoria])
GO
USE [master]
GO
ALTER DATABASE [db_seminario] SET  READ_WRITE 
GO