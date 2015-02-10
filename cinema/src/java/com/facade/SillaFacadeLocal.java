/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.modelo.Silla;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface SillaFacadeLocal {

    void create(Silla silla);

    void edit(Silla silla);

    void remove(Silla silla);

    Silla find(Object id);

    List<Silla> findAll();

    List<Silla> findRange(int[] range);

    int count();
    
}
