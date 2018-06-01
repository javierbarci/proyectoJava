/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.modelo;

/**
 *
 * @author daw1
 */
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String email;
    private String foto;
    private String usuario;
    private String hash;
    private String salto;
    
    public Usuario(){
        
    }
    
    public Usuario(int idUsuario, String nombre, String email, String foto, String usuario, String hash, String salto) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.foto = foto;
        this.usuario = usuario;
        this.hash = hash;
        this.salto = salto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalto() {
        return salto;
    }

    public void setSalto(String salto) {
        this.salto = salto;
    }
}
