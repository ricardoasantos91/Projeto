package com.mycompany.projeto.pessoa;

import com.mycompany.projeto.chamado.Chamado;
import com.mycompany.projeto.departamento.Departamento;
import com.mycompany.projeto.pessoa.Pessoa;
import com.mycompany.projeto.pessoa.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Atendente extends Pessoa implements Serializable{
  public Atendente(String nome, String cpf, String data_nasc, String endereco, String email, String telefone, Departamento departamento){
    super(nome, cpf, data_nasc, endereco, email, telefone, departamento);
  }
  
  public Atendente(){
      super();
  }
  
  
  public List<Chamado> listarChamados(){
    return null;
  }
}