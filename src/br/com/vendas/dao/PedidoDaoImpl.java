/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.Pedido;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Kamilla Faust
 */
public class PedidoDaoImpl extends BaseDaoImpl <Pedido, Long> implements PedidoDao, Serializable{

    @Override
    public Pedido pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Pedido) sessao.get(Pedido.class, id);
    }

    @Override
    public Pedido buscarPorNumeroPedido(String pedido, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from Pedido p join fetch p.cliente.endereco where p.numero = :numeroHQL");
        consulta.setParameter("numeroHQL", pedido);
        return (Pedido) consulta.uniqueResult();
    }
    
}
