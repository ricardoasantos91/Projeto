/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto.tratamento;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ricar
 */
public class tratamento {
    
    public static String cpfTester(String CPF) throws CPFInvalidoException{
        String regex = "[0-9]+"; 
        boolean CPFcorreto = CPF.matches(regex);
        int digits = 0;
        if (CPFcorreto){
            for (int i = 0; i < CPF.length(); i++) {
            if (CPF.charAt(i) >= 48 && CPF.charAt(i) <= 57)
                digits++;
            }
        }else{
            throw new CPFInvalidoException("CPF invalido");
            
        }
        if (digits != 11){
            throw new CPFInvalidoException("CPF invalido");
        }
        return CPF;
    }
    
    public static String nameTester(String nome) throws NomeInvalidoException{
        String regex = "[a-zA-Z][a-zA-Z ]*";
        boolean nomeCorreto = nome.matches(regex);
        if(!nomeCorreto){
            throw new NomeInvalidoException("Nome inválido");
        }else{
            return nome;
        }
                
    }
    
    public static String dataTester(String data) throws DataInvalidaException{
        Pattern p = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Matcher m = p.matcher(data);
        if (m.find()){
            return data;
        }else{
            throw new DataInvalidaException("Data inválida.");
        }
    }
    public static String enderecoTester(String endereco) throws EnderecoInvalidoException{
        if(endereco.length() < 5){
            throw new EnderecoInvalidoException("Endereço inválido");
        }else{
            return endereco;
        }
    }
    
    public static String emailTester(String email) throws emailInvalidoException{
        String regex = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        boolean emailCorreto = email.matches(regex);
        if(!emailCorreto){
            throw new emailInvalidoException("Email invalido");
        }else{
            return email;
        }
    }
}
