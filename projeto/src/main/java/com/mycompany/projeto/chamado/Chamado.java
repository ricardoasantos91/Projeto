package com.mycompany.projeto.chamado;
import com.mycompany.projeto.criticidade.Criticidade;
import com.mycompany.projeto.equipamento.Equipamento;
import com.mycompany.projeto.estado.Estado;
import com.mycompany.projeto.servico.Servico;
import com.mycompany.projeto.pessoa.Cliente;
import com.mycompany.projeto.pessoa.Pessoa;
import com.mycompany.projeto.pessoa.Atendente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chamado implements Serializable{
  private int id;
  private String titulo;
  private String data_criacao;
  private List<String> data_modificacao = new ArrayList();
  private Servico servico;
  private Pessoa atendente;
  private Pessoa cliente;
  private Estado estado;
  private Criticidade criticidade;
  private List<Equipamento> equipamentos;
  private List<String> descricao = new ArrayList();

  /*Criar chamado*/
  public Chamado(int id, String titulo, String data_criacao, List<String> data_modificacao, Servico servico, Atendente atendente, Cliente cliente, Estado estado, Criticidade criticidade, List<Equipamento> equipamentos, List<String> descricao){
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
  
  public void adicionarDescricao(String str){
      this.descricao.add(str);
      
  }
  
  public void adicionarDataModificacao(String str){
      this.data_modificacao.add(str);
  }
  
  public List<String> getDescricao(){
      return this.descricao;
  }
  
  public List<String> getDataModificacao(){
      return this.data_modificacao;
  }  
  
  public void setEstado(Estado estado){
      this.estado = estado;
      
  }
  
  public void setCriticidade(Criticidade criticidade){
      this.criticidade = criticidade;
  }
  
  public void printChamado(){
      int i;
      System.out.println("Id do chamado: " + this.id);
      System.out.println("Título: " + this.titulo);
      
      System.out.println("Nome do cliente: " + this.cliente.getNome());
      System.out.println("Nome do atendente: " + this.atendente.getNome());
      System.out.println("Data de criação: " + this.data_criacao);
      System.out.println("Criticidade: " + this.criticidade.getNome());
      System.out.println("Estado: " + this.estado.getNome());
      System.out.println("Serviço: " + this.servico.getNome());
      
      i = 1;
      
      System.out.println("Equipamentos: ");
      for(Equipamento equipamento: this.equipamentos){
          System.out.println("Equipamento " + i + ":");
          equipamento.print();
          i++;
      }
      
      
      i = 1;
      System.out.println("Atualizações: ");
      for(String descricao_atual: this.descricao){
          if (i == 1){
              System.out.println(i + ": Data: " + this.data_criacao);
          }else{
              System.out.println(i + ": Data: " + this.data_modificacao.get(i-2));
          }
          System.out.println(i + ": Descrição: " + descricao_atual);
          i++;
      }
      
      
      
      
  }
  
  
  public Pessoa getCliente(){
    return this.cliente;
  }

  public Pessoa getAtendente(){
    return this.atendente;
  }

  public Estado getEstado(){
    return this.estado;
  }

  public String getDataCriacao(){
      return this.data_criacao;
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