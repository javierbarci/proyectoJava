/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;

import com.javierbarciela.proyectofin.modelo.Categoria;
import com.javierbarciela.proyectofin.modelo.Producto;
import com.javierbarciela.proyectofin.modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.javierbarciela.proyectofin.util.Conexion;
import java.util.Date;

/**
 *
 * @author daw1
 */
public class ProductoBBDD implements ProductoDAO{

    @Override
    public List<Producto> getProductos() {
        List<Producto> productos = new ArrayList<>();
        ResultSet rst;
        try {
            rst = Conexion.getConexion()
                    .createStatement()
                    .executeQuery("Select * From Productos_JB");
            while(rst.next()){
                Producto p = leerProducto(rst);
                productos.add(p);
            }
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    @Override
    public List<Producto> getProductosUsuario(Usuario u) {
        List<Producto> productos = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Select * From Productos_JB Where idUsuario=?");
            pst.setInt(1, u.getIdUsuario());
            rst = pst.executeQuery();
            while(rst.next()){
                Producto p = leerProducto(rst);
                productos.add(p);
            }
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
    @Override
    public List<Producto> getProductosCategoria(Categoria c){
        List<Producto> productos = new ArrayList<>();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Select * From Productos_JB Where categoria=?");
            pst.setInt(1, c.getIdCategoria());
            rst = pst.executeQuery();
            while(rst.next()){
                Producto p = leerProducto(rst);
                productos.add(p);
            }
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    @Override
    public Producto getProducto(int idProducto) {
        Producto p = new Producto();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement(
                            "Select * from Productos_JB "
                                    + "WHERE idproducto=?");
            pst.setInt(1, idProducto);
            rst = pst.executeQuery();
            if(rst.next()){
                p = leerProducto(rst);
            }
            pst.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public void nuevoProducto(Producto p) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion().prepareStatement("Insert Into Productos_JB"
                    + " values (?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, p.getIdProducto());
            pst.setInt(2, p.getIdUsuario());
            pst.setString(3, p.getNombre());
            pst.setString(4, p.getDescripcion());
            pst.setInt(5, p.getValoracion());
            pst.setInt(6, p.getCategoria());
            pst.setDouble(7, p.getPrecio());
            pst.setString(8, p.getImagen());
            pst.setDate(9, new java.sql.Date(p.getFechaAlta().getTime()));
            pst.setInt(10, p.getCantidad());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarProducto(Producto p) {
        PreparedStatement pst;
        PreparedStatement pst2;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Delete from LineasPedidos_JB "
                            + "WHERE idproducto=?");
            pst.setInt(1, p.getIdProducto());
            pst.executeUpdate();
            pst.close();
            pst2 = Conexion.getConexion()
                    .prepareStatement("Delete from Productos_JB "
                            + "WHERE idproducto=?");
            pst2.setInt(1, p.getIdProducto());
            pst2.executeUpdate();
            pst2.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificarProducto(Producto p) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Update Productos_JB set "
                            + "idUsuario=?,"
                            + "nombre=?,"
                            + "descripcion=?,valoracion=?,"
                            + "categoria=?,"
                            + "precio=?,imagen=?,"
                            + "fechaAlta=?,cantidad=? "
                            + "WHERE idProducto=?");
            pst.setInt(1, p.getIdUsuario());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getDescripcion());
            pst.setInt(4, p.getValoracion());
            pst.setInt(5, p.getCategoria());
            pst.setDouble(6, p.getPrecio());
            pst.setString(7, p.getImagen());
            pst.setDate(8, new java.sql.Date(p.getFechaAlta().getTime()));
            pst.setInt(9, p.getCantidad());
            pst.setInt(10, p.getIdProducto());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean poseeProductos(int idUsuario){
        PreparedStatement pst;
        ResultSet rst;
        Producto p = null;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Select * from Productos_JB where idUsuario=?");
            pst.setInt(1, idUsuario);
            rst = pst.executeQuery();
            if (rst.next()){
                p = leerProducto(rst);
            }
            pst.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (p != null){
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void setProducto(int idProducto, int idusuario, String nombre,
    String descripcion, int valoracion, int idcategoria, double precio,
            String imagen, Date fechaAlta, int cantidad){ //probablemente tenga que 
        PreparedStatement pst;                                              //editarlo para actualizar todo
        try {                                               //hace lo mismo que modificarPedido() pero con los datos como parámetros en vez del objeto producto en sí
            pst = Conexion.getConexion()
                    .prepareStatement("Update Productos_JB set nombre=?,"
                            + "descripcion=?,"
                            + "valoracion=?,"
                            + "categoria=?,"
                            + "precio=?,"
                            + "imagen=?,"
                            + "fechaalta=?,"
                            + "cantidad=? Where idproducto=?");
            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setInt(3, valoracion);
            pst.setInt(4, idcategoria);
            pst.setDouble(5, precio);
            pst.setString(6, imagen);
            pst.setDate(7, new java.sql.Date(fechaAlta.getTime()));
            pst.setInt(8, cantidad);
            pst.setInt(9, idProducto);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Producto leerProducto(ResultSet rst) {
        Producto p = new Producto();
        try {
            p.setIdProducto(rst.getInt("idProducto"));
            p.setIdUsuario(rst.getInt("idUsuario"));
            p.setNombre(rst.getString("nombre"));
            p.setDescripcion(rst.getString("descripcion"));
            p.setValoracion(rst.getInt("valoracion"));
            p.setCategoria(rst.getInt("categoria"));
            p.setPrecio(rst.getDouble("precio"));
            p.setImagen(rst.getString("imagen"));
            p.setFechaAlta(rst.getDate("fechaAlta"));
            p.setCantidad(rst.getInt("cantidad"));
        } catch (SQLException ex) {
            Logger.getLogger(ProductoBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    
    
}
