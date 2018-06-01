/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;

import com.javierbarciela.proyectofin.modelo.Pedido;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author daw1
 */
public interface PedidoDAO {
    List<Pedido> getPedidos();
    Pedido getPedido(int idPedido);
    void nuevoPedido(Pedido p);
    void eliminarPedido(Pedido p);
    void modificarPedido(Pedido p);
    Pedido leerPedido(ResultSet rst);
}
