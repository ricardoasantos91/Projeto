package com.mycompany.projeto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PessoaArquivo{
  
  public static void escreverArquivo(CadastroPessoa pessoas){
    File file = new File("PessoaArquivo.bin");
    try{
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(pessoas);
            //oos.writeObject(funcionario2);
            //oos.writeObject(funcionario3);

      oos.close();
      fos.close();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static CadastroPessoa lerArquivo(File file){
    CadastroPessoa pessoas = new CadastroPessoa();

    try{
       FileInputStream fileStream = new FileInputStream(file);    // Creating an object input stream
            try{
                ObjectInputStream objStream = new ObjectInputStream(fileStream);
                try{
                    pessoas = (CadastroPessoa) objStream.readObject();
                    System.out.println("teste7");
                    return pessoas;
                }catch(Exception e){
                   System.out.println("teste4");
                    e.printStackTrace();
                }
                    
                objStream.close();
            }catch(IOException e){
                System.out.println("teste3");
                e.printStackTrace();
            }
            
        
    }
    catch(FileNotFoundException e){
      System.out.println("teste");
      return pessoas;
    }
    catch(IOException e){
      System.out.println("teste2");
      e.printStackTrace();
    }
    return pessoas;
  }

    
    /*public static Pessoa pessoaSearch(File file) {
        Scanner teclado = new Scanner(System.in);
        String nome;
        nome = teclado.nextLine();
        
        try(FileInputStream fileStream = new FileInputStream(file)){
            // Creating an object input stream
            try(ObjectInputStream objStream = new ObjectInputStream(fileStream)){
                
                try{
                    CadastroFuncionario funcionarios = (CadastroFuncionario) objStream.readObject();
                    Funcionario funcionario = funcionarios.buscarFuncionario(nome);
                    return funcionario;
                }catch(Exception e){
                    e.printStackTrace();
                }
                    
                objStream.close();
            }catch(IOException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            
        
    }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            }catch(IOException e){
                e.printStackTrace();
            }
        
    
    return null;
    
}*/
}