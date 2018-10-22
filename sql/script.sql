USE [master]
GO
/****** Object:  Database [db_seminario]    Script Date: 10/22/2018 1:02:05 AM ******/
CREATE DATABASE [db_seminario]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'db_seminario', FILENAME = N'c:\Program Files (x86)\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\db_seminario.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'db_seminario_log', FILENAME = N'c:\Program Files (x86)\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\db_seminario_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
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
USE [db_seminario]
GO
/****** Object:  Table [dbo].[Baza]    Script Date: 10/22/2018 1:02:05 AM ******/
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
/****** Object:  Table [dbo].[Carta]    Script Date: 10/22/2018 1:02:05 AM ******/
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
/****** Object:  Table [dbo].[Categoria]    Script Date: 10/22/2018 1:02:05 AM ******/
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
/****** Object:  Table [dbo].[Chico]    Script Date: 10/22/2018 1:02:05 AM ******/
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
/****** Object:  Table [dbo].[Cliente]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Envite]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Grupo]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[HistorialJugadas]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Juego]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Jugada]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Jugador]    Script Date: 10/22/2018 1:02:06 AM ******/
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
	[tieneTurno] [bit] NOT NULL,
	[orden] [int] NULL,
 CONSTRAINT [PK_Jugador] PRIMARY KEY CLUSTERED 
(
	[idJugador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[JugadorCarta]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Mano]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Miembro]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Pareja]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Puntuacion]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Truco]    Script Date: 10/22/2018 1:02:06 AM ******/
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
/****** Object:  Table [dbo].[Usuario]    Script Date: 10/22/2018 1:02:06 AM ******/
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
SET IDENTITY_INSERT [dbo].[Carta] ON 

INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (1, 1, N'ESPADA', 14, 1)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (2, 1, N'BASTO', 13, 1)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (3, 7, N'ESPADA', 12, 7)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (4, 7, N'ORO', 11, 7)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (5, 3, N'ESPADA', 10, 3)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (6, 3, N'BASTO', 10, 3)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (7, 3, N'ORO', 10, 3)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (8, 3, N'COPA', 10, 3)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (9, 2, N'ESPADA', 10, 3)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (10, 2, N'BASTO', 9, 2)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (11, 2, N'ORO', 9, 2)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (12, 2, N'COPA', 9, 2)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (13, 1, N'COPA', 8, 1)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (14, 1, N'ORO', 8, 1)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (17, 12, N'ESPADA', 7, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (18, 12, N'BASTO', 7, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (19, 12, N'ORO', 7, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (20, 12, N'COPA', 7, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (21, 11, N'ESPADA', 6, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (22, 11, N'BASTO', 6, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (23, 11, N'ORO', 6, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (24, 11, N'COPA', 6, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (25, 10, N'ESPADA', 5, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (26, 10, N'BASTO', 5, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (27, 10, N'ORO', 5, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (28, 10, N'COPA', 5, 0)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (29, 7, N'BASTO', 4, 7)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (30, 7, N'COPA', 4, 7)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (31, 6, N'ESPADA', 3, 6)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (32, 6, N'BASTO', 3, 6)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (33, 6, N'ORO', 3, 6)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (34, 6, N'COPA', 3, 6)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (35, 5, N'ESPADA', 2, 5)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (36, 5, N'BASTO', 2, 5)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (37, 5, N'ORO', 2, 5)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (38, 5, N'COPA', 2, 5)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (39, 4, N'ESPADA', 1, 4)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (40, 4, N'BASTO', 1, 4)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (41, 4, N'ORO', 1, 4)
INSERT [dbo].[Carta] ([idCarta], [numero], [palo], [pesoTruco], [pesoEnvido]) VALUES (42, 4, N'COPA', 1, 4)
SET IDENTITY_INSERT [dbo].[Carta] OFF
SET IDENTITY_INSERT [dbo].[Categoria] ON 

INSERT [dbo].[Categoria] ([idCategoria], [nombre], [score], [minimoPartidas], [minimoPuntos], [promedioMinimo]) VALUES (3, N'NOVATO', 1, 1, 1, 1)
SET IDENTITY_INSERT [dbo].[Categoria] OFF
SET IDENTITY_INSERT [dbo].[Cliente] ON 

INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (1, 1, N'2222222', N'', N'', N'213213113123', N'qweqwewqe@wewe.com', N'213213', N' ', N'qweqwewqewqe', 2130, 1, 1, CAST(N'2018-07-15' AS Date), N'')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (2, 1, N'werwerwe', N'', N'', N'erwer', N'werwerwe', N'rwerwer', N' ', N'rwer', 3330, 1, 1, CAST(N'2018-07-15' AS Date), N'werewrewr')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (3, 1, N'werwerwe', N'', N'', N'erwer', N'werwerwe', N'rwerwer', N' ', N'rwer', 3330, 1, 1, CAST(N'2018-07-15' AS Date), N'werewrewr')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (4, 1, N'werwerwe', N'', N'', N'erwer', N'werwerwe', N'rwerwer', N' ', N'rwer', 3330, 1, 1, CAST(N'2018-07-15' AS Date), N'werewrewr')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (5, 1, N'werwerwe', N'', N'', N'erwer', N'werwerwe', N'rwerwer', N' ', N'rwer', 3330, 1, 1, CAST(N'2018-07-15' AS Date), N'werewrewr')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (6, 1, N'123213', N'', N'', N'qweqwewq', N'22', N'22', N' ', N'12322', 2220, 1, 1, CAST(N'2018-07-15' AS Date), N'222')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (7, 0, N'', N'qwewqeqw', N'qwewqe', N'', N'22', N'22', N'M', N'12322', 2220, 1, 1, CAST(N'2018-07-15' AS Date), N'222')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (8, 0, N'', N'qwewqeqw', N'qwewqe', N'', N'22', N'22', N'M', N'12322', 2220, 1, 1, CAST(N'2018-07-15' AS Date), N'222')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (9, 1, N'4234324', N'', N'', N'232343', N'4234', N'234', N' ', N'234', 230, 1, 1, CAST(N'2018-07-15' AS Date), N'23423')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (10, 0, N'', N'423432432', N'32432432', N'', N'324234', N'234234', N'F', N'4234', 30, 1, 1, CAST(N'2018-07-15' AS Date), N'4234')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (11, 0, N'', N'rwerwerwer', N'werew', N'', N'333', N'333', N'M', N'wer', 330, 1, 1, CAST(N'2018-07-15' AS Date), N'333')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (12, 1, N'312356407', N'', N'', N'Emiliano Sa', N'elaneri@gmail.com', N'1533884398', N' ', N'Uriburu 75', 1206, 1, 0, CAST(N'2018-07-18' AS Date), N'emiliano laneri')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (13, 0, N'', N'123', N'Juan pepe', N'', N'pepe@pepelandia.com', N'15099888', N'M', N'Pasaje 76', 123, 1, 0, CAST(N'2018-07-18' AS Date), N'pepe ')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (14, 1, N'2031235640', N'', N'', N'EJEMPLO', N'333@DDD.COM', N'21211222', N' ', N'PEPE 123', 122, 1, 0, CAST(N'2018-07-18' AS Date), N'213123')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (15, 1, N'123456', N'', N'', N'ejemoplo sa', N'ejemplo@ejemplo.com', N'123123', N' ', N'ejemplo', 123, 1, 0, CAST(N'2018-07-18' AS Date), N'test')
INSERT [dbo].[Cliente] ([id_cliente], [id_tipoCliente], [cuit], [dni], [nombre], [razonSocial], [email], [telefono], [sexo], [domicilio], [codigoPostal], [activo], [moroso], [fechaDeAlta], [contacto]) VALUES (16, 1, N'1834567', N'', N'', N'Emiliano Sa4', N'ejemplo@test.com', N'46780954', N' ', N'Ejemplo', 123, 1, 0, CAST(N'2018-07-18' AS Date), N'Emiliano')
SET IDENTITY_INSERT [dbo].[Cliente] OFF
SET IDENTITY_INSERT [dbo].[Usuario] ON 

INSERT [dbo].[Usuario] ([idUsuario], [partidasGanadas], [partidasJugadas], [puntaje], [apodo], [pass], [email], [idCategoria], [activo]) VALUES (5, 0, 0, 0, N'Emiliano', N'km4n7s28ehiFizeYupm93Q==', N'Emiliano', 3, 1)
INSERT [dbo].[Usuario] ([idUsuario], [partidasGanadas], [partidasJugadas], [puntaje], [apodo], [pass], [email], [idCategoria], [activo]) VALUES (6, 0, 0, 0, N'Debi', N'km4n7s28ehiFizeYupm93Q==', N'Debi', 3, 1)
INSERT [dbo].[Usuario] ([idUsuario], [partidasGanadas], [partidasJugadas], [puntaje], [apodo], [pass], [email], [idCategoria], [activo]) VALUES (7, 0, 0, 0, N'Lucas', N'km4n7s28ehiFizeYupm93Q==', N'Lucas', 3, 1)
INSERT [dbo].[Usuario] ([idUsuario], [partidasGanadas], [partidasJugadas], [puntaje], [apodo], [pass], [email], [idCategoria], [activo]) VALUES (8, 0, 0, 0, N'Amilcar', N'km4n7s28ehiFizeYupm93Q==', N'Amilcar', 3, 1)
INSERT [dbo].[Usuario] ([idUsuario], [partidasGanadas], [partidasJugadas], [puntaje], [apodo], [pass], [email], [idCategoria], [activo]) VALUES (13, 0, 0, 0, N'Emiliano2', N'km4n7s28ehiFizeYupm93Q==', N'Emiliano', 3, 1)
SET IDENTITY_INSERT [dbo].[Usuario] OFF
ALTER TABLE [dbo].[Cliente] ADD  DEFAULT ('1') FOR [activo]
GO
ALTER TABLE [dbo].[Cliente] ADD  CONSTRAINT [DF__Cliente__moroso__117F9D94]  DEFAULT ('0') FOR [moroso]
GO
ALTER TABLE [dbo].[Cliente] ADD  DEFAULT (getdate()) FOR [fechaDeAlta]
GO
ALTER TABLE [dbo].[Jugador] ADD  CONSTRAINT [DF_Jugador_tieneTurno]  DEFAULT ((0)) FOR [tieneTurno]
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
