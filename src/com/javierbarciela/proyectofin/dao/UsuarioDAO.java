/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;
import com.javierbarciela.proyectofin.modelo.Usuario;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author daw1
 */
public interface UsuarioDAO {
    List<Usuario> getUsuarios();
    Usuario getUsuarioNick(String nick, String password);
    Usuario getUsuarioEmail(String email, String password);
    Usuario getUsuario(int idUsuario);
    void registrarUsuario(String nombrePila, String email, String nick, String pwd);
    /*void nuevoUsuario(Usuario c);*/
    void eliminarUsuario(Usuario c);
    void modificarUsuario(Usuario c);
    Usuario leerUsuario(ResultSet rst);
}
