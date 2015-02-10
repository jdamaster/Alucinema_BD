/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.modelo.PuntoDeVenta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class PuntoDeVentaFacade extends AbstractFacade<PuntoDeVenta> implements PuntoDeVentaFacadeLocal {
    @PersistenceContext(unitName = "cinemaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PuntoDeVentaFacade() {
        super(PuntoDeVenta.class);
    }
    
}
