package com.mycompany.projeto.criticidade;

import java.io.Serializable;

public enum Criticidade implements Serializable{
  BAIXA("Baixa"),
  MEDIA("Media"),
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