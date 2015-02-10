/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.modelo.Actores;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ActoresFacadeLocal {

    void create(Actores actores);

    void edit(Actores actores);

    void remove(Actores actores);

    Actores find(Object id);

    List<Actores> findAll();

    List<Actores> findRange(int[] range);

    int count();
    
}
