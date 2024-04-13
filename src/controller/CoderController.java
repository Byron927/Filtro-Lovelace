package controller;

import entity.Coder;
import model.CoderModel;

import javax.swing.*;

public class CoderController {
    public static void create() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del coder");
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del coder");
        String documento = JOptionPane.showInputDialog("Ingrese el documento del coder");
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte del coder"));
        String cv = JOptionPane.showInputDialog("Ingrese la cv del coder");

        instanceModel().create(new Coder(nombre, apellido, documento, cohorte, cv));
        JOptionPane.showMessageDialog(null, "El coder fue creado exitosamente");
    }

    public static String listarString() {
        String listaDeCoder = "LISTA DE CODERS \n";
        for (Object coder : instanceModel().listar()) {
            listaDeCoder += (Coder) coder + "\n";
        }
        return listaDeCoder;
    }

    public static void listar() {
        JOptionPane.showMessageDialog(null, listarString());
    }

    public static void update() {
        int id_coder = Integer.parseInt(JOptionPane.showInputDialog(listarString() + "\nIngrese el id del coder que desea actualizar"));
        Coder objCoder = (Coder) instanceModel().findByID(id_coder);

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del coder", objCoder.getNombre());
        String apellido = JOptionPane.showInputDialog("Ingrese el apellido del coder", objCoder.getApellido());
        String documento = JOptionPane.showInputDialog("Ingrese el documento del coder", objCoder.getDocumento());
        int cohorte = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cohorte del coder", objCoder.getCohorte()));
        String cv = JOptionPane.showInputDialog("Ingrese la cv del coder", objCoder.getCv());

        instanceModel().update(new Coder(id_coder, nombre, apellido, documento, cohorte, cv));
    }

    public static void delete() {
        int id_coder = Integer.parseInt(JOptionPane.showInputDialog(listarString() + "\nIngrese el id del coder que desea eliminar"));
        instanceModel().delete(id_coder);
    }

    public static CoderModel instanceModel() {

        return new CoderModel();
    }
}
