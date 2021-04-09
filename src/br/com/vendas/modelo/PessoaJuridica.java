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
@Table (name = "pessoa_juridica")
@PrimaryKeyJoinColumn (name = "id_Cliente")
public class PessoaJuridica extends Cliente {
    
    @Column (nullable = false, unique = true)
    private String cnpj;
    @Column (nullable = false, unique = true)
    private String inscricaoEstadual;

    public PessoaJuridica() {
    }

    public PessoaJuridica(Long id, String nome, String email, String telefone, String cnpj, String inscricaoEstadual) {
        super(id, nome, email, telefone);
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
