/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.modelo.Pelicula;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface PeliculaFacadeLocal {

    void create(Pelicula pelicula);

    void edit(Pelicula pelicula);

    void remove(Pelicula pelicula);

    Pelicula find(Object id);

    List<Pelicula> findAll();

    List<Pelicula> findRange(int[] range);

    int count();
    
}
