/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.CRUDtienda;
import model.Ferreteria;
import view.tienda;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author usuario1
 */
public class Ferreteria_Controller implements ActionListener{
    //Atributos
    private tienda viewTienda;
    private Ferreteria modelFerreteria;
    private CRUDtienda modelCRUD;
    DefaultTableModel modelo= new DefaultTableModel(); 
    
    //constructor
    public Ferreteria_Controller(tienda viewTienda,Ferreteria modelVideojuego,CRUDtienda modelCRUD){
        this.modelFerreteria= modelVideojuego;
        this.modelCRUD= modelCRUD;
        this.viewTienda= viewTienda;
        this.viewTienda.buttonGuardar.addActionListener(this);
        this.viewTienda.buttonEliminar.addActionListener(this);
        this.viewTienda.buttonModificar.addActionListener(this);
        this.viewTienda.buttonListar.addActionListener(this);
        this.viewTienda.buttonLimpiar.addActionListener(this);
        this.viewTienda.buttonBuscar.addActionListener(this);
    }
    
    //Metodos
    
    public void iniciar(){
        viewTienda.setTitle("Tienda de Videojuegos G4");
        viewTienda.setLocationRelativeTo(null);
        //viewTienda.buttonBuscar.setVisible(false);
    }
    
    @Override 
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==viewTienda.buttonBuscar){
            modelFerreteria.setCodigo(Integer.parseInt(viewTienda.jTextField3.getText()));
            try {
                if(modelCRUD.buscar(modelFerreteria)){
                    viewTienda.jTextFieldCodigo.setText(String.valueOf(modelFerreteria.getCodigo()));
                    viewTienda.jTextFieldNombre.setText(String.valueOf(modelFerreteria.getNombre()));
                    viewTienda.jTextFieldPeso.setText(String.valueOf(modelFerreteria.getPeso()));
                    viewTienda.jComboBoxCategoria.setSelectedItem(String.valueOf(modelFerreteria.getCategoria()));
                    viewTienda.jTextFieldPrecio.setText(String.valueOf(modelFerreteria.getPrecio()));
                    viewTienda.jSpinner1.setValue(modelFerreteria.getCantidad());
                    
                }else{
                    JOptionPane.showMessageDialog(null, "No hay registro con ese código");
                    limpiar();
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else if(e.getSource()==viewTienda.buttonEliminar){
            modelFerreteria.setCodigo(Integer.parseInt(viewTienda.jTextFieldCodigo.getText()));
            
            try{
                if(modelCRUD.borrar(modelFerreteria)){
                    JOptionPane.showMessageDialog(null,"Se eliminó el producto");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al eliminar"); 
                }
            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(e.getSource()==viewTienda.buttonModificar){
            modelFerreteria.setCodigo(Integer.parseInt(viewTienda.jTextFieldCodigo.getText()));
            modelFerreteria.setNombre(viewTienda.jTextFieldNombre.getText());
            modelFerreteria.setPeso(Integer.parseInt(viewTienda.jTextFieldPeso.getText()));
            modelFerreteria.setCategoria((String)viewTienda.jComboBoxCategoria.getSelectedItem());
            modelFerreteria.setPrecio(Double.parseDouble(viewTienda.jTextFieldPrecio.getText()));
            modelFerreteria.setCantidad(Integer.parseInt(viewTienda.jSpinner1.getValue().toString()));
            
            try{
               if(modelCRUD.actualizar(modelFerreteria)){
                    JOptionPane.showMessageDialog(null,"Se Actualizó el producto");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al actualizar"); 
                }
               } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }else if(e.getSource()==viewTienda.buttonGuardar){
            modelFerreteria.setCodigo(Integer.parseInt(viewTienda.jTextFieldCodigo.getText()));
            modelFerreteria.setNombre(viewTienda.jTextFieldNombre.getText());
            modelFerreteria.setPeso(Integer.parseInt(viewTienda.jTextFieldPeso.getText()));
            modelFerreteria.setCategoria((String)viewTienda.jComboBoxCategoria.getSelectedItem());
            modelFerreteria.setPrecio(Double.parseDouble(viewTienda.jTextFieldPrecio.getText()));
            modelFerreteria.setCantidad(Integer.parseInt(viewTienda.jSpinner1.getValue().toString()));
            
            try{
                if(modelCRUD.agregar(modelFerreteria)){
                   JOptionPane.showMessageDialog(null,"Se guardo el producto");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al guardar"); 
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Ferreteria_Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else if(e.getSource()==viewTienda.buttonListar){  
            listarTabla(viewTienda.jTable1);
        }else if(e.getSource()==viewTienda.buttonLimpiar){
            limpiar();
        }
    }
    
    public void listarTabla(JTable tabla){
        modelo= (DefaultTableModel)tabla.getModel();
        List<Ferreteria> lista= modelCRUD.listar();
        Object[] objeto= new Object[6];
        
        for(int i=0; i<lista.size();i++){
            objeto[0]= lista.get(i).getCodigo();
            objeto[1]= lista.get(i).getNombre();
            objeto[2]= lista.get(i).getPeso()+" g";
            objeto[3]= lista.get(i).getCategoria();
            objeto[4]= "$ "+lista.get(i).getPrecio();
            objeto[5]= lista.get(i).getCantidad();   
            
            modelo.addRow(objeto);
        }
        viewTienda.jTable1.setModel(modelo);
        viewTienda.buttonListar.setVisible(false);
        
        
        
    }
    
    public void limpiar(){
    viewTienda.jTextFieldCodigo.setText(null);
    viewTienda.jTextFieldNombre.setText(null);
    viewTienda.jTextFieldPeso.setText(null);
    viewTienda.jComboBoxCategoria.setSelectedItem("Cerrajería");
    viewTienda.jTextFieldPrecio.setText(null);
    viewTienda.jSpinner1.setValue(1);
    viewTienda.jTextField3.setText(null);
    
    DefaultTableModel tb= (DefaultTableModel)viewTienda.jTable1.getModel();
    int a= viewTienda.jTable1.getRowCount()-1;
    for(int i=a; i>=0; i--){
        tb.removeRow(tb.getRowCount()-1);
    }
        viewTienda.buttonListar.setVisible(true);
        
    }

    
}
