package controller;

import entity.Empresa;
import model.EmpresaModel;

import javax.swing.*;

public class EmpresaController {

    public static String listarString() {
        String listaDeEmpresas = "LISTA DE EMPRESAS \n";
        for (Object empresa : instanceModel().listar()) {
            listaDeEmpresas += (Empresa) empresa + "\n";
        }
        return listaDeEmpresas;
    }

    public static void listar() {
        JOptionPane.showMessageDialog(null, listarString());
    }

    public static EmpresaModel instanceModel() {
        return new EmpresaModel();
    }
}
