/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.rankeable.dao;

import br.ufpr.rankeable.jdbc.MysqlConnectionFactory;
import br.ufpr.rankeable.modelo.Comentarios;
import br.ufpr.rankeable.modelo.Topico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class JdbcComentariosDao {
     private Connection connection;

    public JdbcComentariosDao() {
        connection = (new MysqlConnectionFactory()).getConnection();
    }
    
    //metodo que adiciona no banco de dados
    public void adiciona(Comentarios comentario, int id_topico, int id_usuario) {
        String sql = "insert into comentarios " + "(comentario, id_usuario, id_topico) " + "values (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, comentario.getComentario()); 
            stmt.setInt(2, id_usuario);
            stmt.setInt(3, comentario.getId_topico());
            
           
//            stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis()));
            // executa
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
     public List<Comentarios> lista(Topico topico) {
        String sql = "select * from comentarios where id_topico = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Comentarios> comentarios = new ArrayList<Comentarios>();
            while (rs.next()) {
                Comentarios comentario = new Comentarios();
                comentario.setId_topico(rs.getInt("id_topico"));
                comentario.setComentario(rs.getString("comentario"));                      
              
                comentarios.add(comentario);
            }
            stmt.close();
            return comentarios;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
        public List<Comentarios> getLista(Topico topico){
        String sql = "select * from comentarios where id_topico = ? ";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, topico.getId());
            ResultSet rs = stmt.executeQuery();
            List<Comentarios> comentarios = new ArrayList<Comentarios>();
            while (rs.next()) {
                Comentarios comentario = new Comentarios();
                comentario.setId_topico(rs.getInt("id_topico"));
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));                         
                comentario.setId_Usuario(rs.getInt("id_usuario"));
                comentarios.add(comentario);
            }
            stmt.close();;
            return comentarios;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }       
    

    }
         public Comentarios buscaPorId(int id) {
        String sql = "select * from comentarios where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
                Comentarios comentario = new Comentarios();
            if (rs.next()) {                
                comentario.setId(rs.getInt("id"));
                comentario.setId_topico(rs.getInt("id_topico"));
                comentario.setComentario(rs.getString("comentario"));
               
            }
            stmt.close();
            return comentario;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void altera(Comentarios comentario) {
         String sql = "update comentarios set comentario=?"+
                 " where id = "+comentario.getId();        
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, comentario.getComentario());
           
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
        
        public void remove(Comentarios comentario) {
        String sql = "delete from comentarios where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, comentario.getId());

            // executa
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    
}
