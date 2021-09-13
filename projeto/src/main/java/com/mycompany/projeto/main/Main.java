package com.mycompany.projeto.main;
import com.mycompany.projeto.tela.Tela;
import com.mycompany.projeto.arquivo.EquipamentoArquivo;
import com.mycompany.projeto.arquivo.ChamadoArquivo;
import com.mycompany.projeto.cadastro.CadastroEquipamento;
import com.mycompany.projeto.cadastro.CadastroChamado;
import com.mycompany.projeto.cadastro.CadastroCliente;
import com.mycompany.projeto.arquivo.ClienteArquivo;
import com.mycompany.projeto.cadastro.CadastroAtendente;
import com.mycompany.projeto.arquivo.AtendenteArquivo;
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