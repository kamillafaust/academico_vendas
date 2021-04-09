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
public class PessoaFisicaDaoImplTest {

    private Session sessao;
    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;

    public PessoaFisicaDaoImplTest() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("Salvar");
        pessoaFisica = new PessoaFisica(null,
                UtilGerador.gerarNome(),
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTeleCelular(),
                UtilGerador.gerarNumero(3) + "." + UtilGerador.gerarNumero(3) + "." + UtilGerador.gerarNumero(3) + "-" + UtilGerador.gerarNumero(3),
                UtilGerador.gerarNumero(2) + "." + UtilGerador.gerarNumero(2) + "." + UtilGerador.gerarNumero(3));

        Endereco endereco = new Endereco(null,
                UtilGerador.gerarLogradouro(),
                UtilGerador.gerarCpf(),
                UtilGerador.gerarBairro(),
                UtilGerador.gerarCidade(),
                UtilGerador.gerarUF(),
                "casa",
                UtilGerador.gerarCaracter(5));
        
        endereco.setCliente(pessoaFisica);
        pessoaFisica.setEndereco(endereco);
        
        
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();

        assertNotNull(pessoaFisica.getId());
    }

    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarPessoaFisicaBd();   
        String nome = pessoaFisica.getNome().substring(0, 2);
     
        sessao = HibernateUtil.abrirConexao();
        List<PessoaFisica> pessoas = pessoaFisicaDao.pesquisarPorNome(nome, sessao);
        sessao.close();

        assertNotNull(!pessoas.isEmpty());
    }
    
    @Test
    public void testAlterar() {
        System.out.println("Alterar");
        buscarPessoaFisicaBd();
        pessoaFisica.setNome(UtilGerador.gerarNome());
        pessoaFisica.getEndereco().setCidade(UtilGerador.gerarCidade());
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        PessoaFisica alterado = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        
        assertEquals(pessoaFisica.getNome(), alterado.getNome());
        assertEquals(pessoaFisica.getEndereco().getCidade(), alterado.getEndereco().getCidade());
    }

    @Test
    public void testPesquisaPorCpf() {
        System.out.println("pesquisaPorCpf");
        buscarPessoaFisicaBd();
        String cpf = pessoaFisica.getCpf().substring(0, 5);

        sessao = HibernateUtil.abrirConexao();
        PessoaFisica buscouCpf = pessoaFisicaDao.pesquisaPorCpf(cpf, sessao);
        sessao.close();

        assertNotNull(buscouCpf);
    }

    public PessoaFisica buscarPessoaFisicaBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from PessoaFisica");
        List<PessoaFisica> pessoas = consulta.list();
        sessao.close();
        if (pessoas.isEmpty()) {
            testSalvar();
        } else {
            pessoaFisica = pessoas.get(0);
        }
        return pessoaFisica;
    }
}
