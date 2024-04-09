package model;

import database.ConfigDB;
import entity.Coder;
import interfaces.CRUD;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class CoderModel implements CRUD {

    @Override
    public ArrayList<Object> listar() {
        ArrayList<Object> listaDeCoder = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM coder";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()) {
                Coder objCoder = new Coder();
                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setDocumento(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));

                listaDeCoder.add(objCoder);
            }
        } catch (SQLException e) {
            System.out.println("Error en lista coder model" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listaDeCoder;
    }

    @Override
    public Object create(Object obj) {
        Coder objCoder = (Coder) obj;
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "INSERT INTO coder (nombre, apellido, documento, cohorte, cv) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, (objCoder.getApellido()));
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());

            objPrepare.execute();

            ResultSet generatedKeys = objPrepare.getGeneratedKeys();

            while (generatedKeys.next()) {
                objCoder.setId_coder(generatedKeys.getInt(1));
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en modelo compra crear " + e.getMessage());
        }
        ConfigDB.closeConnection();
        return objCoder;
    }

    @Override
    public boolean update(Object obj) {
        Coder objCoder = (Coder) obj;
        Connection objConnection = ConfigDB.openConnection();

        boolean isUpdate = false;

        try {
            String sql = "UPDATE coder SET nombre = ?, apellido = ?, documento = ?, cohorte = ?, cv = ? WHERE id_coder = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setString(1, objCoder.getNombre());
            objPrepare.setString(2, objCoder.getApellido());
            objPrepare.setString(3, objCoder.getDocumento());
            objPrepare.setInt(4, objCoder.getCohorte());
            objPrepare.setString(5, objCoder.getCv());
            objPrepare.setInt(6, objCoder.getId_coder());


            int filasAfectadas = objPrepare.executeUpdate();

            if (filasAfectadas > 0) {
                isUpdate = true;
                JOptionPane.showMessageDialog(null, "El coder fue actualizado exitosamente");
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en coderModel update"
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
            String sql = "DELETE FROM coder WHERE id_coder = ?";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, (Integer) obj);

            int filasAfectadas =  objPrepare.executeUpdate();

            if (filasAfectadas >0){
                JOptionPane.showMessageDialog(null, "El coder fue eliminado exitosamente");
                isDelete = true;
            }

        } catch (SQLException e) {
            System.out.println("Error en modelCoderDelete" + e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDelete;
    }

    public Object findByID(int id_coder){
        Coder objCoder = new Coder();
        Connection objConnection = ConfigDB.openConnection();

        try {
            String sql = "SELECT * FROM coder WHERE id_coder = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, id_coder);

            ResultSet objResult =  objPrepare.executeQuery();

            while (objResult.next()){
                objCoder.setId_coder(objResult.getInt("id_coder"));
                objCoder.setNombre(objResult.getString("nombre"));
                objCoder.setApellido(objResult.getString("apellido"));
                objCoder.setApellido(objResult.getString("documento"));
                objCoder.setCohorte(objResult.getInt("cohorte"));
                objCoder.setCv(objResult.getString("cv"));
            }
        } catch (SQLException e) {
            System.out.println("Error en model coder findById: " + e.getMessage());
        }

        ConfigDB.closeConnection();
        return objCoder;
    }
}
