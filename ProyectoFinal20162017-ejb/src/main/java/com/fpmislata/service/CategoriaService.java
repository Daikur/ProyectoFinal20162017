/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Categoria;
import com.fpmislata.repository.CategoriaDaoLocal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author lodiade
 */
@Stateless
public class CategoriaService implements CategoriaServiceLocal {

    @EJB
    private CategoriaDaoLocal categoriaDao;
    
    @Resource
    private SessionContext contexto;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List listCategorias() {
        try{
            return categoriaDao.listCategorias();
        }catch(Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Categoria findCategoriaById(Categoria categoria) {
        try{
            return categoriaDao.findCategoriaById(categoria);
        }catch(Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCategoria(Categoria categoria) {
        try{
            categoriaDao.addCategoria(categoria);
        }catch(Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
        }        
    }  

    @Override
    public void updateCategoria(Categoria categoria) {
        try{
            categoriaDao.updateCategoria(categoria);
        }catch(Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
        }         
    }

    @Override
    public void deleteCategoria(Categoria categoria) {
        try{
            categoriaDao.deleteCategoria(categoria);
        }catch(Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
        }         
    }
    
    
    
    
}
