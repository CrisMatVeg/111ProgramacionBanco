package com.mycompany.banco;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un banco con un nombre y una lista de cuentas.
 * Permite gestionar cuentas bancarias.
 * 
 * @author cristian.matveg
 */
public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;
    private Cuenta cuenta;
    
    /**
     * Constructor de la clase Banco.
     * @param nombre Nombre del banco.
     */
    public Banco(String nombre) {
        this.nombre = nombre;
        cuentas = new LinkedList<>();
    }

    /**
     * Obtiene el nombre del banco.
     * @return Nombre del banco.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del banco.
     * @param nombre Nuevo nombre del banco.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de cuentas del banco.
     * @return Lista de cuentas.
     */
    public List<Cuenta> getCuentas() {
        return new ArrayList<>(cuentas);
    }

    /**
     * Establece la lista de cuentas del banco.
     * @param cuenta Nueva lista de cuentas.
     */
    public void setCuentas(List<Cuenta> cuenta) {
        this.cuentas = cuenta;
    }
    
    // MÉTODOS

    /**
     * Abre una nueva cuenta en el banco.
     * @param cuenta Cuenta a agregar.
     * @return true si se agregó correctamente, false si la cuenta ya existe.
     */
    public boolean abrirCuenta(Cuenta cuenta){
        if(buscarCuenta(cuenta.getCodigo()) == null){
            return cuentas.add(cuenta);
        }       
        return false;
    }
    
    /**
     * Busca una cuenta en el banco por su código.
     * @param codigo Código de la cuenta a buscar.
     * @return La cuenta si existe, null si no se encuentra.
     */
    public Cuenta buscarCuenta(String codigo){
        int posicion = cuentas.indexOf(new Cuenta(codigo));
        if(posicion != -1){
            return cuentas.get(posicion);
        }
        return null;
    }
    
    /**
     * Cancela una cuenta del banco.
     * @param codigo Código de la cuenta a cancelar.
     * @return true si se eliminó correctamente, false si no se encontró.
     */
    public boolean cancelarCuenta(String codigo){
        return cuentas.remove(new Cuenta(codigo));
    }
    
    /**
     * Obtiene el total de depósitos en el banco.
     * @return Suma total de los saldos de todas las cuentas.
     */
    public float getTotalDepositos(){
        float acumulador = 0;
        for(Cuenta c : cuentas){
            acumulador += c.getSaldo();
        }
        return acumulador;
    }
}
