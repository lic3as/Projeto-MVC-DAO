package DAO;

import Factory.ConnectionFactory;
import java.sql.*;
import Modelo.Usuario;
import java.util.*;
import javax.swing.*;

public class UsuarioDAO { 
   private Connection connection;
   public UsuarioDAO(){ 
        this.connection = new ConnectionFactory().getConnection();
    } 
     public void AdicionaUsuario(Usuario usuario){
    
        String sql = "INSERT INTO usuario (nome, cpf, email, telefone) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public List<Usuario> ListaUsuarios(){
    
        String sql = "SELECT * FROM usuario ORDER BY nome";
        List<Usuario> lista = new ArrayList<>();
        
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                  Usuario u = new Usuario();
                  u.setId(rs.getInt(1));                  
                  u.setNome(rs.getString(2));

                  lista.add(u);
                }
                return lista;
            }else{
                return null;
            }
            
        }catch(Exception ex){
           return null;
        }
    }
    public String ExcluirUsuario(Usuario u){
        String sql = "DELETE FROM usuario WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, u.getId());
            if(stmt.executeUpdate() > 0){
                return "Excluido com sucesso!";
            }else{
                return "Erro ao excluir!";
            }
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
    public String AlteraUsuario(Usuario u){
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, telefone = ? WHERE id = ?";
        
        try{
           PreparedStatement stmt = connection.prepareStatement(sql);
           
           stmt.setString(1, u.getNome());
           stmt.setString(2, u.getCpf());
           stmt.setString(3, u.getEmail());
           stmt.setString(4, u.getTelefone());
           stmt.setInt(5, u.getId());
           
           if(stmt.executeUpdate() > 0){
                return "Atualizado com sucesso!";
            }else{
                return "Erro ao Atualizar!";
            }
        }catch(SQLException ex){
            return ex.getMessage();
        }
    }
    public boolean TestaIdUsuario(int id){
        boolean resultado = false;
        String sql = "SELECT * FROM usuario WHERE id = "+id;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   resultado = true;     
                }
            }

        } catch (SQLException ex) {
            ex.getMessage();
        }
        return resultado;
    } 
    public List<Usuario> ListaUsuario(int id){
    
        String sql = "SELECT * FROM usuario WHERE id = "+id;
        List<Usuario> lista = new ArrayList<>();
        
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Usuario u = new Usuario();
                    
                    u.setNome(rs.getString(2));
                    u.setCpf(rs.getString(3));
                    u.setEmail(rs.getString(4));
                    u.setTelefone(rs.getString(5));
                    
                    lista.add(u);
                }
                return lista;
            }else{
               return null; 
            }
        } catch (SQLException ex) {
           return null;
        }
    }
}