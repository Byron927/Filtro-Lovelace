package model;

import database.ConfigDB;
import entity.Empresa;
import entity.Vacante;
import interfaces.CRUD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VacanteModel implements CRUD {

    @Override
    public ArrayList<Object> listar() {
        ArrayList<Object> listaDeVacantes = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM vacante INNER JOIN empresa ON vacante.empresa_id = empresa.id_empresa;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Vacante objVacante = new Vacante();
                objVacante.setId_vacante(objResult.getInt("id_vacante"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setDescripcion(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setDuracion(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("empresa_id"));

                Empresa objEmpresa = new Empresa();
                objEmpresa.setNombre(objResult.getString("nombre"));
                objEmpresa.setSector(objResult.getString("sector"));
                objEmpresa.setUbicacion(objResult.getString("ubicacion"));
                objEmpresa.setContacto(objResult.getString("contacto"));
                objEmpresa.setId_empresa(objResult.getInt("id_empresa"));
                objVacante.setObjEmpresa(objEmpresa);

                listaDeVacantes.add(objVacante);

            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaDeVacantes;

    }

    @Override
    public Object create(Object obj) {
        Vacante objVacante = (Vacante) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO vacante (titulo, descripcion, duracion, estado, empresa_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objVacante.getTitulo());
            objPrepare.setString(2, objVacante.getDescripcion());
            objPrepare.setString(3, objVacante.getDuracion());
            objPrepare.setString(4, objVacante.getEstado());
            objPrepare.setInt(5, objVacante.getId_empresa());

            objPrepare.execute();

            ResultSet generatedKeys = objPrepare.getGeneratedKeys();

            while (generatedKeys.next()) {
                objVacante.setId_vacante(generatedKeys.getInt(1));
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en modelo vacante crear"
                    + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objVacante;
    }

    @Override
    public boolean update(Object obj) {
        Vacante objVacante = (Vacante) obj;
        Connection objConnection = ConfigDB.openConnection();

        Boolean isUpdate = false;

        try {
            String sql = "UPDATE vacante SET titulo = ?, descripcion = ?, duracion = ?, estado = ?, empresa_id = ? WHERE id_vacante = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objVacante.getTitulo());
            objPrepare.setString(2, objVacante.getDescripcion());
            objPrepare.setString(3, objVacante.getDuracion());
            objPrepare.setString(4, objVacante.getEstado());
            objPrepare.setInt(5, objVacante.getId_empresa());
            objPrepare.setInt(6, objVacante.getId_vacante());

            int filasAfectadas =  objPrepare.executeUpdate();

            if (filasAfectadas >0){
                JOptionPane.showMessageDialog(null, "La vacante fue actualizada exitosamente");
                isUpdate = true;
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en vacante model listar"
                    + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isUpdate;
    }

    @Override
    public boolean delete(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Boolean isDelete = false;


        try {
            String sql = "DELETE FROM vacante WHERE id_vacante = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, (Integer) obj);

            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas >0){
                JOptionPane.showMessageDialog(null, "La vacante fue eliminada exitosamente");
                isDelete = true;
            }

        } catch (SQLException e) {
            System.out.println("Error en model vacante eliminar" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public Object findByID(int id_vacante) {
        Vacante objVacante = new Vacante();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM vacante WHERE id_vacante = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id_vacante);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                objVacante.setId_vacante(objResult.getInt("id_vacante"));
                objVacante.setTitulo(objResult.getString("titulo"));
                objVacante.setTitulo(objResult.getString("descripcion"));
                objVacante.setDuracion(objResult.getString("duracion"));
                objVacante.setEstado(objResult.getString("estado"));
                objVacante.setId_empresa(objResult.getInt("empresa_id"));
            }

        } catch (SQLException e) {
            System.out.println("Error en model vacante findById: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objVacante;
    }
}
}
