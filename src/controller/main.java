/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.CRUDtienda;
import model.Ferreteria;
import view.tienda;

/**
 *
 * @author usuario1
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CRUDtienda modelCRUD= new CRUDtienda();
        Ferreteria modelVideojuego= new Ferreteria();
        tienda viewTienda = new tienda();
        Ferreteria_Controller controlJuego= new Ferreteria_Controller(viewTienda,modelVideojuego,modelCRUD);
        controlJuego.iniciar();
        viewTienda.setVisible(true);
    }   
    
}
