/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.Pedido;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Kamilla Faust
 */
public interface PedidoDao extends BaseDao <Pedido, Long> {
    
    Pedido buscarPorNumeroPedido (String pedido, Session sessao) throws HibernateException;
    
}
