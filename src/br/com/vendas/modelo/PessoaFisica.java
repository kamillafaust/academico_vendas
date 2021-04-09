/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.vendas.modelo;

import javax.persistence.*;

/**
 *
 * @author Kamilla Faust
 */

@Entity
@Table (name = "pessoa_fisica")
@PrimaryKeyJoinColumn (name = "id_Cliente")
public class PessoaFisica extends Cliente {
    
    @Column (nullable = false, unique = true)
    private String cpf;
    @Column (nullable = false, unique = true)
    private String rg;

    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String nome, String email, String telefone, String cpf, String rg) {
        super(id, nome, email, telefone);
        this.cpf = cpf;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
     
}
