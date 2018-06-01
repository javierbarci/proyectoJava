/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javierbarciela.proyectofin.util;

import com.javierbarciela.proyectofin.modelo.Categoria;
import java.util.Comparator;

/**
 *
 * @author daw1
 */
public class OrdenarCategoriasPorNombre implements Comparator<Categoria>
{
    public int compare(Categoria a, Categoria b)
    {
        return a.getNombre().compareToIgnoreCase(b.getNombre());
    }
}