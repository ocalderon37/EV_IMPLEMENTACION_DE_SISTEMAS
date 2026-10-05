package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import entity.Proveedor;
import util.MySqlDBConexion;

public class ProveedorModel {

    public int insertaProveedor(Proveedor obj) {
        int insertados = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = MySqlDBConexion.getConexion();
            
            
            String sql = "INSERT INTO proveedor (nombre, ruc, email, telefono, direccion, "
            		+ "contacto, paginaWeb, fechaRegistro, idTipoProveedor) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE(), ?)";
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, obj.getNombre());
            pstm.setString(2, obj.getRuc());
            pstm.setString(3, obj.getEmail());
            pstm.setString(4, obj.getTelefono());
            pstm.setString(5, obj.getDireccion());
            pstm.setString(6, obj.getContacto());
            pstm.setString(7, obj.getPaginaWeb());
            pstm.setInt(8, obj.getTipoProveedor().getIdTipoProveedor()); // Corresponde al 9no '?' (el 8vo es CURDATE())
            
            System.out.println("SQL: " + pstm.toString());
            
            insertados = pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return insertados;
    }
}