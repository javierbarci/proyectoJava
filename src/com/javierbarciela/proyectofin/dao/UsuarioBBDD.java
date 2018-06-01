/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.dao;

import com.javierbarciela.proyectofin.modelo.Usuario;
import com.javierbarciela.proyectofin.util.Conexion;
import com.javierbarciela.proyectofin.util.RandomString;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import util.SHA;

/**
 *
 * @author daw1
 */
public class UsuarioBBDD implements UsuarioDAO{
    
    private SHA sha = new SHA();
    private RandomString randomString = new RandomString();

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        ResultSet rst;
        try {
            rst = Conexion.getConexion()
                    .createStatement()
                    .executeQuery("Select * From Usuarios_JB");
            while(rst.next()){
                Usuario u = leerUsuario(rst);
                usuarios.add(u);
            }
            rst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;    
    }

    @Override
    public Usuario getUsuarioNick(String nick, String pwd) {
        Usuario u = new Usuario();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement(
                        "Select * From Usuarios_JB " +
                        "where usuario=?");
            pst.setString(1, nick);
            rst = pst.executeQuery();
            if (rst.next()){
                u = leerUsuario(rst);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        String hash = sha.getSHA("" + sha.getSHA(pwd) + u.getSalto());
        if (sha.getSHA("" + sha.getSHA(pwd) + u.getSalto()).equals(u.getHash())){
                return u;
            } else {
                return null;
            }
    }
    
    @Override
    public Usuario getUsuarioEmail(String email, String pwd) {
        Usuario u = new Usuario();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement(
                        "Select * From Usuarios_JB " +
                        "where email=?");
            pst.setString(1, email);
            rst = pst.executeQuery();
            if (rst.next()){
                u = leerUsuario(rst);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (sha.getSHA("" + sha.getSHA(pwd) + u.getSalto()).equals(u.getHash())){
                return u;
            } else {
                return null;
            }
    }
    
    @Override
    public Usuario getUsuario(int idUsuario) {
        Usuario u = new Usuario();
        PreparedStatement pst;
        ResultSet rst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement(
                        "Select * From Usuarios_JB " +
                        "where idUsuario=?");
            pst.setInt(1, idUsuario);
            rst = pst.executeQuery();
            if (rst.next()){
                u = leerUsuario(rst);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    
    
    /*
    @Override
    public void nuevoUsuario(Usuario u) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Insert into Usuarios_JB values(?,?,?,?,?,?,?)");
            pst.setInt(1, u.getIdUsuario());
            pst.setString(2, u.getNombre());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getFoto());
            pst.setString(5, u.getUsuario());
            pst.setString(6, u.getHash());
            pst.setString(7, u.getSalto());
            
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
    @Override
    public void registrarUsuario(String nombrePila, String email, String nick, String pwd){
        PreparedStatement pst;
        ResultSet rst;
        List<Usuario> usuarios = new ArrayList<>();
        int lastId = 0;
        try { // obtenemos el último id para saber cuál asignaremos al nuevo usuario
            pst = Conexion.getConexion().
                    prepareStatement("Select * From Usuarios_JB");
            rst = pst.executeQuery();
            while(rst.next()){
                Usuario u = leerUsuario(rst);
                usuarios.add(u);
            }
            lastId = usuarios.get(usuarios.size()-1).getIdUsuario();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pst2;
        int idUsuario = lastId + 1;
        String salto = randomString.getSaltString();
        String hash = sha.getSHA("" + sha.getSHA(pwd) + salto);
        Usuario u = new Usuario(idUsuario, nombrePila, email, null, nick, hash, salto);
        try { // Insertamos el usuario ahora que tenemos todos los datos (la foto es opcional)
            pst2 = Conexion.getConexion()
                    .prepareStatement("Insert Into Usuarios_JB values (?,?,?,?,?,?,?)");
            pst2.setInt(1, lastId + 1);
            pst2.setString(2, nombrePila);
            pst2.setString(3, email);
            pst2.setString(4, null);
            pst2.setString(5, nick);
            pst2.setString(6, hash);
            pst2.setString(7, salto);
            pst2.executeUpdate();
            pst2.close();
            JOptionPane.showConfirmDialog(null, "Se ha registrado en la BBDD");
        } catch (SQLException ex) { 
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarUsuario(Usuario u) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion()
                    .prepareStatement("Delete from Usuarios_JB WHERE idUsuario=?");
            pst.setInt(1, u.getIdUsuario());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modificarUsuario(Usuario u) {
        PreparedStatement pst;
        try {
            pst = Conexion.getConexion().
                    prepareStatement("Update Usuarios_JB SET "
                            + "nombre=?,email=?,foto=?,usuario=?,"
                            + "has=?,salto=?"
                            + "WHERE idUsuario=?");
            pst.setString(1, u.getNombre());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getFoto());
            pst.setString(4, u.getUsuario());
            pst.setString(5, u.getHash());
            pst.setString(6, u.getSalto());
            pst.setInt(7, u.getIdUsuario());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Usuario leerUsuario(ResultSet rst) {
        Usuario u = new Usuario();
        try {
            u.setIdUsuario(rst.getInt("idUsuario"));
            u.setNombre(rst.getString("nombre"));
            u.setEmail(rst.getString("email"));
            u.setFoto(rst.getString("foto"));
            u.setUsuario(rst.getString("usuario"));
            u.setHash(rst.getString("hash"));
            u.setSalto(rst.getString("salto"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
}
