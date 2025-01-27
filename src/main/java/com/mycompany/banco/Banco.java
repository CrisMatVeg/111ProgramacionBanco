/*
 * Cristian Mateos Vega   DAW1
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cristian.matveg
 */
public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;
    private Cuenta cuenta;
    
    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas=new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuenta) {
        this.cuentas = cuentas;
    }
    
    public boolean abrirCuenta(Cuenta cuenta){
        return cuentas.add(cuenta);        
    }
    
    public Cuenta buscarCuenta(String codigo){
        cuenta=null;
        if(this.cuenta!=null && codigo.equals(this.cuenta.getCodigo())){
            cuenta=this.cuenta;
        }
        return cuenta;
    }
    
    public boolean cancelarCuenta(String codigo){
        return true;
    }
    
    public float getTotalDepositos(){
        float acumulador=0;
        for(Cuenta c: cuentas){
            acumulador+=c.getSaldo();
        }
        return acumulador;
    }
    
}
