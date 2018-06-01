/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.util;

import com.javierbarciela.proyectofin.modelo.Categoria;
import com.javierbarciela.proyectofin.modelo.Producto;
import com.javierbarciela.proyectofin.modelo.Usuario;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author pc
 */
public class Foto {
    public ImageIcon establecerFoto (String img) {
        ImageIcon imIc = new ImageIcon(img);
        Image image = imIc.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
    
    public ImageIcon establecerFotoFactor (String img, double factor) {
        ImageIcon imIc = new ImageIcon(img);
        Image image = imIc.getImage();
        int max = (int) Math.max(imIc.getIconWidth(), imIc.getIconHeight());
        double factorEscala = factor/max;
        int ancho = (int) (imIc.getIconWidth() * factorEscala);
        int alto = (int) (imIc.getIconHeight() * factorEscala);
        image = image.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
    
    public ImageIcon establecerFotoUsuario (Usuario u) {
        ImageIcon imIc = new ImageIcon(u.getFoto());
        Image image = imIc.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
    
    public ImageIcon establecerFotoUsuarioFactor (Usuario u, double factor) {
        ImageIcon imIc = new ImageIcon(u.getFoto());
        Image image = imIc.getImage();
        int max = (int) Math.max(imIc.getIconWidth(), imIc.getIconHeight());
        double factorEscala = factor/max;
        int ancho = (int) (imIc.getIconWidth() * factorEscala);
        int alto = (int) (imIc.getIconHeight() * factorEscala);
        image = image.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
    
    public ImageIcon establecerFotoProducto (Producto p) {
        ImageIcon imIc = new ImageIcon(p.getImagen());
        Image image = imIc.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
    
    public ImageIcon establecerFotoProductoFactor (Producto p, double factor) {
        ImageIcon imIc = new ImageIcon(p.getImagen());
        Image image = imIc.getImage();
        int max = (int) Math.max(imIc.getIconWidth(), imIc.getIconHeight());
        double factorEscala = factor/max;
        int ancho = (int) (imIc.getIconWidth() * factorEscala);
        int alto = (int) (imIc.getIconHeight() * factorEscala);
        image = image.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
    
    public ImageIcon establecerFotoCategoria (Categoria c) {
        ImageIcon imIc = new ImageIcon(c.getImagen());
        Image image = imIc.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
    
    public ImageIcon establecerFotoCategoriaFactor (Categoria c, double factor) {
        ImageIcon imIc = new ImageIcon(c.getImagen());
        Image image = imIc.getImage();
        int max = (int) Math.max(imIc.getIconWidth(), imIc.getIconHeight());
        double factorEscala = factor/max;
        int ancho = (int) (imIc.getIconWidth() * factorEscala);
        int alto = (int) (imIc.getIconHeight() * factorEscala);
        image = image.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        imIc.setImage(image);
        return imIc;
    }
}
