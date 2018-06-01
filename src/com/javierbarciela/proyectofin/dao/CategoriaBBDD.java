/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;

import com.javierbarciela.proyectofin.modelo.Categoria;
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
public class CategoriaBBDD implements CategoriaDAO{

    @Override
    public List<Categoria> getCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        ResultSet rst;
        try {
            rst = Conexion.getConexion()
                    .createStatement()
                    .executeQuery("Select * From Categorias_JB");
            while(rst.next()){
                Categoria c = leerCategoria(rst);
                categorias.add(c);                
            }
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }

    @Override
    public Categoria getCategoria(int idCategoria) {
        Categoria c = new Categoria();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Select * from Categorias_JB WHERE idCategoria=?");
            pst.setInt(1, idCategoria);
            rst = pst.executeQuery();
            if(rst.next()){
                c = leerCategoria(rst);
            }
            pst.close();
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public void nuevaCategoria(Categoria c) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion().
                    prepareStatement("Insert Into Categorias_JB Values ?,?,?,?");
            pst.setInt(1, c.getIdCategoria());
            pst.setString(2, c.getNombre());
            pst.setString(3, c.getDescripcion());
            pst.setString(4, c.getImagen());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void eliminarCategoria(Categoria c) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Delete From Categorias_JB Where "
                            + "idCategoria=?");
            pst.setInt(1, c.getIdCategoria());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificarCategoria(Categoria c) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Update Categorias_JB Set "
                            + "nombre=?, descripcion=?, imagen=? "
                            + "WHERE idCategoria=?");
            pst.setString(1, c.getNombre());
            pst.setString(2, c.getDescripcion());
            pst.setString(3, c.getImagen());
            pst.setInt(4, c.getIdCategoria());
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Categoria leerCategoria(ResultSet rst) {
        Categoria c = new Categoria();
        try {
            c.setIdCategoria(rst.getInt("idCategoria"));
            c.setNombre(rst.getString("nombre"));
            c.setDescripcion(rst.getString("descripcion"));
            c.setImagen(rst.getString("imagen"));
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    
    
}
