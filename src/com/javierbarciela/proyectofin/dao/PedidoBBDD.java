/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;

import com.javierbarciela.proyectofin.modelo.Pedido;
import com.javierbarciela.proyectofin.util.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class PedidoBBDD implements PedidoDAO{
    @Override
    public List<Pedido> getPedidos() {
        List<Pedido> productoUsuarios = new ArrayList<>();
        ResultSet rst;
        try {
            rst = Conexion.getConexion()
                    .createStatement()
                    .executeQuery("Select * From Pedidos_JB");
            while(rst.next()){
                Pedido pu = leerPedido(rst);
                productoUsuarios.add(pu);
            }
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productoUsuarios;
    }

    @Override
    public Pedido getPedido(int idPedido) {
        Pedido pu = new Pedido();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Select * From Pedidos_JB WHERE idPedido=?");
            pst.setInt(1, idPedido);
            rst = pst.executeQuery();
            pu = leerPedido(rst);
            pst.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pu;
    }

    @Override
    public void nuevoPedido(Pedido p) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Insert Into Pedidos_JB values ?,?,?");
            pst.setInt(1, p.getIdPedido());
            pst.setInt(2, p.getIdUsuario());
            pst.setDate(3, new java.sql.Date(p.getFechaPedido().getTime()));
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarPedido(Pedido p) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Delete From Pedidos_JB WHERE idPedido=?");
            pst.setInt(1, p.getIdPedido());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificarPedido(Pedido p) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Update Pedidos_JB Set idUsuario=?,"
                            + "fechaPedido=? WHERE idPedido=?");
            pst.setInt(1, p.getIdUsuario());
            pst.setDate(2, new java.sql.Date(p.getFechaPedido().getTime()));
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Pedido leerPedido(ResultSet rst) {
        Pedido p = new Pedido();
        try {
            p.setIdPedido(rst.getInt("idPedido"));
            p.setIdUsuario(rst.getInt("idUsuario"));
            p.setFechaPedido(rst.getDate("fechaPedido"));
        } catch (SQLException ex) {
            Logger.getLogger(PedidoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
