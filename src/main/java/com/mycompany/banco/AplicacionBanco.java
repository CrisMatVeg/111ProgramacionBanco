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
        Cuenta cuenta=null;
        int opcion;
        String codigo,titular;
        float saldo, cantidad; 
        Scanner teclado=new Scanner(System.in);
        do{
            System.out.println("1- Abrir Cuenta");
            System.out.println("2- Ingresar Dinero");
            System.out.println("3- Retirar Dinero");
            System.out.println("4- Consultar Saldo");
            System.out.println("5- Cancelar Cuenta");
            System.out.println("0- Salir");  
            opcion=teclado.nextInt();
            teclado.nextLine();
            if(opcion>=1 && opcion <=5){
                System.out.println("Introduzca codigo cuenta:");
                codigo=teclado.nextLine();
                if(opcion==1){
                    if(cuenta==null){
                        System.out.println("Introduzca titular cuenta:");
                        titular=teclado.nextLine();
                        System.out.println("Introduzca saldo cuenta:");
                        saldo=teclado.nextFloat();
                        teclado.nextLine();
                        cuenta=new Cuenta(codigo,titular,saldo);
                        System.out.println("Cuenta creada con éxito"+cuenta);
                    }else{
                        System.out.println("No se puede abrir la cuenta");
                    }
                }
            }else{
                codigo=teclado.nextLine();
                if(cuenta!=null && codigo.equals(cuenta.getCodigo())){
                    switch(opcion){
                        case 2 -> {
                            System.out.println("Introduzca cantidad a ingresar:");
                            cantidad=teclado.nextFloat();
                            teclado.nextLine();
                            cuenta.ingresar(cantidad);
                            System.out.println("Cantidad ingresada");
                            System.out.println("Nuevo saldo: "+cuenta.getSaldo());
                        }
                        case 3 -> {

                            System.out.println("Introduzca cantidad a retirar:");
                            cantidad=teclado.nextFloat();
                            teclado.nextLine();
                            cuenta.reintegrar(cantidad);
                            System.out.println("Cantidad retirada");
                            System.out.println("Nuevo saldo: "+cuenta.getSaldo());

                        }
                        case 4 -> {
                            System.out.println(cuenta.getSaldo());   
                        }
                        case 5 -> {
                            cuenta=null;
                            System.out.println("Cuenta cancelada correctamente");
                        }
                    }
                }else{
                    System.out.println("No se puede operar con la cuenta");
                }
            }
        }while(opcion!=0);
    }
}
