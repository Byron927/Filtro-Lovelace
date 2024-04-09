package model;

import database.ConfigDB;
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
        return null;
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

            while (generatedKeys.next()){
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
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
