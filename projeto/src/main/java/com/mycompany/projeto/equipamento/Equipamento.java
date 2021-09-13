package com.mycompany.projeto.equipamento;

import com.mycompany.projeto.equipamento.TipoEquipamento;
import java.io.Serializable;
import java.util.List;

public class Equipamento implements Serializable{
  private int id;
  private TipoEquipamento tipo;
  private String data_inst;

  public Equipamento(int id, TipoEquipamento tipo, String data_inst){
    this.id = id;
    this.tipo = tipo;
    this.data_inst = data_inst;
  }

  public TipoEquipamento getTipoEquipamento(){
    return this.tipo;
  }

  public int getId(){
    return this.id;
  }

  public String getData_inst(){
    return this.data_inst;
  }

  public void setData_inst(String data_inst){
    this.data_inst = data_inst;
  }

  public void print(){
    System.out.println("ID: " + this.id + " Tipo: " + this.tipo.getNome() + " Data de Inst.: " + this.data_inst);
  }
  
  
  
}