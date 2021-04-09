/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.*;
import java.util.*;
import org.hibernate.*;
import org.junit.Test;
import static org.junit.Assert.*;
import util.UtilGerador;

/**
 *
 * @author Kamilla Faust
 */
public class PedidoDaoImplTest {

    private Session sessao;
    private Pedido pedido;
    private PedidoDao pedidoDao;
    private Cliente cliente;

    public PedidoDaoImplTest() {
        pedidoDao = new PedidoDaoImpl();
    }

    @Test
    public void testSalvarPedidoPessoaFisica() {
        System.out.println("SalvarEmPF");
        
        PessoaFisicaDaoImplTest testPF = new PessoaFisicaDaoImplTest();
        PessoaFisica pessoaFisica = testPF.buscarPessoaFisicaBd();
        
        pedido = new Pedido(null,
                UtilGerador.gerarNumero(4),
                10.0,
                new Date());
        pedido.setCliente(pessoaFisica);

        sessao = HibernateUtil.abrirConexao();
        pedidoDao.salvarOuAlterar(pedido, sessao);
        sessao.close();

        assertNotNull(pedido.getId());
    }
    
    @Test
    public void testSalvarPedidoPessoaJuridica() {
        System.out.println("SalvarEmPJ");
        
        PessoaJuridicaDaoImplTest testPJ = new PessoaJuridicaDaoImplTest();
        PessoaJuridica pessoaJuridica = testPJ.buscarPessoaJuridicaBd();
        
        pedido = new Pedido(null,
                UtilGerador.gerarNumero(4),
                10.0,
                new Date());
        pedido.setCliente(pessoaJuridica);

        sessao = HibernateUtil.abrirConexao();
        pedidoDao.salvarOuAlterar(pedido, sessao);
        sessao.close();

        assertNotNull(pedido.getId());
    }

    @Test
    public void testBuscarPorNumeroPedido() {
        System.out.println("buscarPorNumeroPedido");
        buscarPedidoBd();
        sessao = HibernateUtil.abrirConexao();
        Pedido pesquisado = pedidoDao.buscarPorNumeroPedido(pedido.getNumero(), sessao);
        sessao.close();

        assertNotNull(pesquisado);
        assertNotNull(pesquisado.getCliente().getEndereco().getId());
    }
    
    @Test
    public void testAlterar(){
        System.out.println("Alterar");
        buscarPedidoBd();
        pedido.setNumero(UtilGerador.gerarNumero(3));
        sessao = HibernateUtil.abrirConexao();
        pedidoDao.salvarOuAlterar(pedido, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Pedido alterado = pedidoDao.pesquisarPorId(pedido.getId(), sessao);
        sessao.close();
        
        assertEquals(pedido.getNumero(), alterado.getNumero());
    }

    private Pedido buscarPedidoBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Pedido");
        List<Pedido> pedidos = consulta.list();
        if (pedidos.isEmpty()) {
            testSalvarPedidoPessoaFisica();
        } else {
            pedido = pedidos.get(0);
        }
        return pedido;
    }
}
