/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.archivos.facade;

import edu.archivos.entity.Bodega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JoseLuis
 */
@Stateless
public class BodegaFacade extends AbstractFacade<Bodega> {

    @PersistenceContext(unitName = "archivosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BodegaFacade() {
        super(Bodega.class);
    }
    
}
