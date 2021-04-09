/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.PessoaFisica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author Kamilla Faust
 */
public class PessoaFisicaDaoImpl extends BaseDaoImpl <PessoaFisica, Long> implements PessoaFisicaDao, Serializable {

    @Override
    public PessoaFisica pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (PessoaFisica) sessao.get(PessoaFisica.class, id);
    }

    @Override
    public List<PessoaFisica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from PessoaFisica where nome like :nomeHQL");
        consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public PessoaFisica pesquisaPorCpf(String cpf, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("from PessoaFisica pf join fetch pf.pedidos where cpf like :cpfHQL");
        consulta.setParameter("cpfHQL", "%" + cpf + "%");
        return (PessoaFisica) consulta.uniqueResult();
    }
    
}
