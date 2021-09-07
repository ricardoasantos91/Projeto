package com.mycompany.projeto;
import java.io.Serializable;
import java.util.List;

public class Chamado implements Serializable{
  private int id;
  private String titulo;
  private String data_criacao;
  private String data_modificacao;
  private Servico servico;
  private Atendente atendente;
  private Cliente cliente;
  private Estado estado;
  private Criticidade criticidade;
  private List<Equipamento> equipamentos;
  private String descricao;

  /*Criar chamado*/
  public Chamado(int id, String titulo, String data_criacao, String data_modificacao, Servico servico, Atendente atendente, Cliente cliente, Estado estado, Criticidade criticidade, List<Equipamento> equipamentos, String descricao){
    this.id = id;
    this.titulo = titulo;
    this.data_criacao = data_criacao;
    this.data_modificacao = data_modificacao;
    this.servico = servico;
    this.atendente = atendente;
    this.cliente = cliente;
    this.estado = estado;
    this.criticidade = criticidade;
    this.equipamentos = equipamentos;
    this.descricao = descricao;
  }

  public int getId(){
    return this.id;
  }

  public Cliente getCliente(){
    return this.cliente;
  }

  public Atendente getAtendente(){
    return this.atendente;
  }

  public Estado getEstado(){
    return this.estado;
  }

  public Criticidade getCriticidade(){
    return this.criticidade;
  }

  public Servico getServico(){
    return this.servico;
  }

  public void fechar(){

  }

  public void editar(){

  }

  public String verificar(){
    return null;
  }
  
}