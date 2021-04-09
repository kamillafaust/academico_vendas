/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.dao;

import br.com.vendas.modelo.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Kamilla Faust
 */
public class HibernateUtil {

private static final SessionFactory sessionFactory;
    
    static {
        
        try {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Cliente.class);
            cfg.addAnnotatedClass(PessoaFisica.class);
            cfg.addAnnotatedClass(PessoaJuridica.class);
            cfg.addAnnotatedClass(Endereco.class);
            cfg.addAnnotatedClass(Pedido.class);
           
            cfg.configure("/br/com/vendas/dao/hibernate.cfg.xml");
            
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().
                                           applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
       
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session abrirConexao() {
        return sessionFactory.openSession();
    }
}
