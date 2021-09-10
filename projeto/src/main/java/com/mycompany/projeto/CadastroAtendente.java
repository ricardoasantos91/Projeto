package com.mycompany.projeto;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class CadastroAtendente implements Serializable{
  private List<Atendente> pessoas = new ArrayList<>();

  public void inserir(Atendente pessoa) throws Exception {
    int i = 0;
    try{
        System.out.println("CARALHO2");
      buscarPorCpf(pessoa.getCpf());
    }
    catch(Exception e){
      System.out.println("asaasss");
      pessoas.add(pessoa);

      i = 1;
    }
    if(i == 0){
      throw new Exception("PESSOA JÁ EXISTE!");
    }
    
  }

  public Atendente buscarPorCpf(String cpf)throws Exception{
    for(Atendente pes: pessoas){
      if(pes.getCpf().equals(cpf)){
        return pes;
      }
    }
    throw new Exception("PESSOA NÃO EXISTE!");
  }

  public void remover(Atendente pessoa)throws Exception{
    try{
      Atendente pes = buscarPorCpf(pessoa.getCpf());
      pessoas.remove(pes);
    }
    catch(Exception e){
      throw e;
    }
  }
}