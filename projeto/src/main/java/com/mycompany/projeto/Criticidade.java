package com.mycompany.projeto;

import java.io.Serializable;

public enum Criticidade implements Serializable{
  BAIXA("Baixa"),
  MEDIA("Média"),
  ALTA("Alta"),
  URGENTE("Urgente");

  private String nome;

  Criticidade(String nome){
    this.nome = nome;
  }

  public String getNome(){
    return this.nome;
  }
}