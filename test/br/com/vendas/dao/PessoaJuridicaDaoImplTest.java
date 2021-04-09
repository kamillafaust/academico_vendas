/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.*;
import java.util.List;
import org.hibernate.*;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilGerador;

/**
 *
 * @author Kamilla Faust
 */
public class PessoaJuridicaDaoImplTest {
    
    private Session sessao;
    private PessoaJuridica pessoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    
    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("Salvar");
        pessoaJuridica = new PessoaJuridica(null,
                UtilGerador.gerarNome(),
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTeleCelular(),
                UtilGerador.gerarNumero(2) + "." + UtilGerador.gerarNumero(3) + "." + UtilGerador.gerarNumero(3) + "/0001 - " + UtilGerador.gerarNumero(2),
                UtilGerador.gerarNumero(4));
        
        Endereco endereco = new Endereco(null,
                UtilGerador.gerarLogradouro(),
                UtilGerador.gerarCpf(),
                UtilGerador.gerarBairro(),
                UtilGerador.gerarCidade(),
                UtilGerador.gerarUF(),
                "Empresa",
                UtilGerador.gerarCaracter(5));
          
        endereco.setCliente(pessoaJuridica);
        pessoaJuridica.setEndereco(endereco);
        
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
        sessao.close();

        assertNotNull(pessoaJuridica.getId());
    }

    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarPessoaJuridicaBd();
        String nome = pessoaJuridica.getNome().substring(0, 3);
        
        sessao = HibernateUtil.abrirConexao();
        List<PessoaJuridica> pessoas = pessoaJuridicaDao.pesquisarPorNome(nome, sessao);
        sessao.close();
        
        assertNotNull(!pessoas.isEmpty());
    }
    
    @Test
    public void testAlterar() {
        System.out.println("Alterar");
        buscarPessoaJuridicaBd();
        pessoaJuridica.setEmail(UtilGerador.gerarEmail());
        pessoaJuridica.getEndereco().setBairro(UtilGerador.gerarBairro());
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        PessoaJuridica alterado = pessoaJuridicaDao.pesquisarPorId(pessoaJuridica.getId(), sessao);
        sessao.close();
        
        assertEquals(pessoaJuridica.getEmail(), alterado.getEmail());
        assertEquals(pessoaJuridica.getEndereco().getBairro(), alterado.getEndereco().getBairro());
    }
    
    @Test
    public void testPesquisaPorCnpj() {
        System.out.println("pesquisaPorCnpj");
        buscarPessoaJuridicaBd();
        String cnpf = pessoaJuridica.getCnpj().substring(0, 2);
        
        sessao = HibernateUtil.abrirConexao();
        PessoaJuridica buscouCnpj = pessoaJuridicaDao.pesquisaPorCnpj(cnpf, sessao);
        sessao.close();

        assertNotNull(buscouCnpj);
    }  
    
     public PessoaJuridica buscarPessoaJuridicaBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from PessoaJuridica");
        List<PessoaJuridica> pessoas = consulta.list();
        sessao.close();
        if (pessoas.isEmpty()) {
            testSalvar();
        } else {
            pessoaJuridica = pessoas.get(0);
        }
        return pessoaJuridica;
     }
}
