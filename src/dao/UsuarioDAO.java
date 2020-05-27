package dao;

import factory.ConnectionFactory;
import modelo.Usuario;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO { 
   private Connection connection;
   public UsuarioDAO(){ 
        this.connection = new ConnectionFactory().getConnection();
    } 
    public void adiciona(Usuario usuario){ 
        String sql = "INSERT INTO usuario(nome,cpf,email,telefone) VALUES(?,?,?,?)";
        try { 
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
            stmt.execute();
            stmt.close();
        } 
        catch (SQLException u) { 
            throw new RuntimeException(u);
        } 
        
    } 
    public List<Usuario> ListaUsuario(){
        String sql = "SELECT * FROM usuario ORDER BY nome";
        List<Usuario> lista = new ArrayList<>();
            try{
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                if(rs != null){
                    while(rs.next()){
                    Usuario u = new Usuario();
                    u.setId(rs.getLong(1)); 
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
           stmt.setLong(1, u.getId());
           
           if(stmt.executeUpdate()>0){
           return "Excluído com sucesso!";
           }else{
               return "Erro ao exluir!";
           }
       } catch (SQLException ex) {
           return "Erro ao excluir.";
       }
        
    }
    }
