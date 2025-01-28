/*
 * Cristian Mateos Vega   DAW1
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

import java.util.ArrayList;
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
        return new ArrayList<>(cuentas);
    }

    public void setCuentas(List<Cuenta> cuenta) {
        this.cuentas = cuenta;
    }
    
 //METODOS//
    public boolean abrirCuenta(Cuenta cuenta){
        if(buscarCuenta(cuenta.getCodigo())==null){
            return cuentas.add(cuenta);
        }       
        return false;
    }
    
    public Cuenta buscarCuenta(String codigo){
        int posicion=cuentas.indexOf(new Cuenta(codigo));
        if(posicion!=-1){
            return cuentas.get(posicion);
        }
        return null;
    }
    
    public boolean cancelarCuenta(String codigo){
        return cuentas.remove(new Cuenta(codigo));
    }
    
    public float getTotalDepositos(){
        float acumulador=0;
        for(Cuenta c: cuentas){
            acumulador+=c.getSaldo();
        }
        return acumulador;
    }
    
}
