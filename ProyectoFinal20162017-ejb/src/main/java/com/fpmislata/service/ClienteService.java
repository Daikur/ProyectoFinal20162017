package com.fpmislata.service;

import com.fpmislata.domain.Cliente;
import com.fpmislata.repository.ClienteDaoLocal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author David
 */
@Stateless
public class ClienteService implements ClienteServiceLocal {

    @EJB
    private ClienteDaoLocal clienteDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List listClientes() {
        try {
            return clienteDao.listClientes();
        } catch (Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente findClienteById(Cliente cliente) {
        try {
            return clienteDao.findClienteById(cliente);
        } catch (Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addCliente(Cliente cliente) {
        try {
            clienteDao.addCliente(cliente);
        } catch (Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {
        try {
            clienteDao.updateCliente(cliente);
        } catch (Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        try {
            clienteDao.deleteCliente(cliente);
        } catch (Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }

}
