package com.mycompany.projeto;
import java.text.DateFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Tela{
  
  public static void exibirMenu(CadastroCliente cadastroCliente, CadastroAtendente cadastroAtendente, CadastroChamado cadastroChamado, CadastroEquipamento cadastroEquipamento){
      Scanner scan = new Scanner(System.in);

      scan.useDelimiter("\n");

    System.out.println("Escolha uma opção:\n");
    System.out.println("1 - Abrir um novo chamado");
    System.out.println("2 - Tratar um chamado");
    System.out.println("3 - Consultar chamado");
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
            cliente = cadastroCliente.buscarPorCpf(cpfB);
            
        }catch(Exception e){
            System.out.println("Cliente não existe e deve ser cadastrado!");
            cliente = receberCliente();
            
            try{
                cadastroCliente.inserir(cliente);
            }catch(Exception ex2){
                System.out.println("Alerta: pessoa já existe");
            }
        }
        
        System.out.println("Insira o CPF do atendente que deseja buscar:");
        String cpfA = scan.nextLine();
        
        try{
            atendente = cadastroAtendente.buscarPorCpf(cpfA);


    
        }catch(Exception e){
            System.out.println("Atendente não existe e deve ser cadastrado!");
            atendente = receberAtendente();
            try{
                cadastroAtendente.inserir(atendente);
            }catch(Exception ex3){
                System.out.println("Alerta: pessoa já existe");
            }
        }
        
        List<Equipamento> equipamentos = receberEquipamentosChamado(cadastroEquipamento);
        
        Servico servico = receberServico();
        Criticidade criticidade = receberCriticidade();
        Estado estado = Estado.EM_ANALISE;
        String data_criacao = dataAtual();
        List<String> data_modificacao = new ArrayList();
        
        int id = cadastroChamado.numChamados()+1;
        String titulo = receberTitulo();
        
        System.out.println("Digite a descrição: ");
        String descricao = scan.nextLine();
        
        List<String> descricoes = new ArrayList();
        descricoes.add(descricao);
        
        Chamado chamado = new Chamado(id, titulo, data_criacao, data_modificacao, servico, atendente, cliente, estado, criticidade, equipamentos, descricoes);
        
        try{
            cadastroChamado.inserir(chamado);
        }catch(Exception ex){
            System.out.println("Chamado já existe");
        }
        
        exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
        
        
        
        
      case 2:
          exibirMenuTratarChamado(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento, 0);
      case 3:
          exibirMenuBuscaChamado(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      case 4:
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      case 5:
        ClienteArquivo.escreverArquivo(cadastroCliente);
        AtendenteArquivo.escreverArquivo(cadastroAtendente);
        ChamadoArquivo.escreverArquivo(cadastroChamado);
        EquipamentoArquivo.escreverArquivo(cadastroEquipamento);
        System.exit(0);
    }
  }

  public static void exibirMenuTratarChamado(CadastroCliente cadastroCliente, CadastroAtendente cadastroAtendente, CadastroChamado cadastroChamado, CadastroEquipamento cadastroEquipamento, int idChamado){
      int n;
      int id_chamado;
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");      
      if (idChamado == 0){
        System.out.println("Digite o id do chamado");
        id_chamado = scan.nextInt();
      }else{
        id_chamado = idChamado;
      }
      
      try{
          Chamado chamado = cadastroChamado.buscarPorId(id_chamado);
          
          
          while(true){
            System.out.println("Menu:");
            System.out.println("1 - Adicionar atualização");
            System.out.println("2 - Mudar estado");
            System.out.println("3 - Mudar criticidade");
            System.out.println("4 - Voltar");
            
            n = scan.nextInt();
            
            switch(n){
                
                case 1:
                    String atualizacao = receberAtualizacao();
                    chamado.adicionarDescricao(atualizacao);
                    chamado.adicionarDataModificacao(dataAtual());
                    break;
                    
                case 2:
                    Estado estado = receberEstado();
                    chamado.setEstado(estado);
                    
                    break;
                    
                case 3:
                    Criticidade criticidade = receberCriticidade();
                    chamado.setCriticidade(criticidade);
         
                    break;
                    
                case 4:
                    exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
                    break;
                        
                      
                
            }
            
            
          }

          
      }catch(Exception e){
          System.out.println("Chamado não existe");
          exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      }
      
      exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      

      
      
  }
  
  public static String receberAtualizacao(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      System.out.println("Digite a nova atualização:");
      
      String atualizacao = scan.nextLine();
      
      return atualizacao;
      
      
  }
  
  public static void exibirMenuAdministracao(CadastroCliente cadastroCliente,CadastroAtendente cadastroAtendente, CadastroChamado cadastroChamado, CadastroEquipamento cadastroEquipamento){
    Scanner scan = new Scanner(System.in);
    scan.useDelimiter("\n");
      System.out.println("Escolha uma opção:");
    System.out.println("1 - Cadastrar cliente");
    System.out.println("2 - Cadastrar atendente");
    System.out.println("3 - Cadastrar equipamento");
    System.out.println("4 - Buscar cliente");
    System.out.println("5 - Buscar atendente");
    System.out.println("6 - Buscar equipamento");
    System.out.println("7 - Remover cliente");
    System.out.println("8 - Remover atendente");
    System.out.println("9- Remover equipamento");
    System.out.println("10 - Voltar");
    System.out.println("11 - Sair");
    

    int opcao = scan.nextInt();

    switch(opcao){
      case 1:
        
        Cliente cliente = receberCliente();
        
        try{
          cadastroCliente.inserir(cliente);
        }
        catch(Exception e){
            System.out.println("erro");
        }
        
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      case 2:

        Atendente atendente = receberAtendente();
        try{
          cadastroAtendente.inserir(atendente);
        }
        catch(Exception e){

        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      case 3:
        Equipamento equipamento = receberEquipamento(cadastroEquipamento);
        try{
          cadastroEquipamento.inserir(equipamento);
        }
        catch(Exception e){

        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);

      case 4:
        scan.nextLine();

        System.out.println("Insira o CPF do cliente que deseja buscar:");
        String cpfB = scan.nextLine();

        try{
            
          Cliente pes = cadastroCliente.buscarPorCpf(cpfB);
          pes.print();
        }
        catch(Exception e){
          System.out.println("Pessoa não encontrada");
        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);

      case 5:
        scan.nextLine();

        System.out.println("Insira o CPF do atendente que deseja buscar:");
        cpfB = scan.nextLine();

        try{
            
          Atendente pes = cadastroAtendente.buscarPorCpf(cpfB);
          pes.print();
        }
        catch(Exception e){
          System.out.println("Pessoa não encontrada");
        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);

      case 6:
        scan.nextLine();
        System.out.println("Insira o tipo de equipamento que deseja buscar:");
        String tipoB = scan.nextLine();

        try{
          TipoEquipamento tipoEquipamento = TipoEquipamento.valueOf(tipoB);

          List<Equipamento> equipamentos = cadastroEquipamento.buscarPorTipo(tipoEquipamento);

          for(Equipamento equip : equipamentos){
            equip.print();
          }
        }
        catch(Exception e){
            System.out.println("Equipamento não encontrado");
        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);

      case 7:
         scan.nextLine();
        System.out.println("Insira o CPF do cliente que deseja remover:");
        String cpfR = scan.nextLine();
        
        try{
          List<Chamado> chamados = new ArrayList();
          Cliente pessoaR = cadastroCliente.buscarPorCpf(cpfR);
          if (pessoaR instanceof Cliente){
              try{
                  System.out.println("Chamados desse cliente serão marcados como concluído");
                  chamados = cadastroChamado.buscarPorCliente(cadastroCliente, (Cliente) pessoaR);
              }catch(Exception e){
              }
              if(chamados.size() > 0){
                  for(Chamado chamado: chamados){
                      chamado.setEstado(Estado.CONCLUIDO);
                  }
              }
          }
          
          cadastroCliente.remover((Cliente) pessoaR);
        }
        catch(Exception e){
            System.out.println("Pessoa não encontrada");
        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);


      case 8:
        scan.nextLine();
        System.out.println("Insira o CPF do atendente que deseja remover:");
        cpfR = scan.nextLine();
        
        try{
          List<Chamado> chamados = new ArrayList();
          Atendente pessoaR = cadastroAtendente.buscarPorCpf(cpfR);
          
          
          cadastroAtendente.remover(pessoaR);
        }
        catch(Exception e){
            System.out.println("Pessoa não encontrada");
        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);

      case 9:
        System.out.println("Insira o Id do equipamento que deseja remover:");
        int idR = scan.nextInt();

        try{
          Equipamento equipamentoR = cadastroEquipamento.buscarPorId(idR);

          cadastroEquipamento.remover(equipamentoR);
        }
        catch(Exception e){
          
        }
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);

      case 10:
        exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
        
      case 11:
        ClienteArquivo.escreverArquivo(cadastroCliente);
        AtendenteArquivo.escreverArquivo(cadastroAtendente);
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

    System.out.println("Insira o email:");
    String email2 = scan.nextLine();

    System.out.println("Insira o telefone:");
    String telefone2 = scan.nextLine();

    System.out.println("Insira o departamento:");
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

        Equipamento equipamento = new Equipamento(cadastroEquipamento.getNextId(), TipoEquipamento.valueOf(tipo), data3);
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
  
  
  public static void exibirMenuBuscaChamado(CadastroCliente cadastroCliente, CadastroAtendente cadastroAtendente, CadastroChamado cadastroChamado, CadastroEquipamento cadastroEquipamento){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      
      int id_chamado;
      System.out.println("Digite o id do chamado");
      id_chamado = scan.nextInt();
      
      
      try{
        Chamado chamado = cadastroChamado.buscarPorId(id_chamado);
        chamado.printChamado();
        int k;
        k = 2;
        while(k != 0 && k != 1){
            System.out.println("Deseja editar o chamado? 0 - Não, 1 - Sim");
            k = scan.nextInt();

            switch(k){
                case 0:
                    exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);           
                case 1:
                    exibirMenuTratarChamado(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento, id_chamado);
                default:
                    System.out.println("Opção inválida"); 
                    break;
            }
        }
      }catch(Exception e){
          System.out.println("Chamado não encontrado");
          exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      }
      
      
      
      
  }
  
  public static List<Equipamento> receberEquipamentosChamado(CadastroEquipamento cadastroEquipamento){
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        List<Equipamento> equipamentos = new ArrayList();
        
        int n;
        
        
        int id_equipamento;
        
        
        
        int count = 0;
        
        n = 0;
        int k;
        k = 0;
        int verificar;
        Equipamento equipamento_chamado;
        while(n == 0){
            if (count == 0){
                id_equipamento = cadastroEquipamento.getNextId();
                Equipamento equipamento = receberEquipamento(cadastroEquipamento);
                try{
                    cadastroEquipamento.inserir(equipamento);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Digite a id do equipamento:");
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
          
                    }catch(Exception e2){
                        System.out.println("Equipamento já existe");
                    }
                }
                
            }
            }
            
            
            

                
                
                
            
            
            
            System.out.println("Digite 0 para adicionar equipamento e 1 para continuar");
            count++;
            
            n = scan.nextInt();
        }
        return equipamentos;
  }
  
  /*
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
            
            id_equipamento = cadastroEquipamento.getNextId();
            
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
  */
}