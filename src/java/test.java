
import DAO.DAOPersistencia;
import java.util.ArrayList;
import objetosNegocio.Material;
import objetosNegocio.Producto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eliu
 */
public class test {
    public static void main(String[] args) {
        DAOPersistencia p=new DAOPersistencia();
      
        ArrayList<Material> listP=p.consultarInventarioMateriales();
        for (Material material : listP) {
            System.out.println(material);
        }
    }
}
