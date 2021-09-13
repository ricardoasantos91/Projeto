package com.mycompany.projeto.cadastro;
import com.mycompany.projeto.chamado.Chamado;
import com.mycompany.projeto.criticidade.Criticidade;
import com.mycompany.projeto.estado.Estado;
import com.mycompany.projeto.servico.Servico;
import com.mycompany.projeto.pessoa.Cliente;
import com.mycompany.projeto.cadastro.CadastroCliente;
import com.mycompany.projeto.cadastro.CadastroAtendente;
import com.mycompany.projeto.pessoa.Atendente;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class CadastroChamado implements Serializable{
  private List<Chamado> chamados = new ArrayList<>();
  
  
  public void inserir(Chamado chamado){
    chamados.add(chamado);
  }

  public Chamado buscarPorId(int id)throws Exception{
    for(Chamado pes: chamados){
      if(pes.getId() == id){
        return pes;
      }
    }
    throw new Exception("CHAMADO NÃO EXISTE!");
  }

  public void remover(Chamado chamado)throws Exception{
    try{
      Chamado cha = buscarPorId(chamado.getId());
      chamados.remove(cha);
    }
    catch(Exception e){
      throw e;
    }
  }

  public List<Chamado> buscarPorCliente(CadastroCliente cadastro, Cliente cliente)throws Exception{
    List<Chamado> chamadosEncontrados = new ArrayList<Chamado>();

    try{
      cadastro.buscarPorCpf(cliente.getCpf());

      for(Chamado chamado : chamados){
        if(chamado.getCliente().getCpf().equals(cliente.getCpf())){
          chamadosEncontrados.add(chamado);
        }
      }
    }
    catch(Exception e){
      throw e;
    }
    finally{
      return chamadosEncontrados;
    }
  }

  public List<Chamado> buscarPorAtendente(CadastroAtendente cadastro, Atendente atendente)throws Exception{
    List<Chamado> chamadosEncontrados = new ArrayList<Chamado>();

    try{
      cadastro.buscarPorCpf(atendente.getCpf());

      for(Chamado chamado : chamados){
        if(chamado.getAtendente().getCpf().equals(atendente.getCpf())){
          chamadosEncontrados.add(chamado);
        }
      }
    }
    catch(Exception e){
      throw e;
    }
    finally{
      return chamadosEncontrados;
    }
  }

  public List<Chamado> buscarPorEstado(Estado estado)throws Exception{
    List<Chamado> chamadosEncontrados = new ArrayList<Chamado>();

    try{
      for(Chamado chamado : chamados){
        if(chamado.getEstado().equals(estado)){
          chamadosEncontrados.add(chamado);
        }
      }
    }
    catch(Exception e){
      throw e;
    }
    finally{
      return chamadosEncontrados;
    }
  }

  public List<Chamado> buscarPorCriticidade(Criticidade criticidade)throws Exception{
    List<Chamado> chamadosEncontrados = new ArrayList<Chamado>();

    try{
      for(Chamado chamado : chamados){
        if(chamado.getCriticidade().equals(criticidade)){
          chamadosEncontrados.add(chamado);
        }
      }
    }
    catch(Exception e){
      throw e;
    }
    finally{
      return chamadosEncontrados;
    }
  }

  public List<Chamado> buscarPorServico(Servico servico)throws Exception{
    List<Chamado> chamadosEncontrados = new ArrayList<Chamado>();

    try{
      for(Chamado chamado : chamados){
        if(chamado.getServico().equals(servico)){
          chamadosEncontrados.add(chamado);
        }
      }
    }
    catch(Exception e){
      throw e;
    }
    finally{
      return chamadosEncontrados;
    }
  }
  
  
  public int numChamados(){
    if (chamados.size() == 0) return 1;
    return (chamados.get(chamados.size()-1).getId()+1);
  }
}