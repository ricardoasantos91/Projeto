package com.mycompany.projeto;
import java.io.File;

public class Main {
    

  public static void main(String[] args) {

    File clienteArquivo = new File("ClienteArquivo.bin");
    File atendenteArquivo = new File("AtendenteArquivo.bin");
    File equipamentoArquivo = new File("ChamadoEquipamento.bin");
    File chamadoArquivo = new File("ChamadoArquivo.bin");

    
    CadastroAtendente cadastroAtendente = AtendenteArquivo.lerArquivo(atendenteArquivo);
    CadastroCliente cadastroCliente = ClienteArquivo.lerArquivo(clienteArquivo);
    
    CadastroEquipamento cadastroEquipamento = EquipamentoArquivo.lerArquivo(equipamentoArquivo);
    CadastroChamado cadastroChamado = ChamadoArquivo.lerArquivo(chamadoArquivo);
    
    
    //CadastroPessoa cadastroPessoa = new CadastroPessoa();
    //Pessoa pessoa = cadastroPessoa.buscarPorCpf("123");
    //pessoa.print();
    try{
      Tela.exibirMenu(cadastroCliente, cadastroAtendente ,cadastroChamado,cadastroEquipamento);
    }
    catch(Exception e){
        e.printStackTrace();
    }
  }
}