/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.util;

import com.javierbarciela.proyectofin.modelo.Producto;
import java.util.Comparator;

/**
 *
 * @author daw1
 */
public class OrdenarProductosPorNombre implements Comparator<Producto>
{
    public int compare(Producto a, Producto b)
    {
        return a.getNombre().compareToIgnoreCase(b.getNombre());
    }
}
