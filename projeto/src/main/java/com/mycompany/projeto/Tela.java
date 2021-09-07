package com.mycompany.projeto;
import java.text.DateFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

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
    int n;
    Atendente atendente = new Atendente();
    Cliente cliente = new Cliente();
    switch(opcao){
      
      case 1:
        scan.nextLine();
        System.out.println("Insira o CPF do cliente que deseja buscar:");
        String cpfB = scan.nextLine();
        
        try{
            Pessoa pessoa = cadastroPessoa.buscarPorCpf(cpfB);
        }catch(Exception e){
            System.out.println("Cliente não existe e deve ser cadastrado!");
            cliente = receberCliente();
            
            try{
                cadastroPessoa.inserir(cliente);
            }catch(Exception ex2){
                System.out.println("Alerta: pessoa já existe");
            }
        }
        
        System.out.println("Insira o CPF do atendente que deseja buscar:");
        String cpfA = scan.nextLine();
        
        try{
            Pessoa pessoa = cadastroPessoa.buscarPorCpf(cpfA);
        }catch(Exception e){
            System.out.println("Atendente não existe e deve ser cadastrado!");
            atendente = receberAtendente();
            try{
                cadastroPessoa.inserir(atendente);
            }catch(Exception ex3){
                System.out.println("Alerta: pessoa já existe");
            }
        }
        
        List<Equipamento> equipamentos = receberEquipamentosChamado(cadastroEquipamento);
        
        Servico servico = receberServico();
        Criticidade criticidade = receberCriticidade();
        Estado estado = receberEstado();
        String data_criacao = dataAtual();
        String data_modificacao = dataAtual();
        int id = cadastroChamado.numChamados();
        String titulo = receberTitulo();
        
        System.out.println("Digite a descrição: ");
        String descricao = scan.nextLine();
        
        Chamado chamado = new Chamado(id, titulo, data_criacao, data_modificacao, servico, atendente, cliente, estado, criticidade, equipamentos, descricao);
        
        try{
            cadastroChamado.inserir(chamado);
        }catch(Exception ex){
            System.out.println("Sei nem o que tá acontecendo.");
        }
        
        
        
        
        
        
      case 2:

      case 3:

      case 4:
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);
      case 5:
        PessoaArquivo.escreverArquivo(cadastroPessoa);
        ChamadoArquivo.escreverArquivo(cadastroChamado);
        EquipamentoArquivo.escreverArquivo(cadastroEquipamento);
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
    System.out.println("8 - Voltar");
    System.out.println("9 - Sair");
    

    int opcao = scan.nextInt();

    switch(opcao){
      case 1:
        
        Cliente cliente = receberCliente();
        
        try{
          cadastroPessoa.inserir(cliente);
        }
        catch(Exception e){
            System.out.println("erro");
        }
        
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);
      case 2:

        Atendente atendente = receberAtendente();
        try{
          cadastroPessoa.inserir(atendente);
        }
        catch(Exception e){

        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);
      case 3:
        Equipamento equipamento = receberEquipamento(cadastroEquipamento);
        try{
          cadastroEquipamento.inserir(equipamento);
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

          List<Equipamento> equipamentos = cadastroEquipamento.buscarPorTipo(tipoEquipamento);

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
          Equipamento equipamentoR = cadastroEquipamento.buscarPorId(idR);

          cadastroEquipamento.remover(equipamentoR);
        }
        catch(Exception e){
          
        }
        exibirMenuAdministracao(cadastroPessoa, cadastroChamado, cadastroEquipamento);

      case 8:
        exibirMenu(cadastroPessoa, cadastroChamado, cadastroEquipamento);
        
      case 9:
        PessoaArquivo.escreverArquivo(cadastroPessoa);
        ChamadoArquivo.escreverArquivo(cadastroChamado);
        EquipamentoArquivo.escreverArquivo(cadastroEquipamento);
        System.exit(0);
    }
  }
  
  public static Cliente receberCliente(){
    Scanner scan = new Scanner(System.in);
    scan.useDelimiter("\n");
    
    //scan.nextLine();

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
    
    return cliente;
  }
  
  public static Atendente receberAtendente(){
    Scanner scan = new Scanner(System.in);
    scan.useDelimiter("\n");
    System.out.println("Insira o nome:");
    String nome2 = scan.nextLine();

    System.out.println("Insira o CPF:");
    String cpf2 = scan.nextLine();

    System.out.println("Insira a data de nascimento:");
    String data2 = scan.nextLine();

    System.out.println("Insira o endereço:");
    String endereco2 = scan.nextLine();

    System.out.println("Insira o email:\n");
    String email2 = scan.nextLine();

    System.out.println("Insira o telefone:\n");
    String telefone2 = scan.nextLine();

    System.out.println("Insira o departamento:\n");
    String dep2 = scan.nextLine();

    Departamento departamento2 = Departamento.valueOf(dep2);

    Atendente atendente = new Atendente(nome2, cpf2, data2, endereco2, email2, telefone2, departamento2);

    return atendente;
      
  }
  
  public static Equipamento receberEquipamento(CadastroEquipamento cadastroEquipamento){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      System.out.println("Insira o tipo de equipamento:");
        String tipo = scan.nextLine();

        System.out.println("Insira a data de instalação:");
        String data3 = scan.nextLine();

        Equipamento equipamento = new Equipamento(cadastroEquipamento.numEquipamentos(), TipoEquipamento.valueOf(tipo), data3);
        return equipamento;
      
  }
  
  
  public static Servico receberServico(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      
      Servico[] servicos = Servico.values();
      
      int i = 0;  
      for(Servico servico: Servico.values()){   
            System.out.println(i + ": " + servico.getNome());
            i++;
      }
      
      int n;
      
      
        while(true){
        System.out.println("Insira o serviço:");
         n = scan.nextInt();
         if(n >= i){
             System.out.println("Opção inválida.");
         }else{
             return Servico.valueOf(servicos[n].getNome().toUpperCase());
             
         }
      }
      
      
        
  }
  
  public static String receberTitulo(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      
      System.out.println("Escreva o título do chamado: ");
      String titulo = scan.nextLine();
      
      return titulo;
  }
  
  public static String dataAtual(){
      
      Date d = new Date();

       String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
       return dStr;
  }
  
  
  public static Criticidade receberCriticidade(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      
      Criticidade[] criticidades = Criticidade.values();
      
      int i = 0;  
      for(Criticidade criticidade: Criticidade.values()){   
            System.out.println(i + ": " + criticidade.getNome());
            i++;
      }
      
      int n;
      
      
        while(true){
        System.out.println("Insira a criticidade:");
         n = scan.nextInt();
         if(n >= i){
             System.out.println("Opção inválida.");
         }else{
             return Criticidade.valueOf(criticidades[n].getNome().toUpperCase());
             
         }
      }
        
      
  }
  
  
    public static Estado receberEstado(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      Estado[] estados = Estado.values();
      System.out.println("Estados disponíveis:");
      int i = 0;  
      for(Estado estado: Estado.values()){
            
            System.out.println(i + ": " + estado.getNome());
            i++;
      }
      
      
      int n;
      while(true){
        System.out.println("Insira o estado:");
         n = scan.nextInt();
         if(n >= i){
             System.out.println("Opção inválida.");
         }else{
             return Estado.valueOf(estados[n].getNome().toUpperCase());
             
         }
      }
             
      }    
  
  
  public static void exibirMenuBuscaChamado(){
    
  }
  
  public static List<Equipamento> receberEquipamentosChamado(CadastroEquipamento cadastroEquipamento){
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        List<Equipamento> equipamentos = new ArrayList();
        
        int n;
        
        
        int id_equipamento;
        
        
        
        
       
        n = 0;
        int k;
        k = 0;
        int verificar;
        Equipamento equipamento_chamado;
        while(n == 0){
            
            System.out.println("Digite o id do equipamento");
            id_equipamento = scan.nextInt();
            
            try{
                equipamento_chamado = cadastroEquipamento.buscarPorId(id_equipamento);
                
                
                
            }catch(Exception e){
                while(k != 2 && k != 3){
                    
                    System.out.println("Equipamento não existe. Deseja adicionar? 2 - Sim 3 - Não");
                    k = scan.nextInt();
                    if(k != 2 && k != 3){
                        System.out.println("Resposta inválida");
                    }
                }
                if(k == 2){
                    Equipamento equipamento = receberEquipamento(cadastroEquipamento);
                
                    try{
                        cadastroEquipamento.inserir(equipamento);
                        verificar = 0;
                    
                    for(Equipamento equipamento2: equipamentos){
                        if(equipamento2.getId() == (equipamento.getId())){
                            System.out.println("Equipamento já foi adicionado");
                            verificar = 1;
                            break;
                        }
                    }
                    if(verificar == 0){
                        equipamentos.add(equipamento);
                    }
                    
                    }catch(Exception e2){
                        System.out.println("Equipamento já existe");
                    }
                }
                
            }
            
            
            System.out.println("Digite 0 para adicionar equipamento e 1 para continuar");
 
            
            n = scan.nextInt();
        }
        return equipamentos;
  }
}