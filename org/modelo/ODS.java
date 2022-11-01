/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelo;

import config.ConexionDB;
import config.ConexionMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hendr
 */
public class ODS {

    private String tipoCaso;
    private String relacionado;
    private String resumen;
    private String solucion;

    public ODS() {
    }

    public ODS(String tipoCaso, String relacionado, String resumen, String solucion) {
        this.tipoCaso = tipoCaso;
        this.relacionado = relacionado;
        this.resumen = resumen;
        this.solucion = solucion;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
    }

    public String getRelacionado() {
        return relacionado;
    }

    public void setRelacionado(String relacionado) {
        this.relacionado = relacionado;
    }

    public String getResumen() {
        // resumen = "Probando";//org.util.Constantes.conexion.devolverValorString("sol_caso", "ods", "tipo_caso = 1");
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    /*public String resumenes(){
        String resumenes = "probando String externo";
        
        return resumenes;
    }*/
    public ObservableList<String> getODS() {
        ObservableList<String> obs = FXCollections.observableArrayList();
        try {
            //org.util.Constantes.conexion.getconexion();
            org.util.Constantes.conexion.ejecutarConsulta("select * from ods");
            ResultSet rs = org.util.Constantes.conexion.getResultSet();

            while (rs.next()) {
                //int tipoCaso = rs.getInt("tipo_caso");
                //int relacionado = rs.getInt("relacionadoA");
                String resumen = rs.getString("resumen_caso");
                //String solucion = rs.getString("sol_caso");
                //String registro = rs.getString("reg_caso");
                //ODS orden  = new ODS(tipoCaso, relacionado, resumen, solucion, registro);

                //System.out.print(orden.resumen);
                obs.add(resumen);
            }
            // org.util.Constantes.conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ODS.class.getName()).log(Level.SEVERE, null, ex);

        }
        return obs;
    }

    public boolean insertarODS() {
        try {
            //ConexionMySQL conexion = new ConexionMySQL("localhost", "bd_casos", "root", "");

            /*System.out.println("values("
                    + "'" + this.getTipoCaso() + "',"
                    + "'" + this.getRelacionado() + "',"
                    + "'" + this.getResumen() + "',"
                    + "'" + this.getSolucion() + "',"
                    + "'" + this.getRegistro() + "')");*/
            // org.util.Constantes.conexion.ejecutarConsulta("select * from ods");
            String SQL = "INSERT INTO `ods`(`tipo_caso`, `relacionadoA`, `resumen_caso`, `sol_caso`)"
                    + " VALUES ("
                    + "'" + this.getTipoCaso() + "',"
                    + "'" + this.getRelacionado() + "',"
                    + "'" + this.getResumen() + "',"
                    + "'" + this.getSolucion() + "')";

            int filas = org.util.Constantes.conexion.ejecutarInstruccionCommit(SQL, true);

            org.util.Constantes.conexion.cerrarConexion();

            if (filas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            Logger.getLogger(ODS.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
// ConexionMySQL conexion = new ConexionMySQL(resumenes, solucion); 

    public ObservableList<String> setComboResumenes(String tipo_caso, String relacionado) {
        ObservableList<String> obs = FXCollections.observableArrayList();
        String resumen = "";

        try {
            //org.util.Constantes.conexion.getconexion();           
            String SQL = ("select resumen_caso from ods where tipo_caso like '" + tipo_caso + "' and relacionadoA like '"
                    + relacionado + "'");
            org.util.Constantes.conexion.ejecutarConsulta(SQL);
            ResultSet rs = org.util.Constantes.conexion.getResultSet();

            while (rs.next()) {

                this.resumen = rs.getString("resumen_caso");
                resumen = this.resumen;
                System.out.println(resumen);
                obs.add(resumen);
                //ODS orden  = new ODS(tipoCaso, relacionado, resumen, solucion, registro);

                //System.out.print(orden.getRegistro());
            }
            // org.util.Constantes.conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ODS.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return obs;
    }

    public ODS getDatosODS(String resumen) {
        try {
            //org.util.Constantes.conexion.getconexion();
            System.out.println("EL RESUMEN ES " + resumen);
            org.util.Constantes.conexion.ejecutarConsulta("select * from ods where resumen_caso like '" + resumen + "'");
            ResultSet rs = org.util.Constantes.conexion.getResultSet();

            while (rs.next()) {
                this.tipoCaso = rs.getString("tipo_caso");
                this.relacionado = rs.getString("relacionadoA");
                this.solucion = rs.getString("sol_caso");

                //ODS orden  = new ODS(tipoCaso, relacionado, resumen, solucion, registro);
                //System.out.print(orden.getRegistro());
            }
            // org.util.Constantes.conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(ODS.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
        /* org.util.Constantes.conexion.ejecutarConsulta("SELECT `tipo_caso`, `relacionadoA`, `resumen_caso`, `sol_caso`, 
        `reg_caso` FROM `ods` WHERE resumen_caso LIKE 'PROBLEMAS CON MEMORIA RAM'");  */
    }
}
