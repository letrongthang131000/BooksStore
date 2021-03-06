USE [master]
GO
/****** Object:  Database [J3.L.P0018]    Script Date: 07/10/2021 21:49:15 ******/
CREATE DATABASE [J3.L.P0018] ON  PRIMARY 
( NAME = N'J3.L.P0018', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\J3.L.P0018.mdf' , SIZE = 2048KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'J3.L.P0018_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL10_50.SQLEXPRESS\MSSQL\DATA\J3.L.P0018_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [J3.L.P0018] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [J3.L.P0018].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [J3.L.P0018] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [J3.L.P0018] SET ANSI_NULLS OFF
GO
ALTER DATABASE [J3.L.P0018] SET ANSI_PADDING OFF
GO
ALTER DATABASE [J3.L.P0018] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [J3.L.P0018] SET ARITHABORT OFF
GO
ALTER DATABASE [J3.L.P0018] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [J3.L.P0018] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [J3.L.P0018] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [J3.L.P0018] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [J3.L.P0018] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [J3.L.P0018] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [J3.L.P0018] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [J3.L.P0018] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [J3.L.P0018] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [J3.L.P0018] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [J3.L.P0018] SET  DISABLE_BROKER
GO
ALTER DATABASE [J3.L.P0018] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [J3.L.P0018] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [J3.L.P0018] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [J3.L.P0018] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [J3.L.P0018] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [J3.L.P0018] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [J3.L.P0018] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [J3.L.P0018] SET  READ_WRITE
GO
ALTER DATABASE [J3.L.P0018] SET RECOVERY SIMPLE
GO
ALTER DATABASE [J3.L.P0018] SET  MULTI_USER
GO
ALTER DATABASE [J3.L.P0018] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [J3.L.P0018] SET DB_CHAINING OFF
GO
USE [J3.L.P0018]
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 07/10/2021 21:49:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [nvarchar](50) NOT NULL,
	[password] [varchar](30) NULL,
	[userName] [nvarchar](30) NULL,
	[isAdmin] [bit] NULL,
	[status] [varchar](20) NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblCategorys]    Script Date: 07/10/2021 21:49:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblCategorys](
	[categoryID] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblCategorys] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblDiscounts]    Script Date: 07/10/2021 21:49:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscounts](
	[code] [int] NOT NULL,
	[userID] [nvarchar](50) NULL,
	[isUsed] [bit] NULL,
	[discount] [float] NULL,
 CONSTRAINT [PK_tblDiscounts] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblBooks]    Script Date: 07/10/2021 21:49:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblBooks](
	[bookID] [int] IDENTITY(1,1) NOT NULL,
	[title] [nvarchar](100) NULL,
	[price] [float] NULL,
	[image] [nvarchar](max) NULL,
	[author] [nvarchar](50) NULL,
	[description] [nvarchar](200) NULL,
	[quantity] [int] NULL,
	[createDate] [date] NULL,
	[status] [varchar](50) NULL,
	[categoryID] [int] NULL,
 CONSTRAINT [PK_tblBooks] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 07/10/2021 21:49:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[orderDate] [datetime] NULL,
	[orderDay] [date] NULL,
	[userID] [nvarchar](50) NULL,
	[total] [float] NULL,
 CONSTRAINT [PK_tblOrders] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 07/10/2021 21:49:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[orderDetailID] [int] IDENTITY(1,1) NOT NULL,
	[bookID] [int] NULL,
	[quantity] [int] NULL,
	[unitPrice] [float] NULL,
	[total] [float] NULL,
	[orderID] [int] NULL,
	[title] [nvarchar](100) NULL,
 CONSTRAINT [PK_tblOrderDetails] PRIMARY KEY CLUSTERED 
(
	[orderDetailID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Default [DF_tblBooks_createDate]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblBooks] ADD  CONSTRAINT [DF_tblBooks_createDate]  DEFAULT (getdate()) FOR [createDate]
GO
/****** Object:  Default [DF_tblOrders_orderDate]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblOrders] ADD  CONSTRAINT [DF_tblOrders_orderDate]  DEFAULT (getdate()) FOR [orderDate]
GO
/****** Object:  Default [DF_tblOrders_orderDay]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblOrders] ADD  CONSTRAINT [DF_tblOrders_orderDay]  DEFAULT (getdate()) FOR [orderDay]
GO
/****** Object:  ForeignKey [FK_tblDiscounts_tblUsers]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblDiscounts]  WITH CHECK ADD  CONSTRAINT [FK_tblDiscounts_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblDiscounts] CHECK CONSTRAINT [FK_tblDiscounts_tblUsers]
GO
/****** Object:  ForeignKey [FK_tblBooks_tblCategorys1]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblBooks]  WITH CHECK ADD  CONSTRAINT [FK_tblBooks_tblCategorys1] FOREIGN KEY([categoryID])
REFERENCES [dbo].[tblCategorys] ([categoryID])
GO
ALTER TABLE [dbo].[tblBooks] CHECK CONSTRAINT [FK_tblBooks_tblCategorys1]
GO
/****** Object:  ForeignKey [FK_tblOrders_tblUsers]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK_tblOrders_tblUsers] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK_tblOrders_tblUsers]
GO
/****** Object:  ForeignKey [FK_tblOrderDetails_tblBooks]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblBooks] FOREIGN KEY([bookID])
REFERENCES [dbo].[tblBooks] ([bookID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblBooks]
GO
/****** Object:  ForeignKey [FK_tblOrderDetails_tblOrders]    Script Date: 07/10/2021 21:49:16 ******/
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblOrders] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblOrders]
GO
