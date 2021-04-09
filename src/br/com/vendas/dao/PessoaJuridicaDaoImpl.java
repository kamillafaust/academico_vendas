/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Kamilla Faust
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl <PessoaJuridica, Long> implements PessoaJuridicaDao, Serializable {
    
    @Override
    public PessoaJuridica pesquisarPorId(Long id, Session sessao) throws HibernateException {
       return (PessoaJuridica) sessao.get(PessoaJuridica.class, id);
    }

    @Override
    public List<PessoaJuridica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from PessoaJuridica where nome like :nomeHQL");
        consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public PessoaJuridica pesquisaPorCnpj(String cnpj, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from PessoaJuridica pj join fetch pj.pedidos where cnpj like :cnpjHQL");
        consulta.setParameter("cnpjHQL", "%" + cnpj + "%");
        return (PessoaJuridica) consulta.uniqueResult();
    }  
    
}
