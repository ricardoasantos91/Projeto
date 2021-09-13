package com.mycompany.projeto.equipamento;

import java.io.Serializable;

public enum TipoEquipamento implements Serializable{
  COMPUTADOR("Computador"),
  NOTEBOOK("Notebook");

  private String nome;

  TipoEquipamento(String nome){
    this.nome = nome;
  }

  public String getNome(){
    return this.nome;
  }
}