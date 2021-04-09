/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.PessoaJuridica;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Kamilla Faust
 */
public interface PessoaJuridicaDao extends BaseDao <PessoaJuridica, Long> {
    
    List<PessoaJuridica> pesquisarPorNome (String nome, Session sessao) throws HibernateException;
    
    PessoaJuridica pesquisaPorCnpj (String cnpj, Session sessao) throws HibernateException;
    
}
