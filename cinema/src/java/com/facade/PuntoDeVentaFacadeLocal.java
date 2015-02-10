/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.modelo.PuntoDeVenta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface PuntoDeVentaFacadeLocal {

    void create(PuntoDeVenta puntoDeVenta);

    void edit(PuntoDeVenta puntoDeVenta);

    void remove(PuntoDeVenta puntoDeVenta);

    PuntoDeVenta find(Object id);

    List<PuntoDeVenta> findAll();

    List<PuntoDeVenta> findRange(int[] range);

    int count();
    
}
