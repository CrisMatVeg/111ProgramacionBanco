/*
 * Cristian Mateos Vega   DAW1
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

import java.time.LocalDate;

/**
 *
 * @author cristian.matveg
 */
public class Movimiento {
    private final LocalDate fecha;
    private final char tipo;
    private final float cantidad;
    private final float saldo;
    
public Movimiento(LocalDate fecha, char tipo, float cantidad, float saldo) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.saldo = saldo;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public char getTipo() {
        return tipo;
    }
    public float getCantidad() {
        return cantidad;
    }
    public float getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return fecha +" / "+ tipo + " / " + cantidad + " / " + saldo;
    }
    
}
