/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.banco;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author cristian.matveg
 */
public class AplicacionBanco {

    private static final Logger LOG=Logger.getLogger("com.mycompany.banco");
    public static void main(String[] args) throws IOException {
        Cuenta cuenta = null;
        Banco banco = new Banco("mibanco");
        int opcion;
        boolean error;
        String codigo=null, titular;
        float saldo, cantidad;
        Scanner teclado = new Scanner(System.in);
        Handler controlador=new FileHandler("./registro.log",true);
        controlador.setFormatter(new SimpleFormatter());
        LOG.addHandler(controlador);
        LogManager.getLogManager().readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream("mylogging.properties"));
        
        do {
            System.out.println("1- Abrir Cuenta");
            System.out.println("2- Operar con cuenta");
            System.out.println("3- Cancelar Cuenta");
            System.out.println("4- Listar cuentas");
            System.out.println("5- Consultar total depositos");
            System.out.println("0- Salir");
            try {
                opcion = teclado.nextInt();
            } catch (InputMismatchException ime) {
                opcion = 1000;
            }
            teclado.nextLine();
            if (opcion >= 1 && opcion <= 5) {
                if (opcion == 1) {

                    do {
                        error = false;
                        try {
                            System.out.println("Introduzca codigo cuenta:");
                            codigo = teclado.nextLine();
                            System.out.println("Introduzca titular cuenta:");
                            titular = teclado.nextLine();
                            System.out.println("Introduzca saldo cuenta:");
                            saldo = teclado.nextFloat();
                            teclado.nextLine();
                            cuenta = new Cuenta(codigo, titular, saldo);
                            System.out.println(cuenta.toString());
                            if (banco.abrirCuenta(cuenta)) {
                                System.out.println("Cuenta creada con exito ");
                            } else {
                                System.out.println("No se puede abrir la cuenta " + cuenta);
                            }
                        } catch (IllegalArgumentException | IbanException iae) {
                            LOG.log(Level.WARNING, "{0},{1}", new Object[]{iae.getMessage(), codigo});
                            System.out.println(iae.getMessage());
                            error = true;
                        } catch (InputMismatchException ime) {
                            System.out.println("Entrada incorrecta");
                            error = true;
                        } catch (NullPointerException npe) {
                            System.out.println("hay algun null");
                            error = true;
                        } finally {
                            teclado.nextLine();
                        }
                    } while (error);
                } else {
                    System.out.println("Introduzca codigo cuenta:");
                    codigo = teclado.nextLine();
                    if (cuenta != null && codigo.equals(cuenta.getCodigo())) {
                        switch (opcion) {
                            case 2 -> {
                                System.out.println("1- Ingresar Dinero");
                                System.out.println("2- Retirar Dinero");
                                System.out.println("3- Consultar saldo");
                                System.out.println("4- Realizar transferencia");
                                System.out.println("5- Consultar movimientos");
                                System.out.println("0- Salir");
                                opcion = teclado.nextInt();
                                teclado.nextLine();
                                
                                switch (opcion) {
                                    case 1 -> {
                                        System.out.println("Introduzca codigo de la cuenta a ingresar:");
                                        codigo = teclado.nextLine();
                                        cuenta = banco.buscarCuenta(codigo);
                                        System.out.println("Introduzca cantidad a ingresar:");
                                        cantidad = teclado.nextFloat();
                                        teclado.nextLine();
                                        cuenta.ingresar(cantidad);
                                        System.out.println("Cantidad ingresada");
                                        System.out.println("Nuevo saldo: " + cuenta.getSaldo());
                                    }
                                    case 2 -> {
                                        System.out.println("Introduzca codigo de la cuenta a reintegrar:");
                                        codigo = teclado.nextLine();
                                        cuenta = banco.buscarCuenta(codigo);
                                        System.out.println("Introduzca cantidad a reintegrar:");
                                        cantidad = teclado.nextFloat();
                                        teclado.nextLine();
                                        try {
                                            cuenta.reintegrar(cantidad);
                                            System.out.println("Cantidad retirada");
                                            System.out.println("Nuevo saldo: " + cuenta.getSaldo());
                                        } catch (SaldoInsuficienteException | IllegalArgumentException ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                    }
                                    case 3 -> {
                                        System.out.println("Saldo: " + cuenta.getSaldo());
                                    }
                                    case 4 -> {
                                        System.out.println("Introduzca codigo de la cuenta a la que quiere hacer la transferencia:");
                                        codigo = teclado.nextLine();
                                        Cuenta cuentad = banco.buscarCuenta(codigo);
                                        System.out.println("Introduzca cantidad a transferir:");
                                        cantidad = teclado.nextFloat();
                                        teclado.nextLine();
                                    try {
                                        cuenta.realizarTransferencia(cuentad, cantidad);
                                    } catch (SaldoInsuficienteException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                        System.out.println("Cantidad transferida");
                                        System.out.println("Nuevo saldo: " + cuenta.getSaldo());
                                    }
                                    case 5 -> {
                                        System.out.println(cuenta.listarMovimientos());
                                    }
                                    case 0 -> {
                                        System.out.println("Adios");
                                    }
                                }
                            }
                            case 3 -> {
                                System.out.println("Introduzca codigo de la cuenta a cancelar");
                                codigo = teclado.nextLine();
                                teclado.nextLine();
                                if (banco.cancelarCuenta(codigo)) {
                                    System.out.println("Cuenta cancelada");
                                } else {
                                    System.out.println("No es posible cancelar la cuenta");
                                }
                            }
                            case 4 -> {
                                System.out.println(banco.getCuentas());
                            }
                            case 5 -> {
                                System.out.println(banco.getTotalDepositos());
                            }
                        }
                    } else {
                        System.out.println("No se puede operar con la cuenta");
                    }
                }
            }
        } while (opcion != 0);
    }
}
