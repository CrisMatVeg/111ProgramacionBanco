/*
 * Clase que representa un movimiento bancario.
 * Contiene información sobre la fecha, tipo de movimiento,
 * cantidad y saldo después del movimiento.
 */
package com.mycompany.banco;

import java.time.LocalDate;

/**
 * Clase Movimiento que almacena información sobre una transacción bancaria.
 */
public class Movimiento {
    private final LocalDate fecha; // Fecha del movimiento
    private final char tipo; // Tipo de movimiento: 'I' para ingreso, 'R' para retiro
    private final float cantidad; // Cantidad del movimiento
    private final float saldo; // Saldo después del movimiento
    
    /**
     * Constructor de la clase Movimiento.
     * 
     * @param fecha Fecha del movimiento.
     * @param tipo Tipo de movimiento ('I' para ingreso, 'R' para retiro).
     * @param cantidad Cantidad del movimiento.
     * @param saldo Saldo después del movimiento.
     */
    public Movimiento(LocalDate fecha, char tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }

    /**
     * Obtiene la fecha del movimiento.
     * 
     * @return Fecha del movimiento.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el tipo de movimiento.
     * 
     * @return Tipo de movimiento ('I' para ingreso, 'R' para retiro).
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * Obtiene la cantidad del movimiento.
     * 
     * @return Cantidad del movimiento.
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * Obtiene el saldo después del movimiento.
     * 
     * @return Saldo después del movimiento.
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Devuelve una representación en cadena del movimiento.
     * 
     * @return Cadena con la información del movimiento.
     */
    @Override
    public String toString() {
        return fecha + " / " + tipo + " / " + cantidad + " / " + saldo;
    }
}
