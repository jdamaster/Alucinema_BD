/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.modelo.Sala;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface SalaFacadeLocal {

    void create(Sala sala);

    void edit(Sala sala);

    void remove(Sala sala);

    Sala find(Object id);

    List<Sala> findAll();

    List<Sala> findRange(int[] range);

    int count();
    
}
