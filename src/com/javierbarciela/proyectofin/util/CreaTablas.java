/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iñigo
 */
public class CreaTablas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection cnn = null;
        Statement stmt = null;
        try {
            // Registrar el driver de la BBDD
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Conexión a la BBDD
            cnn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:ORCL",
                    "scott", "tiger");
            stmt = cnn.createStatement();
            
            try {
                stmt.executeUpdate("Drop table Categorias_JB");
            } catch (SQLException ex) {
            }
            
            try {
                stmt.executeUpdate("Drop table LineasPedidos_JB");
            } catch (SQLException ex) {
            }
            
            try {
                stmt.executeUpdate("Drop table Productos_JB");
            } catch (SQLException ex) {
            }
            
            try {
                stmt.executeUpdate("Drop table Pedidos_JB");
            } catch (SQLException ex) {
            }
            
            try {
                stmt.executeUpdate("Drop table Usuarios_JB");
            } catch (SQLException ex) {
            }
            
            // Categorías
            stmt.executeUpdate(
                    "Create Table Categorias_JB("
                    + "idCategoria number(3) constraint PK_CATEGORIAS_JB primary key,"
                    + "nombre varchar2(120) not null,"
                    + "descripcion varchar2(1000) not null,"
                    + "imagen varchar2(120) not null)");
            stmt.executeUpdate("Insert into Categorias_JB values(1,'Steam',"
                    + "'Steam es una plataforma de distribución digital,"
                    + " gestión digital de derechos, comunicaciones y"
                    + " servicios multijugador desarrollada por Valve Corporation.',"
                    + "'images/steamIcon.png')");
            stmt.executeUpdate("Insert into Categorias_JB values(2,'PlayStation4',"
                    + "'Es la cuarta videoconsola del modelo PlayStation.8​ Forma parte de"
                    + " las videoconsolas de octava generación. Fue anunciada oficialmente"
                    + " el 20 de febrero de 2013 en el evento PlayStation Meeting 2013.',"
                    + "'images/playstationIcon.png')");
            stmt.executeUpdate("Insert into Categorias_JB values(3,'XBOX One',"
                    + "'Es la tercera videoconsola de sobremesa de la marca Xbox, producida"
                    + " por Microsoft. Forma parte de las videoconsolas de octava generación,"
                    + " fue presentada por Microsoft el 21 de mayo de 2013.',"
                    + "'images/xboxIcon.png')");
            stmt.executeUpdate("Insert into Categorias_JB values(4,'Nintendo Switch',"
                    + "'es la séptima consola de videojuegos principal desarrollada por Nintendo."
                    + " Conocida en el desarrollo por su nombre código «NX», se dio a conocer en"
                    + " octubre de 2016 y fue lanzada mundialmente el 3 de marzo de 2017.',"
                    + "'images/nSwitchIcon.png')");

            //Usuarios
            stmt.executeUpdate("Create table Usuarios_JB("
                    + "idUsuario number(6) constraint PK_USUARIOS_JB primary key,"
                    + "nombre varchar2(120) not null,"
                    + "email varchar2(120) unique,"
                    + "foto varchar2(120),"
                    + "usuario varchar2(20) unique,"
                    + "hash varchar2(64) not null,"
                    + "salto varchar2(64) unique)");
            stmt.executeUpdate("Insert into Usuarios_JB Values(1,'Juan López','juanLopez@zabalevent.com','images/1.jpg',"
                    + "'juantxu','0b863d692af9e0118053bc7f4724c1c33c039e51f2bb299e631433ac347a15b9',"
                    + "'abcd')");
            stmt.executeUpdate("Insert into Usuarios_JB Values(2,'Ana Marín','anaMarin@zabalevent.com','images/2.jpg',"
                    + "'marinita','451b0c0b576b1e6d6ea43fb9c9f436e0bf40eac590345743f2ea8136c30d664c',"
                    + "'efgh')");
            stmt.executeUpdate("Insert into Usuarios_JB Values(3,'Sara Sanz','saraSanz@zabalevent.com','images/3.jpg',"
                    + "'sarandonga','c323fcdc9cc1f24bd1dfe77b63b3a194f7b81c66e189c64fe4c2bd3a008e0b15',"
                    + "'ijkl')");
            stmt.executeUpdate("Insert into Usuarios_JB Values(4,'Carlos Ginés','carlosGines@zabalevent.com','images/4.jpg',"
                    + "'ginebra','6d327b97d7ddaae5b5ade54537ca9f8984b412642d6ea9a7e16244fc09441a7e',"
                    + "'mnño')");

            //Productos
            stmt.executeUpdate(
                    "Create table Productos_JB("
                    + "idProducto number(6) constraint PK_PRODUCTOS_JB primary key,"
                    + "idUsuario number(6) constraint FK_PRODUCTOS_USUARIOS references Usuarios_JB,"
                    + "nombre varchar2(40) unique,"
                    + "descripcion varchar2(300) not null,"
                    + "valoracion number(1) not null check (valoracion>0 and valoracion<6),"
                    + "categoria number(3) references Categorias_JB,"
                    + "precio number(5,2) not null,"
                    + "imagen varchar2(120) not null,"
                    + "fechaAlta date not null,"
                    + "cantidad number(3) check (cantidad > -1))");

            stmt.executeUpdate("Insert into Productos_JB values("
                    + "1, 1, 'Hollow Knight', 'Metroidvania desarrollado por Cherry Team',"
                    + "4,1,14.99,'images/hk.png','16/03/2017',15)");
            
            stmt.executeUpdate("Insert into Productos_JB values("
                    + "2, 1, 'aollow Knight', 'Metroidvania hechito por Cherry Team',"
                    + "2,2,14.99,'images/hk.png','14/02/2017',0)");
            
            stmt.executeUpdate("Insert into Productos_JB values("
                    + "3, 1, 'bollow Knight', 'Metroidvania hacido por Cherry Team',"
                    + "1,4,14.99,'images/hk.png','12/02/2017',11)");
            
            stmt.executeUpdate("Insert into Productos_JB values("
                    + "4, 1, 'collow Knight', 'Metroidvania arrollado por Cherry Team',"
                    + "3,3,14.99,'images/hk.png','17/02/2017',15)");
            
            stmt.executeUpdate("Insert into Productos_JB values("
                    + "5, 1, 'Bioshock Infinite', 'Shooter en un mundo distópico',"
                    + "5,2,29.99,'images/bioshock.png','10/02/2017',7)");

            //Pedidos
            stmt.executeUpdate(
                    "Create table Pedidos_JB("
                    + "idPedido number(6) constraint PK_PEDIDOS_JB primary key,"
                    + "idUsuario number(6) constraint FK_PEDIDOSUSUARIOS_JB references Usuarios_JB,"
                    + "fechaPedido date not null)");

            stmt.executeUpdate("Insert into Pedidos_JB values("
                    + "1, 1, '27/04/2018')");
            
             //LíneasPedidos
            stmt.executeUpdate(
                    "Create table LineasPedidos_JB("
                    + "idPedido number(6) references Pedidos_JB,"
                    + "idProducto number(6) references Productos_JB,"
                    + "cantidad number(3),"
                    + "constraint PK_LINEASPEDIDOS_JB primary key (idPedido, idProducto))");

            stmt.executeUpdate("Insert into LineasPedidos_JB values("
                    + "1, 1, 1)");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreaTablas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreaTablas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
            }
            try {
                cnn.close();
            } catch (Exception ex) {
            }
        }
    }

}
