/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.util;

import config.ConexionMySQL;

/**
 *
 * @author hendr
 */
public class Constantes {
    //ruta a la base de datos
    public static ConexionMySQL conexion = new ConexionMySQL("localhost", "bd_casos", "root", "");
}
