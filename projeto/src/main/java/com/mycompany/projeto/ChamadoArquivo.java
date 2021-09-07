package com.mycompany.projeto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChamadoArquivo{
  
  public static void escreverArquivo(CadastroChamado chamados){
    File file = new File("ChamadoArquivo.bin");
    try{
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(chamados);
            //oos.writeObject(funcionario2);
            //oos.writeObject(funcionario3);

      oos.close();
      fos.close();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static CadastroChamado lerArquivo(File file){
    CadastroChamado chamados = new CadastroChamado();

    try(FileInputStream fileStream = new FileInputStream(file)){
            // Creating an object input stream
            try(ObjectInputStream objStream = new ObjectInputStream(fileStream)){
                
                try{
                    chamados = (CadastroChamado) objStream.readObject();
                    return chamados;
                }catch(Exception e){
                    e.printStackTrace();
                }
                    
                objStream.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            
        
    }
    catch(FileNotFoundException e){
      return chamados;
    }
    catch(IOException e){
      e.printStackTrace();
    }
    return null;
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