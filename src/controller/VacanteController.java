package controller;

import entity.Vacante;

import javax.swing.*;

import static controller.CoderController.instanceModel;

public class VacanteController {
    public static void crear(){
        String titulo = JOptionPane.showInputDialog( "Ingrese el titulo de la vacante");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante");
        String duracion = JOptionPane.showInputDialog("Ingrese la duracion de la vacante");
        String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante");
        int id_empresa  = Integer.parseInt(JOptionPane.showInputDialog(EmpresaController.listarString() + "Ingrese el id de la empresa que ofrece esta vacante"));

        instanceModel().create(new Vacante(titulo, descripcion, duracion, estado, id_empresa));
        JOptionPane.showMessageDialog(null, "La vacante fue creada exitosamente");
    }
}
