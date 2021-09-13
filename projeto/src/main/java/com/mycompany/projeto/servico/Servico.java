package com.mycompany.projeto.servico;

import java.io.Serializable;

public enum Servico implements Serializable{
  CONSERTO("Conserto","Consertar um equipamento");

  private String nome;
  private String descricao;

  Servico(String nome, String descricao){
    this.nome = nome;
    this.descricao = descricao;
  }

  public String getNome(){
    return this.nome;
  }

  public String getDescricao(){
    return this.descricao;
  }
}