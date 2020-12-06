/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mysql.cj.xdevapi.Statement;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import objetosNegocio.Analisis;
import objetosNegocio.Material;
import objetosNegocio.Producto;
import objetosNegocio.Reactivo;
import objetosNegocio.ReqMaterial;
import objetosNegocio.ReqReactivo;

/**
 *
 * @author eliu
 */
public class DAOPersistencia implements interfaces.IPersistencia {

    private Connection conn;

    public DAOPersistencia() {
    }

    public boolean existe(Producto producto) throws PersistenciaException {
        Producto p = obten(producto);
        boolean res = (p == null) ? false : true;
        return res;
    }

    @Override
    public Producto obten(Producto producto) throws PersistenciaException {
        String query = "SELECT * FROM laboratorio.productos WHERE productos.clave= ?";
        conn = Conexion.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Producto p = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, producto.getClave());
            rs = ps.executeQuery();

            while (rs.next()) {
                p = new Producto(rs.getString("clave"), rs.getString("nombre"), rs.getString("unidad"));
            }

            conn.close();
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {

            }
            ex.printStackTrace();
        }

        return p;
    }

    @Override
    public void agregar(Producto producto) throws PersistenciaException {
        String query = "INSERT INTO laboratorio.productos (clave, nombre, unidad) VALUES (?, ?, ?)";
        conn = Conexion.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, producto.getClave());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getUnidad());
            ps.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void actualizar(Producto producto) throws PersistenciaException {
        String query = "UPDATE laboratorio.productos SET nombre = ?, unidad = ? WHERE (clave = ?)";
        conn = Conexion.getConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getUnidad());
            ps.setString(3, producto.getClave());

            ps.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(Producto producto) throws PersistenciaException {
        String query = "DELETE FROM laboratorio.productos WHERE (clave = ?)";
        conn = Conexion.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, producto.getClave());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Producto> consultarProductos() throws PersistenciaException {
        String query = "SELECT * FROM laboratorio.productos";
        conn = Conexion.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Producto> listP = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String c = rs.getString("clave");
                String n = rs.getString("nombre");
                String u = rs.getString("unidad");
                listP.add(new Producto(c, n, u));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listP;
    }

    @Override
    public void inventariar(Material material) throws PersistenciaException {
        String query = "INSERT INTO laboratorio.materiales (clave, cantidad) VALUES (?, ?);";
        String querySelect = "SELECT productos.clave , productos.nombre , productos.unidad , "
                + "materiales.cantidad FROM productos INNER JOIN materiales "
                + "ON productos.clave= materiales.clave WHERE productos.clave = ?";
        String queryUpdate = "UPDATE laboratorio.materiales SET cantidad = ? WHERE (`clave` = ?)";
        conn = Conexion.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Material m = null;
        try {

            ps = conn.prepareStatement(querySelect);
            ps.setString(1, material.getClave());
            rs = ps.executeQuery();

            while (rs.next()) {
                m = new Material(new Producto(rs.getString("clave"), rs.getString("nombre"), rs.getString("unidad")), rs.getInt("cantidad"));
            }

            if (m == null) { //Agrega si no existe el material
                ps = conn.prepareStatement(query);
                ps.setString(1, material.getClave());
                ps.setInt(2, material.getCantidad());
                ps.executeUpdate();
            } else { //Actualiza si el material existe para anadir el la cantidad
                int suma = material.getCantidad() + m.getCantidad();
                ps = conn.prepareStatement(queryUpdate);
                ps.setInt(1, suma);
                ps.setString(2, m.getClave());
                ps.executeUpdate();

            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void desinventariar(Material material) throws PersistenciaException {
        String querySelect = "SELECT productos.clave , productos.nombre , productos.unidad , "
                + "materiales.cantidad FROM productos INNER JOIN materiales "
                + "ON productos.clave= materiales.clave WHERE productos.clave = ?";
        String queryDelete = "DELETE FROM laboratorio.materiales WHERE (clave = ?);";
        String queryUpdate = "UPDATE laboratorio.materiales SET cantidad = ? WHERE (`clave` = ?)";
        conn = Conexion.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        Material m = null;

        try {
            ps = conn.prepareStatement(querySelect);
            ps.setString(1, material.getClave());

            rs = ps.executeQuery();

            while (rs.next()) {
                m = new Material(new Producto(rs.getString("clave"), rs.getString("nombre"), rs.getString("unidad")), rs.getInt("cantidad"));
            }
            int resta = m.getCantidad() - material.getCantidad();
            resta = (resta <= 0) ? 0 : resta;
            m.setCantidad(resta);

            if (resta == 0) {
                ps = conn.prepareStatement(queryDelete);
                ps.setString(1, m.getClave());
                ps.executeUpdate();
            } else {
                ps = conn.prepareStatement(queryUpdate);
                ps.setInt(1, resta);
                ps.setString(2, m.getClave());
                ps.executeUpdate();
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public ArrayList<Material> consultarInventarioMateriales() throws PersistenciaException {
        String query = "SELECT productos.clave , productos.nombre , productos.unidad , materiales.cantidad "
                + "FROM materiales INNER JOIN productos ON productos.clave=materiales.clave";
        conn = Conexion.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Material> listP = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String clave = rs.getString("clave");
                String nombre = rs.getString("nombre");
                String unidad = rs.getString("unidad");
                int cantidad = rs.getInt("cantidad");
                listP.add(new Material(new Producto(clave, nombre, unidad), cantidad));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listP;
    }

    @Override
    public Analisis obten(Analisis analisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregar(Analisis analisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Analisis analisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Analisis analisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReqMaterial obten(ReqMaterial reqMaterial) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregar(ReqMaterial reqMaterial) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ReqMaterial reqMaterial) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(ReqMaterial reqMaterial) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReqReactivo obten(ReqReactivo reqReactivo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void agregar(ReqReactivo reqReactivo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(ReqReactivo reqReactivo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(ReqReactivo reqReactivo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inventariar(Reactivo reactivo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void desinventariar(Reactivo reactivo) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Analisis> consultarAnalisis() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Reactivo> consultarInventarioReactivos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReqMaterial> consultarReqMateriales() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReqMaterial> consultarReqMateriales(Producto producto) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReqMaterial> consultarReqMateriales(Analisis analisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReqReactivo> consultarReqReactivos() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReqReactivo> consultarReqReactivos(Producto producto) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ReqReactivo> consultarReqReactivos(Analisis analisis) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
