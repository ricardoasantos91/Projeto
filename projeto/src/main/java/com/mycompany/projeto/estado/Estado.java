package com.mycompany.projeto.estado;

import java.io.Serializable;

public enum Estado implements Serializable{
  EM_ANALISE("Em_analise"),
  ABERTO("Aberto"),
  CANCELADO("Cancelado"),
  CONCLUIDO("Concluido");
  
  private String nome;

  Estado(String nome){
    this.nome = nome;
  }

  public String getNome(){
    return this.nome;
  }
}