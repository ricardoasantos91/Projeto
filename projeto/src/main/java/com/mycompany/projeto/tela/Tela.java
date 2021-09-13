package com.mycompany.projeto.tela;
import com.mycompany.projeto.departamento.Departamento;
import com.mycompany.projeto.equipamento.Equipamento;
import com.mycompany.projeto.estado.Estado;
import com.mycompany.projeto.arquivo.EquipamentoArquivo;
import com.mycompany.projeto.servico.Servico;
import com.mycompany.projeto.equipamento.TipoEquipamento;
import com.mycompany.projeto.criticidade.Criticidade;
import com.mycompany.projeto.arquivo.ChamadoArquivo;
import com.mycompany.projeto.chamado.Chamado;
import com.mycompany.projeto.cadastro.CadastroEquipamento;
import com.mycompany.projeto.cadastro.CadastroChamado;
import com.mycompany.projeto.pessoa.Cliente;
import com.mycompany.projeto.cadastro.CadastroCliente;
import com.mycompany.projeto.arquivo.ClienteArquivo;
import com.mycompany.projeto.cadastro.CadastroAtendente;
import com.mycompany.projeto.pessoa.Atendente;
import com.mycompany.projeto.arquivo.AtendenteArquivo;
import com.mycompany.projeto.tratamento.*;

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
    System.out.println("5 - Relatório");
    System.out.println("6 - Sair");

    String cpfB;
    int opcao = scan.nextInt();
    int n;
    Atendente atendente = new Atendente();
    Cliente cliente = new Cliente();
    switch(opcao){
      
      case 1:
        scan.nextLine();
        System.out.println("Insira o CPF do cliente que deseja buscar:");
        
        while(true){
            try{
                cpfB = scan.nextLine();
                cpfB = tratamento.cpfTester(cpfB);
                break;
            }catch(CPFInvalidoException e){
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
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
        
        String cpfA;
        while(true){
            try{
                cpfA = scan.nextLine();
                cpfA = tratamento.cpfTester(cpfA);
                break;
            }catch(CPFInvalidoException e){
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
        
        
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
        
        int id = cadastroChamado.numChamados();
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
          Estado[] estados = Estado.values();
          List<Chamado> chamado_estados = new ArrayList();
          for(Estado estado_atual: estados){
              try{
                  chamado_estados = cadastroChamado.buscarPorEstado(estado_atual);
                  System.out.println("Estado: " + estado_atual.getNome() + " Número de chamados: " + chamado_estados.size());
                  
                  for(Chamado chamado_atual:chamado_estados){
                    
                      System.out.println("ID: " + chamado_atual.getId() + " Data de criação: " + chamado_atual.getDataCriacao() + " Nome do cliente: " + chamado_atual.getCliente().getNome());
                  }
              }catch(Exception ex){
                  System.out.println("Estado: " + estado_atual.getNome() + " Número de chamados: " + chamado_estados.size());
                  
              }
 
          }
          exibirMenu(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      case 6:
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
            System.out.println("Cliente já existe");
        }
        
        exibirMenuAdministracao(cadastroCliente, cadastroAtendente, cadastroChamado, cadastroEquipamento);
      case 2:

        Atendente atendente = receberAtendente();
        try{
          cadastroAtendente.inserir(atendente);
        }
        catch(Exception e){
            System.out.println("Atendente já existe");
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
        String cpfB = receberCPF();

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
        cpfB = receberCPF();

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
        TipoEquipamento tipoEquipamento = receberTipoEquipamento();

        try{
          

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
        String cpfR = receberCPF();
        
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
        cpfR = receberCPF();
        
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
    String nome = receberNome();

    System.out.println("Insira o CPF:");
    String cpf = receberCPF();
    
    System.out.println("Insira a data de nascimento:");
    String data = receberData();

    System.out.println("Insira o endereço:");
    String endereco = receberEndereco();

    System.out.println("Insira o email:");
    
    String email;
    while(true){
        try{
            email = scan.nextLine();
            email = tratamento.emailTester(email);
            break;
        }catch(emailInvalidoException e){
            System.out.println(e.getMessage());
            System.out.println("Tente novamente.");
        }
    }


    System.out.println("Insira o telefone:");
    String telefone = scan.nextLine();

    System.out.println("Insira o departamento:");
    Departamento departamento = receberDepartamento();
    Cliente cliente = new Cliente(nome, cpf, data, endereco, email, telefone, departamento);
    
    return cliente;
  }
  
  public static Atendente receberAtendente(){
    Scanner scan = new Scanner(System.in);
    scan.useDelimiter("\n");
    System.out.println("Insira o nome:");
    String nome2 = receberNome();

    System.out.println("Insira o CPF:");
    String cpf2 = receberCPF();

    System.out.println("Insira a data de nascimento:");
    String data2 = receberData();

    System.out.println("Insira o endereço:");
    String endereco2 = receberEndereco();

    System.out.println("Insira o email:");
    String email2;
    
    while(true){
        try{
            email2 = scan.nextLine();
            email2 = tratamento.emailTester(email2);
            break;
        }catch(emailInvalidoException e){
            System.out.println(e.getMessage());
            System.out.println("Tente novamente.");
        }
    }

    System.out.println("Insira o telefone:");
    String telefone2 = scan.nextLine();

    System.out.println("Insira o departamento:");
    Departamento departamento2 = receberDepartamento();

    //Departamento departamento2 = Departamento.valueOf(dep2);

    Atendente atendente = new Atendente(nome2, cpf2, data2, endereco2, email2, telefone2, departamento2);

    return atendente;
      
  }
  
  public static Equipamento receberEquipamento(CadastroEquipamento cadastroEquipamento){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      System.out.println("Insira o tipo de equipamento:");
        TipoEquipamento tipo = receberTipoEquipamento();

        System.out.println("Insira a data de instalação:");
        
        String data3;
        
        data3 = receberData();


        Equipamento equipamento = new Equipamento(cadastroEquipamento.getNextId(), tipo, data3);
        return equipamento;
      
  }
  
  
  public static Servico receberServico(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      
      Servico[] servicos = Servico.values();
      System.out.println("Serviços disponíveis");
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
  
  public static String receberData(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      String data3;
        while(true){
            try{
                data3 = scan.nextLine();
                data3 = tratamento.dataTester(data3);
                break;
            }catch(DataInvalidaException e){
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
        return data3;
      
  }
  public static String receberCPF(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      String cpfA;
      while(true){
        try{
                cpfA = scan.nextLine();
                cpfA = tratamento.cpfTester(cpfA);
                break;
            }catch(CPFInvalidoException e){
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
      return cpfA;
  }
  
  public static String receberNome(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      String nome;
        while(true){
            try{
                nome = scan.nextLine();
                nome = tratamento.nameTester(nome);
                break;
            }catch(NomeInvalidoException e){
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
        return nome;
      
  }
  
  public static String receberEndereco(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      String endereco;
        while(true){
            try{
                endereco = scan.nextLine();
                endereco = tratamento.enderecoTester(endereco);
                break;
            }catch(EnderecoInvalidoException e){
                System.out.println(e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
        return endereco;
      
  }
  public static TipoEquipamento receberTipoEquipamento(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      
      TipoEquipamento[] tipos = TipoEquipamento.values();
      
      int i = 0;  
      for(TipoEquipamento tipo: TipoEquipamento.values()){   
            System.out.println(i + ": " + tipo.getNome());
            i++;
      }
      
      int n;
      
      
        while(true){
        System.out.println("Insira o tipo de equipamento:");
         n = scan.nextInt();
         if(n >= i){
             System.out.println("Opção inválida.");
         }else{
             return TipoEquipamento.valueOf(tipos[n].getNome().toUpperCase());
             
         }
      }
        
      
  }
  
  
  public static Criticidade receberCriticidade(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      
      Criticidade[] criticidades = Criticidade.values();
      System.out.println("Criticidades disponíveis:");
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
  
    public static Departamento receberDepartamento(){
      Scanner scan = new Scanner(System.in);
      scan.useDelimiter("\n");
      Departamento[] departamentos = Departamento.values();
      System.out.println("Departamentos disponíveis:");
      int i = 0;  
      for(Departamento departamento: Departamento.values()){
            
        System.out.println(i + ": " + departamento.getNome());
        i++;    
      }   
      int n;
      while(true){
        System.out.println("Insira o departamento:");
         n = scan.nextInt();
         if(n >= i){
             System.out.println("Opção inválida.");
         }else{
             return Departamento.valueOf(departamentos[n].getNome().toUpperCase());
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
                System.out.println("Digite a id do equipamento:");
                id_equipamento = scan.nextInt();
                try{
                    equipamento_chamado = cadastroEquipamento.buscarPorId(id_equipamento);
                    equipamentos.add(equipamento_chamado);
                }catch(Exception e){
                    System.out.println("Equipamento não existe e precisa ser adicionado.");
                    id_equipamento = cadastroEquipamento.getNextId();
                    Equipamento equipamento = receberEquipamento(cadastroEquipamento);
                    try{
                        cadastroEquipamento.inserir(equipamento);
                        equipamentos.add(equipamento);
                    }catch(Exception e2){
                        e2.printStackTrace();
                    }
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
                        equipamentos.add(equipamento);
          
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