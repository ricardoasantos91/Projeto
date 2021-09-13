/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projeto.tratamento;

/**
 *
 * @author ricar
 */
public class DataInvalidaException extends Exception {
    public DataInvalidaException(String msg){
        super(msg);
    }
}