package com.mycompany.projeto.cadastro;
import com.mycompany.projeto.equipamento.Equipamento;
import com.mycompany.projeto.equipamento.TipoEquipamento;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class CadastroEquipamento implements Serializable{
  private List<Equipamento> equipamentos = new ArrayList<>();

  public void inserir(Equipamento equipamento) throws Exception {
    int i = 0;
    try{
      buscarPorId(equipamento.getId());
    }
    catch(Exception e){
      //System.out.println(e.getMessage());
      equipamentos.add(equipamento);

      i = 1;
    }
    if(i == 0){
      throw new Exception("EQUIPAMENTO JÁ EXISTE!");
    }
  }

  public Equipamento buscarPorId(int id)throws Exception{
    for(Equipamento eq : equipamentos){
      //eq.print();
        if(eq.getId() == id){
        
        return eq;
      }
    }
    throw new Exception("EQUIPAMENTO NÃO EXISTE!");
  }

  public int numEquipamentos(){
    return equipamentos.size();
  }
  
  public int getNextId(){
      if(equipamentos.size() == 0) return 1;
      return equipamentos.get(equipamentos.size()-1).getId()+1;
  }
  public List<Equipamento> buscarPorTipo(TipoEquipamento tipo)throws Exception{
    List<Equipamento> equipamentosEncontrados = new ArrayList<Equipamento>();

    try{
      for(Equipamento equipamento : equipamentos){
        if(equipamento.getTipoEquipamento().equals(tipo)){
          equipamentosEncontrados.add(equipamento);
        }
      }
    }
    catch(Exception e){
      throw e;
    }
    finally{
      return equipamentosEncontrados;
    }
  }

  public void remover(Equipamento equipamento)throws Exception{
    try{
      Equipamento eq = buscarPorId(equipamento.getId());
      equipamentos.remove(eq);
    }
    catch(Exception e){
      throw e;
    }
  }

}
