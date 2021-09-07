package com.mycompany.projeto;

import java.io.Serializable;

public enum Departamento implements Serializable{
  TI("TI");

  private String nome;

  Departamento(String nome){
    this.nome = nome;
  }

  public String getNome(){
    return this.nome;
  }
}