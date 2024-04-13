package controller;

import entity.Vacante;
import model.CoderModel;
import model.VacanteModel;

import javax.swing.*;
import java.math.BigDecimal;


public class VacanteController {
    public static void crear() {
        String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante");
        String duracion = JOptionPane.showInputDialog("Ingrese la duracion de la vacante");
        int id_empresa = Integer.parseInt(JOptionPane.showInputDialog(EmpresaController.listarString() + "Ingrese el id de la empresa que ofrece esta vacante"));

        String estado = JOptionPane.showInputDialog("""
                Ingrese el estado de la vacante
                1 - Activa
                2 - Inactiva                   
                """);
        switch (estado){
            case "1":
                estadoUsuario = (estado);
                break;
        }

        instanceModel().create(new Vacante(titulo, descripcion, duracion, estado, id_empresa));
        JOptionPane.showMessageDialog(null, "La vacante fue creada exitosamente");
    }

    public static String listarString() {
        String listaDeVacantes = "LISTA DE VACANTES \n";
        for (Object vacante : instanceModel().listar()) {
            listaDeVacantes += (Vacante) vacante + "\n";
        }
        return listaDeVacantes;
    }

    public static void listar() {
        JOptionPane.showMessageDialog(null, listarString());
    }

    public  static void delete(){
        int id_vacante = Integer.parseInt(JOptionPane.showInputDialog(listarString() + "\nIngrese el id de la vacante que desea eliminar"));
        instanceModel().delete(id_vacante);
    }

    
    public static void update(){
        int id_vacante = Integer.parseInt(JOptionPane.showInputDialog(listarString() + "\nIngrese el id del producto que desea actualizar"));
        Vacante objVacante = (Vacante) instanceModel().findByID(id_vacante);

        String titulo = JOptionPane.showInputDialog("Ingrese el titulo de la vacante", objVacante.getTitulo());
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la vacante", objVacante.getDescripcion());
        String duracion = JOptionPane.showInputDialog("Ingrese la duracion de la vacante", objVacante.getDescripcion());
        String estado = JOptionPane.showInputDialog("Ingrese el estado de la vacante", objVacante.getDescripcion());

        int empresa_id  = Integer.parseInt(JOptionPane.showInputDialog(EmpresaController.listarString() + "Ingrese el id de la empresa en la que desea agregar la vacante", objVacante.getId_empresa()));

        instanceModel().update(new Vacante(id_vacante, titulo, descripcion, duracion, estado, empresa_id));
    }


    public static VacanteModel instanceModel() {
        return new VacanteModel();
    }
}
