import controller.CoderController;
import controller.VacanteController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int opcion = 0, opcionCoder = 0, opcionVacante = 0, opcionContratacion = 0;

        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                    Ingrese una opción
                    1 - CRUD CODER
                    2 - CRUD VACANTE
                    3 - CRUD CONTRATACION
                    4 - EXIT
                    """));

            switch (opcion){
                case 1:
                    do {
                        opcionCoder = Integer.parseInt(JOptionPane.showInputDialog("""
                                1 - Crear tienda
                                2 - Eliminar tienda 
                                3 - Actualizar tienda
                                4 - Listar tienda
                                5 - Salir 
                                """));

                        switch (opcionCoder){
                            case 1:
                                CoderController.create();
                                break;
                            case 2:
                                CoderController.delete();
                                break;
                            case 3:
                                CoderController.update();
                                break;
                            case 4:
                                CoderController.listar();
                                break;
                        }
                    }while (opcionCoder != 5);

                    break;

                case 2:
                    do {
                        opcionVacante = Integer.parseInt(JOptionPane.showInputDialog("""
                                1 - Crear vacante
                                2 - Eliminar vacante 
                                3 - Actualizar vacante
                                4 - Listar vacante
                                5 - Salir
                                """));

                        switch (opcionVacante){
                            case 1:
                                VacanteController.crear();
                                break;
                            case 2:
                                VacanteController.delete();
                                break;
                            case 3:
                                VacanteController.update();
                                break;
                            case 4:
                                VacanteController.listar();
                                break;
                        }
                    }while (opcionVacante != 6);
                    break;

                case 3:
                    do {
                        opcionContratacion = Integer.parseInt(JOptionPane.showInputDialog("""
                                1 - Crear producto
                                2 - Eliminar producto 
                                3 - Actualizar producto
                                4 - Listar producto
                                5 - Salir 
                                 """));

                        switch (opcionContratacion){
                            case 1:
                                ControllerProducto.crear();
                                break;
                            case 2:
                                ControllerProducto.delete();
                                break;
                            case 3:
                                ControllerProducto.update();
                                break;
                            case 4:
                                ControllerProducto.listar();
                                break;
                        }

                    }while (opcionContratacion != 5);
                    break;


            }


        }while (opcion != 5);
    }
}