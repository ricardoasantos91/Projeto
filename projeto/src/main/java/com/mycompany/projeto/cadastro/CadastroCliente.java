package com.mycompany.projeto.cadastro;
import com.mycompany.projeto.pessoa.Cliente;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class CadastroCliente implements Serializable{
  private List<Cliente> pessoas = new ArrayList<>();

  public void inserir(Cliente pessoa) throws Exception {
    int i = 0;
    try{
        
      buscarPorCpf(pessoa.getCpf());
    }
    catch(Exception e){
      
      pessoas.add(pessoa);

      i = 1;
    }
    if(i == 0){
      throw new Exception("PESSOA JÁ EXISTE!");
    }
    
  }

  public Cliente buscarPorCpf(String cpf)throws Exception{
    for(Cliente pes: pessoas){
      if(pes.getCpf().equals(cpf)){
        return pes;
      }
    }
    throw new Exception("PESSOA NÃO EXISTE!");
  }

  public void remover(Cliente pessoa)throws Exception{
    try{
      Cliente pes = buscarPorCpf(pessoa.getCpf());
      pessoas.remove(pes);
    }
    catch(Exception e){
      throw e;
    }
  }
}