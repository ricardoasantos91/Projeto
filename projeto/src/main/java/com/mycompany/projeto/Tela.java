package com.mycompany.projeto;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Tela{
  
  public static void exibirMenu(CadastroPessoa cadastroPessoa, CadastroChamado cadastroChamado, CadastroEquipamento cadastroEquipamento){
      Scanner scan = new Scanner(System.in);

      scan.useDelimiter("\n");

    System.out.println("Escolha uma opção:\n");
    System.out.println("1 - Abrir um novo chamado");
    System.out.println("2 - Tratar um chamado");
    System.out.println("3 - Buscar chamado");
    System.out.println("4 - Administração");
    System.out.println("5 - Sair");

    int opcao = scan.nextInt();

    switch(opcao){
      case 1:
         System.out.println("Escolha uma opção:\n");
      case 2:

      case 3:

      case 4:
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);
      case 5:

        System.exit(0);
    }
  }

  public static void exibirMenuAdministracao(CadastroPessoa cadastroPessoa, CadastroChamado cadastroChamado, CadastroEquipamento cadastroEquipamento){
    Scanner scan = new Scanner(System.in);
    scan.useDelimiter("\n");
      System.out.println("Escolha uma opção:");
    System.out.println("1 - Cadastrar cliente");
    System.out.println("2 - Cadastrar atendente");
    System.out.println("3 - Cadastrar equipamento");
    System.out.println("4 - Buscar pessoa");
    System.out.println("5 - Buscar equipamento");
    System.out.println("6 - Remover pessoa");
    System.out.println("7 - Remover equipamento");
    System.out.println("8 - Sair");

    int opcao = scan.nextInt();

    switch(opcao){
      case 1:
        scan.nextLine();

        System.out.println("Insira o nome:");
        String nome = scan.nextLine();

        System.out.println("Insira o CPF:");
        String cpf = scan.nextLine();

        System.out.println("Insira a data de nascimento:");
        String data = scan.nextLine();

        System.out.println("Insira o endereço:");
        String endereco = scan.nextLine();

        System.out.println("Insira o email:");
        String email = scan.nextLine();

        System.out.println("Insira o telefone:");
        String telefone = scan.nextLine();

        System.out.println("Insira o departamento:");
        String dep = scan.nextLine();

        Departamento departamento = Departamento.valueOf(dep);

        Cliente cliente = new Cliente(nome, cpf, data, endereco, email, telefone, departamento);

        try{
          cadastroPessoa.inserir(cliente);
        }
        catch(Exception e){
            System.out.println("erro");
        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);
      case 2:
        System.out.println("Insira o nome:\n");
        String nome2 = scan.nextLine();

        System.out.println("Insira o CPF:\n");
        String cpf2 = scan.nextLine();

        System.out.println("Insira a data de nascimento:\n");
        String data2 = scan.nextLine();

        System.out.println("Insira o endereço:\n");
        String endereco2 = scan.nextLine();

        System.out.println("Insira o email:\n");
        String email2 = scan.nextLine();

        System.out.println("Insira o telefone:\n");
        String telefone2 = scan.nextLine();

        System.out.println("Insira o departamento:\n");
        String dep2 = scan.nextLine();

        Departamento departamento2 = Departamento.valueOf(dep2);

        Atendente atendente = new Atendente(nome2, cpf2, data2, endereco2, email2, telefone2, departamento2);


        try{
          cadastroPessoa.inserir(atendente);
        }
        catch(Exception e){

        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);
      case 3:
        System.out.println("Insira o tipo de equipamento:");
        String tipo = scan.nextLine();

        System.out.println("Insira a data de instalação:");
        String data3 = scan.nextLine();

        Equipamento equipamento = new Equipamento(CadastroEquipamento.numEquipamentos(), TipoEquipamento.valueOf(tipo), data3);

        try{
          CadastroEquipamento.inserir(equipamento);
        }
        catch(Exception e){

        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);

      case 4:
        scan.nextLine();

        System.out.println("Insira o CPF da pessoa que deseja buscar:");
        String cpfB = scan.nextLine();

        try{
          Pessoa pes = cadastroPessoa.buscarPorCpf(cpfB);
          pes.print();
        }
        catch(Exception e){
          
        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);

      case 5:
        System.out.println("Insira o tipo de equipamento que deseja buscar:\n");
        String tipoB = scan.nextLine();

        try{
          TipoEquipamento tipoEquipamento = TipoEquipamento.valueOf(tipoB);

          List<Equipamento> equipamentos = CadastroEquipamento.buscarPorTipo(tipoEquipamento);

          for(Equipamento equip : equipamentos){
            equip.print();
          }
        }
        catch(Exception e){
          
        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);

      case 6:
        System.out.println("Insira o CPF da pessoa que deseja remover:\n");
        String cpfR = scan.nextLine();

        try{
          Pessoa pessoaR = cadastroPessoa.buscarPorCpf(cpfR);

          cadastroPessoa.remover(pessoaR);
        }
        catch(Exception e){

        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);

      case 7:
        System.out.println("Insira o Id do equipamento que deseja remover:\n");
        int idR = scan.nextInt();

        try{
          Equipamento equipamentoR = CadastroEquipamento.buscarPorId(idR);

          CadastroEquipamento.remover(equipamentoR);
        }
        catch(Exception e){
          
        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);

      case 8:
        PessoaArquivo.escreverArquivo(cadastroPessoa);
        ChamadoArquivo.escreverArquivo(cadastroChamado);
        EquipamentoArquivo.escreverArquivo(cadastroEquipamento);
        System.exit(0);
    }
  }

  public static void exibirMenuBuscaChamado(){
    
  }
}