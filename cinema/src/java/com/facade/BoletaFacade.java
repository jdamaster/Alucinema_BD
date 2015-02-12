/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facade;

import com.modelo.Boleta;
import com.modelo.Cliente;
import com.modelo.ClientePK;
import com.modelo.Funcion;
import com.modelo.FuncionPK;
import com.modelo.Pelicula;
import com.modelo.Silla;
import com.modelo.SillaPK;
import com.modelo.TablaPuntos;
import java.sql.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class BoletaFacade extends AbstractFacade<Boleta> implements BoletaFacadeLocal {
    ApplicationContext context = new ClassPathXmlApplicationContext("/spring.xml");
    @PersistenceContext(unitName = "cinemaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoletaFacade() {
        super(Boleta.class);
    }
    
    @Override
    public void create(Boleta boleta){
        FuncionPK clavePrimaria = new FuncionPK(boleta.getIdSede(),boleta.getIdPelicula(),boleta.getIdSala(), Date.valueOf(boleta.getHora()));
        FuncionFacade interfazFuncion =  (FuncionFacade)context.getBean("funcion");
        SillaFacade interfazSilla = (SillaFacade)context.getBean("silla");
        ClienteFacade interfazCliente = (ClienteFacade)context.getBean("cliente");
        Funcion f;
        Silla s;
        Cliente c;
        c = interfazCliente.find(new ClientePK(boleta.getTipoDocumento(), boleta.getDocumento()));
        f = interfazFuncion.find(clavePrimaria);
        s = interfazSilla.find(new SillaPK(boleta.getIdSilla(), boleta.getIdSala(), boleta.getIdSede()));
        String tipoBoleta = f.getTipo() + f.getCalidad()+ s.getTipo();
        TablaPuntos t = new TablaPuntos();
        c.setPuntos(t.retornarPuntos(tipoBoleta));
        interfazCliente.edit(c);
        em.persist(boleta);
    }

    
}
