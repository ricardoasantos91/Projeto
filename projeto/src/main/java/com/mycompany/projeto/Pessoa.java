package com.mycompany.projeto;
import java.io.Serializable;
import java.util.List;

public abstract class Pessoa implements Serializable{
  private String nome;
  private String cpf;
  private String data_nasc;
  private String endereco;
  private String email;
  private String telefone;

  private Departamento departamento;

  public Pessoa(String nome, String cpf, String data_nasc, String endereco, String email, String telefone, Departamento departamento){
    this.nome = nome;
    this.cpf = cpf;
    this.data_nasc = data_nasc;
    this.endereco = endereco;
    this.email = email;
    this.telefone = telefone;
    this.departamento = departamento;
  }

  public String getNome(){
    return this.nome;
  }

  public void setNome(String nome){
    this.nome = nome;
  }

  public String getCpf(){
    return this.cpf;
  }

  public void setCpf(String cpf){
    this.cpf = cpf;
  }

  public String getData_nasc(){
    return this.data_nasc;
  }

  public void setData_nasc(String data_nasc){
    this.data_nasc = data_nasc;
  }

  public String getEndereco(){
    return this.endereco;
  }

  public void setEndereco(String endereco){
    this.endereco = endereco;
  }

  public String getEmail(){
    return this.email;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public String getTelefone(){
    return this.telefone;
  }

  public void setTelefone(String telefone){
    this.telefone = telefone;
  }

  public Departamento getDepartamento(){
    return this.departamento;
  }

  public void setDepartamento(Departamento departamento){
    this.departamento = departamento;
  }

  public void print(){
    System.out.println("Nome: " + this.nome + " CPF: " + this.cpf + " Data de Nasc.: " + this.data_nasc + " Endereço: " + this.endereco + " Email: " + this.email + " Telefone: " + this.telefone + " Dep: " + this.departamento.getNome());
  }

  abstract List<Chamado> listarChamados();

  

}