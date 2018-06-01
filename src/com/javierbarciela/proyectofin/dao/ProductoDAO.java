/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;

import com.javierbarciela.proyectofin.modelo.Categoria;
import com.javierbarciela.proyectofin.modelo.Producto;
import com.javierbarciela.proyectofin.modelo.Usuario;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daw1
 */
public interface ProductoDAO {
    List<Producto> getProductos();
    List<Producto> getProductosUsuario(Usuario u);
    List<Producto> getProductosCategoria(Categoria c);
    Producto getProducto(int idProducto);
    void nuevoProducto(Producto p);
    void eliminarProducto(Producto p);
    void modificarProducto(Producto p);
    boolean poseeProductos(int idUsuario);
    void setProducto(int idProducto, int idusuario, String nombre,String descripcion,
            int valoracion, int idcategoria, double precio,String imagen, 
            Date fechaAlta, int cantidad);
    Producto leerProducto(ResultSet rst);
}
