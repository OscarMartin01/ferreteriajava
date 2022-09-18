/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario1
 */
public class CRUDtienda extends Conexion{
    
    //Create --> Insert 
    
    public boolean agregar(Ferreteria objeto) throws ClassNotFoundException{
        //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
        int codigo= objeto.getCodigo();
        String nombre= objeto.getNombre();
        int peso= objeto.getPeso();
        String categoria= objeto.getCategoria();
        double precio= objeto.getPrecio();
        int cantidad= objeto.getCantidad();
        
        String sql= "INSERT INTO objetos (codigo,nombre,peso,precio,categoria,cantidad)"
                + "VALUES(?,?,?,?,?,?)";                                                                                                                      ;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, peso);
            ps.setDouble(4, precio);
            ps.setString(5, categoria);
            ps.setInt(6, cantidad);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
    
    //Read --> Select
    public List listar(){
        List<Ferreteria> datos= new ArrayList<>();
        String sql= "select * from objetos";
        try{
            Connection con=getConexionSinConector();
            PreparedStatement ps= con.prepareStatement(sql);
            //Metodo para capturar resultado de una consulta a la BD
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                /* 1. 1-Halo-50000-Shooters-50
                   2. 3-Batalla Naval-80000-Estrategia-60    
                */
                Ferreteria f= new Ferreteria();
                f.setCodigo(rs.getInt(1));
                f.setNombre(rs.getString(2));
                f.setPeso(rs.getInt(3));
                f.setCategoria(rs.getString(4));
                f.setPrecio(rs.getDouble(5));  
                f.setCantidad(rs.getInt(6));
                datos.add(f);
                //datos= [[1,Halo,50000,Shooters,50],]
            }
               
        }catch(Exception e){
      
        }
        
        return datos;
        
    }
    
    //Update
    
    public boolean actualizar(Ferreteria objeto) throws ClassNotFoundException{
        //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
              
        String sql= "UPDATE objetos SET codigo=?,nombre=?,peso=?, precio=?, categoria=?, cantidad=? where codigo=?";                                                                                                                      ;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.setString(2, objeto.getNombre());
            ps.setInt(3, objeto.getPeso());
            ps.setDouble(4, objeto.getPrecio());
            ps.setString(5, objeto.getCategoria());
            ps.setInt(6, objeto.getCantidad());
            ps.setInt(7, objeto.getCodigo());
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
        //Delete
    
    public boolean borrar(Ferreteria ferr) throws ClassNotFoundException{
        //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
              
        String sql= "DELETE FROM objetos WHERE codigo=?";                                                                                                                      ;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, ferr.getCodigo());
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean buscar(Ferreteria f) throws SQLException{
        
        PreparedStatement ps= null;
        ResultSet rs= null;
        Connection con = getConexionConConector();
        
        String sql= "SELECT * FROM objetos WHERE codigo=?";
        
        
        try{
             ps= con.prepareStatement(sql);
             ps.setInt(1, f.getCodigo());
             rs= ps.executeQuery();
             
             if(rs.next()){
                 f.setCodigo(Integer.parseInt(rs.getString("codigo")));
                 f.setNombre(rs.getString("nombre"));
                 f.setPeso(rs.getInt("peso"));
                 f.setCategoria(rs.getString("categoria"));
                 f.setPrecio(Double.parseDouble(rs.getString("precio")));
                 f.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                 
                 return true;
             }
             return false;
            
            }catch(SQLException e){
               e.printStackTrace();
               return false; 
            } finally{
               try{
                   con.close();
               }catch(SQLException e){
                   e.printStackTrace();
               }
            }
        
        
    }
    
    
    
}
