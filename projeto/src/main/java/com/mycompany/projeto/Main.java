package com.mycompany.projeto;
import java.io.File;

public class Main {
    

  public static void main(String[] args) {

    File pessoaArquivo = new File("PessoaArquivo.bin");
    File equipamentoArquivo = new File("EquipamentoArquivo.bin");
    File chamadoArquivo = new File("ChamadoArquivo.bin");

    
    CadastroPessoa cadastroPessoa = PessoaArquivo.lerArquivo(pessoaArquivo);
    CadastroEquipamento cadastroEquipamento = EquipamentoArquivo.lerArquivo(equipamentoArquivo);
    CadastroChamado cadastroChamado = ChamadoArquivo.lerArquivo(chamadoArquivo);
    
    
    //CadastroPessoa cadastroPessoa = new CadastroPessoa();
    //Pessoa pessoa = cadastroPessoa.buscarPorCpf("123");
    //pessoa.print();
    try{
      Tela.exibirMenu(cadastroPessoa,cadastroChamado,cadastroEquipamento);
    }
    catch(Exception e){
        e.printStackTrace();
    }
  }
}