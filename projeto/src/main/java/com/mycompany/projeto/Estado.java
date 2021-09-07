package com.mycompany.projeto;

import java.io.Serializable;

public enum Estado implements Serializable{
  EM_ANALISE("Em análise"),
  ABERTO("Aberto"),
  CANCELADO("Cancelado"),
  CONCLUIDO("Concluído");
  
  private String nome;

  Estado(String nome){
    this.nome = nome;
  }

  public String getNome(){
    return this.nome;
  }
}