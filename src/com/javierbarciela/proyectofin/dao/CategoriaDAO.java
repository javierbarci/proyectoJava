/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;
import com.javierbarciela.proyectofin.modelo.Categoria;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author daw1
 */
public interface CategoriaDAO {
    List<Categoria> getCategorias();
    Categoria getCategoria(int idCategoria);
    void nuevaCategoria(Categoria c);
    void eliminarCategoria(Categoria c);
    void modificarCategoria(Categoria c);
    Categoria leerCategoria(ResultSet rst);
}
