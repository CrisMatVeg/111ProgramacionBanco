/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

import java.util.Objects;

/**
 * @author Cristian Mateos Vega
 * @since 1.0
 */
public class Cuenta {
    private String codigo;
    private String titular;
    private float saldo;

    /**
     *
     * @param codigo    es el código de la cuenta
     * @param titular   DNI del titular de la cuenta
     * @param saldo     saldo de la cuenta
     */
    public Cuenta(String codigo, String titular, float saldo) {
        this.codigo = codigo;
        this.titular = titular;
        if(saldo>=0){
            this.saldo = saldo;   
        }
    }

    /**
     *
     * @return devuelve el codigo de la cuenta
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @return devuelve el titular de la cuenta
     */
    public String getTitular() {
        return titular;
    }

    /**
     *
     * @return devuelve el saldo de la cuenta
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     *
     * @param codigo establece un codigo de cuenta
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @param titular
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     *
     * @param saldo
     */
    public void setSaldo(float saldo) {
        if(saldo>=0){
         this.saldo = saldo;   
        }
    }
    
    /**
     *
     * @param cantidad
     */
    public void ingresar(float cantidad){
        if(cantidad>=0){
            saldo+=cantidad;
        }
    }

    /**
     *
     * @param cantidad
     */
    public void reintegrar(float cantidad){
        if(cantidad>0 && cantidad<=saldo){
            saldo-=cantidad;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cuenta other = (Cuenta) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
    
}
