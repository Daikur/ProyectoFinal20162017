package com.fpmislata.repository;

import com.fpmislata.domain.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClienteDao implements ClienteDaoLocal {

    @PersistenceContext(unitName = "proyectofinal20162017PU")
    EntityManager em;

    @Override
    public List listClientes() {
        List<Cliente> lista = em.createNamedQuery("Cliente.findAll").getResultList();
        return lista;
    }

    @Override
    public void addCliente(Cliente cliente) {
        em.persist(cliente);
    }

    @Override
    public void deleteCliente(Cliente cliente) {
        cliente = findClienteById(cliente);
        em.remove(cliente);
    }

    @Override
    public Cliente findClienteById(Cliente cliente) {
        return em.find(Cliente.class, cliente.getId_cliente());
    }

    @Override
    public void updateCliente(Cliente cliente) {
        em.merge(cliente);
    }
    
    
}
