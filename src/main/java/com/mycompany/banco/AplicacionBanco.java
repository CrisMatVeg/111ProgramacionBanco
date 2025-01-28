/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.banco;

import java.util.Scanner;

/**
 *
 * @author cristian.matveg
 */
public class AplicacionBanco {

    public static void main(String[] args) {
        Cuenta cuenta = null;
        Banco banco = null;
        int opcion;
        String codigo, titular;
        float saldo, cantidad;
        Scanner teclado = new Scanner(System.in);
        do {
            System.out.println("1- Abrir Cuenta");
            System.out.println("2- Operar con cuenta");
            System.out.println("3- Cancelar Cuenta");
            System.out.println("4- Listar cuentas");
            System.out.println("5- Consultar total depositos");
            System.out.println("0- Salir");
            opcion = teclado.nextInt();
            teclado.nextLine();
            if (opcion >= 1 && opcion <= 5) {
                if (opcion == 1) {
                    System.out.println("Introduzca codigo cuenta:");
                    codigo = teclado.nextLine();
                    System.out.println("Introduzca titular cuenta:");
                    titular = teclado.nextLine();
                    System.out.println("Introduzca saldo cuenta:");
                    saldo = teclado.nextFloat();
                    teclado.nextLine();
                    cuenta = new Cuenta(codigo, titular, saldo);
                    if (banco.abrirCuenta(cuenta)) {
                        System.out.println("Cuenta creada con éxito" + cuenta);
                    } else {
                        System.out.println("No se puede abrir la cuenta");
                    }
                }
            } else {
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
                                    cuenta.reintegrar(cantidad);
                                    System.out.println("Cantidad retirada");
                                    System.out.println("Nuevo saldo: " + cuenta.getSaldo());
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
                                    cuenta.realizarTransferencia(cuentad, cantidad);
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
        } while (opcion != 0);
    }
}
