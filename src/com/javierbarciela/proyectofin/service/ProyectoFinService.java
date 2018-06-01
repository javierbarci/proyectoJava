/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.service;

import com.javierbarciela.proyectofin.dao.CategoriaBBDD;
import com.javierbarciela.proyectofin.dao.PedidoBBDD;
import com.javierbarciela.proyectofin.dao.ProductoBBDD;
import com.javierbarciela.proyectofin.dao.UsuarioBBDD;
import com.javierbarciela.proyectofin.modelo.Categoria;
import com.javierbarciela.proyectofin.modelo.Pedido;
import com.javierbarciela.proyectofin.modelo.Producto;
import com.javierbarciela.proyectofin.modelo.Usuario;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author daw1
 */
public class ProyectoFinService {
    private CategoriaBBDD categoriaBBDD = new CategoriaBBDD();
    private ProductoBBDD productoBBDD = new ProductoBBDD();
    private UsuarioBBDD usuarioBBDD = new UsuarioBBDD();
    private PedidoBBDD pedidoBBDD = new PedidoBBDD();
    /*private CategoriaDAO categoriaDAO = new CategoriaList();
    private EventoDAO eventoDAO = new EventoList();
    private LugarDAO lugarDAO = new LugarBBDD();
    private UsuarioDAO usuarioDAO = new UsuarioList();
    private UsuarioEventoDAO usuarioEventoDAO = new UsuarioEventoList();*/
    
    public Usuario login(String identificativo, String password){
        Usuario u = null;
        boolean esCorreo;
        if (identificativo.indexOf("@") != -1){ // Comprueba si se ha insertado email o nick
            esCorreo = true;
        } else {
            esCorreo = false;
        }
        if (esCorreo) { // busco el usuario ya sea por email o nick
            u = usuarioBBDD.getUsuarioEmail(identificativo, password);
        } else {
            u = usuarioBBDD.getUsuarioNick(identificativo, password);
        }
        /*u = usuarioBBDD.getUsuarioEmail(identificativo, password);
        if (u.equals(null)){
            u = usuarioBBDD.getUsuarioNick(identificativo, password);
        }*/
        return u;
    }
    
    public void registrarse(String nombrePila, String email, String nick, String pwd){
        Usuario u = usuarioBBDD.getUsuarioEmail(email, pwd);
        if (u == null){
            u = usuarioBBDD.getUsuarioNick(nick, pwd);
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese email", 
                    "Email repetido", JOptionPane.ERROR_MESSAGE);
        }
        if (u == null){ // No existe un usuario con ese email o nick
            usuarioBBDD.registrarUsuario(nombrePila, email, nick, pwd);
        } else { // Existe un usuario con ese email o nick
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre de usuario", 
                    "Nombre de usuario repetido", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarioBBDD.getUsuarios();
    }

    public Usuario getUsuarioNick(String nick, String pwd) {
        return usuarioBBDD.getUsuarioNick(nick, pwd);
    }

    public Usuario getUsuarioEmail(String email, String pwd) {
        return usuarioBBDD.getUsuarioEmail(email, pwd);
    }
    
    public Usuario getUsuario(int idUsuario) {
        return usuarioBBDD.getUsuario(idUsuario);
    }

    public void registrarUsuario(String nombrePila, String email, String nick, String pwd) {
        usuarioBBDD.registrarUsuario(nombrePila, email, nick, pwd);
    }

    public void eliminarUsuario(Usuario u) {
        usuarioBBDD.eliminarUsuario(u);
    }

    public void modificarUsuario(Usuario u) {
        usuarioBBDD.modificarUsuario(u);
    }

    public Usuario leerUsuario(ResultSet rst) {
        return usuarioBBDD.leerUsuario(rst);
    }

    public List<Producto> getProductos() {
        return productoBBDD.getProductos();
    }

    public List<Producto> getProductosUsuario(Usuario u) {
        return productoBBDD.getProductosUsuario(u);
    }
    
    public List<Producto> getProductosCategoria(Categoria c) {
        return productoBBDD.getProductosCategoria(c);
    }

    public Producto getProducto(int idProducto) {
        return productoBBDD.getProducto(idProducto);
    }

    public void nuevoProducto(Producto p) {
        productoBBDD.nuevoProducto(p);
    }

    public void eliminarProducto(Producto p) {
        productoBBDD.eliminarProducto(p);
    }

    public void modificarProducto(Producto p) {
        productoBBDD.modificarProducto(p);
    }

    public boolean poseeProductos(int idUsuario) {
        return productoBBDD.poseeProductos(idUsuario);
    }

    public void setProducto(int idProducto, int idusuario, String nombre, String descripcion, int valoracion, int idcategoria, double precio, String imagen, Date fechaAlta, int cantidad) {
        productoBBDD.setProducto(idProducto, idusuario, nombre, descripcion, valoracion, idcategoria, precio, imagen, fechaAlta, cantidad);
    }

    public Producto leerProducto(ResultSet rst) {
        return productoBBDD.leerProducto(rst);
    }

    public List<Pedido> getPedidos() {
        return pedidoBBDD.getPedidos();
    }

    public Pedido getPedido(int idPedido) {
        return pedidoBBDD.getPedido(idPedido);
    }

    public void nuevoPedido(Pedido p) {
        pedidoBBDD.nuevoPedido(p);
    }

    public void eliminarPedido(Pedido p) {
        pedidoBBDD.eliminarPedido(p);
    }

    public void modificarPedido(Pedido p) {
        pedidoBBDD.modificarPedido(p);
    }

    public Pedido leerPedido(ResultSet rst) {
        return pedidoBBDD.leerPedido(rst);
    }

    public List<Categoria> getCategorias() {
        return categoriaBBDD.getCategorias();
    }

    public Categoria getCategoria(int idCategoria) {
        return categoriaBBDD.getCategoria(idCategoria);
    }

    public void nuevaCategoria(Categoria c) {
        categoriaBBDD.nuevaCategoria(c);
    }

    public void eliminarCategoria(Categoria c) {
        categoriaBBDD.eliminarCategoria(c);
    }

    public void modificarCategoria(Categoria c) {
        categoriaBBDD.modificarCategoria(c);
    }

    public Categoria leerCategoria(ResultSet rst) {
        return categoriaBBDD.leerCategoria(rst);
    }

    @Override
    public int hashCode() {
        return categoriaBBDD.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return categoriaBBDD.equals(o);
    }

    @Override
    public String toString() {
        return categoriaBBDD.toString();
    }
    
    
}
