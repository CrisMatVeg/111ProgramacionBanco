/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representa una cuenta bancaria con un titular, un saldo y una lista de
 * movimientos. Permite realizar operaciones como ingresos, reintegros y
 * transferencias.
 *
 * @author Cristian Mateos Vega
 * @version 1.0
 * @since 1.0
 */
public class Cuenta implements Comparable<Cuenta> {

    private static final Logger LOG = Logger.getLogger(Cuenta.class.getName());
    private String codigo;
    private String titular;
    private float saldo;
    private List<Movimiento> movimientos;

    /**
     * Constructor por defecto. Inicializa la lista de movimientos.
     */
    public Cuenta() {
        movimientos = new ArrayList<>();
    }

    /**
     * Constructor que inicializa la cuenta con un código.
     *
     * @param codigo Código único de la cuenta.
     * @throws com.mycompany.banco.IbanException
     */
    public Cuenta(String codigo) throws IbanException{
        if(!esIbanValido(codigo)){
            throw new IbanException("Codigo Incorrecto");
        }
        this.codigo = codigo;
    }

    /**
     * Constructor que inicializa la cuenta con un código, titular y saldo
     * inicial.
     *
     * @param codigo Código único de la cuenta.
     * @param titular DNI del titular de la cuenta.
     * @param saldo Saldo inicial de la cuenta (debe ser mayor o igual a 0).
     * @throws com.mycompany.banco.IbanException
     */
    public Cuenta(String codigo, String titular, float saldo) throws IbanException {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo debe ser positivo");
        }
        if(!esIbanValido(codigo)){
            throw new IbanException("Codigo Incorrecto");
        }
        this.codigo = codigo;
        this.titular = titular;
        this.saldo = saldo;
    }

    /**
     * Obtiene el código de la cuenta.
     *
     * @return Código de la cuenta.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el titular de la cuenta.
     *
     * @return Titular de la cuenta.
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Obtiene el saldo de la cuenta.
     *
     * @return Saldo actual de la cuenta.
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Obtiene la lista de todos los movimientos de la cuenta.
     *
     * @return Lista de movimientos de la cuenta.
     */
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * Obtiene los movimientos realizados en un rango de fechas.
     *
     * @param desde Fecha de inicio del rango.
     * @param hasta Fecha de fin del rango.
     * @return Lista de movimientos dentro del rango especificado.
     */
    public List<Movimiento> getMovimientos(LocalDate desde, LocalDate hasta) {
        return movimientos;
    }

    /**
     * Establece el código de la cuenta.
     *
     * @param codigo Nuevo código de la cuenta.
     */
    public void setCodigo(String codigo) throws IbanException {
        if(!esIbanValido(codigo)){
            throw new IbanException("Codigo Incorrecto");
        }
        this.codigo = codigo;
    }

    /**
     * Establece el titular de la cuenta.
     *
     * @param titular Nuevo titular de la cuenta.
     */
    public void setTitular(String titular) {
        this.titular = titular;
    }

    /**
     * Establece el saldo de la cuenta (siempre que sea un valor positivo o
     * cero).
     *
     * @param saldo Nuevo saldo de la cuenta.
     */
    public void setSaldo(float saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo a definir debe ser positivo");
        }
        this.saldo = saldo;
    }

    /**
     * Realiza un ingreso en la cuenta.
     *
     * @param cantidad Cantidad a ingresar (debe ser mayor a 0).
     */
    public void ingresar(float cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a integrar debe ser positiva");
        }
        saldo += cantidad;
        movimientos.add(new Movimiento(LocalDate.now(), 'I', cantidad, saldo));
    }

    /**
     * Realiza un reintegro en la cuenta.
     *
     * @param cantidad Cantidad a retirar (debe ser mayor a 0 y no superar el
     * saldo).
     * @throws com.mycompany.banco.SaldoInsuficienteException
     */
    public void reintegrar(float cantidad) throws SaldoInsuficienteException {
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a reintegrar debe ser positiva");
        }
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            movimientos.add(new Movimiento(LocalDate.now(), 'R', -cantidad, saldo));
        }
    }

    /**
     * Realiza una transferencia a otra cuenta.
     *
     * @param destino Cuenta destino de la transferencia.
     * @param cantidad Cantidad a transferir (debe ser mayor a 0 y no superar el
     * saldo).
     * @throws com.mycompany.banco.SaldoInsuficienteException
     */
    public void realizarTransferencia(Cuenta destino, float cantidad) throws SaldoInsuficienteException {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a transferir debe ser positiva");
        }
        if (destino == null) {
            throw new NullPointerException("El destino no existe");
        }
        if (destino == this) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta");
        }
        if (cantidad > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        saldo -= cantidad;
        movimientos.add(new Movimiento(LocalDate.now(), 'T', -cantidad, saldo));
        destino.recibirTransferencia(this, cantidad);
    }

    /**
     * Recibe una transferencia de otra cuenta.
     *
     * @param origen Cuenta de origen de la transferencia.
     * @param cantidad Cantidad recibida (debe ser mayor a 0).
     * @throws com.mycompany.banco.SaldoInsuficienteException
     */
    public void recibirTransferencia(Cuenta origen, float cantidad) throws SaldoInsuficienteException {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a recibir debe ser positiva");
        }
        if (origen == null) {
            throw new NullPointerException("El origen no existe");
        }
        saldo += cantidad;
        movimientos.add(new Movimiento(LocalDate.now(), 'T', cantidad, saldo));
        origen.realizarTransferencia(this, cantidad);
    }

    /**
     * Lista los movimientos de la cuenta en formato de cadena.
     *
     * @return Representación en cadena de los movimientos.
     */
    public String listarMovimientos() {
        return movimientos.toString();
    }

    /**
     * Representación en cadena de la cuenta.
     *
     * @return Cadena con el código, titular y saldo de la cuenta.
     */
    @Override
    public String toString() {
        return codigo + "," + titular + "," + saldo;
    }

    /**
     * Genera un código hash para la cuenta basado en su código.
     *
     * @return Código hash de la cuenta.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    /**
     * Compara si dos cuentas son iguales en base a su código.
     *
     * @param obj Objeto a comparar.
     * @return true si los códigos son iguales, false en caso contrario.
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

    /**
     * Compara dos cuentas por su código.
     *
     * @param o Cuenta a comparar.
     * @return Resultado de la comparación.
     */
    @Override
    public int compareTo(Cuenta o) {
        return this.codigo.compareTo(o.codigo);
    }

    public static boolean esIbanValido(String codigoIban){
        String patron="(ES)(\\d{2})(\\d{4})(\\d{4})(\\d{2})(\\d{10})";
        Pattern p=Pattern.compile(patron);
        Matcher m=p.matcher(codigoIban);
        return m.matches();
    }
}
