/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.rankeable.dao;

import br.ufpr.rankeable.jdbc.MysqlConnectionFactory;

import br.ufpr.rankeable.modelo.Topico;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 *
 * @author julio
 */
public class JdbcTopicosDao {
    private Connection connection;

    public JdbcTopicosDao() {
        connection = (new MysqlConnectionFactory()).getConnection();
    }
    
    //metodo que adiciona no banco de dados
    public void adiciona(Topico topico, int id_usuario) {
        String sql = "insert into topicos " + "(descricao, id_usuario) " + "values (?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, topico.getDescricao());    
            stmt.setInt(2, id_usuario);
      
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public List<Topico> lista() {
        String sql = "select * from topicos";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Topico> topicos = new ArrayList<Topico>();
            while (rs.next()) {
                Topico topico = new Topico();
                topico.setId(rs.getInt("id"));
                topico.setDescricao(rs.getString("descricao"));                      
                topico.setId_Usuario(rs.getInt("id_usuario"));
                topicos.add(topico);
            }
            stmt.close();
            return topicos;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
        public List<Topico> getLista() {
        String sql = "select * from topicos";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Topico> topicos = new ArrayList<Topico>();
            while (rs.next()) {
                Topico topico = new Topico();
                topico.setId(rs.getInt("id"));
                topico.setDescricao(rs.getString("descricao"));                             
                topico.setId_Usuario(rs.getInt("id_usuario"));

               topicos.add(topico);
            }
            stmt.close();
            return topicos;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
         public Topico buscaPorId(int id) {
        String sql = "select * from topicos where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
                Topico topico = new Topico();
            if (rs.next()) {                
                topico.setId(rs.getInt("id"));
                //comentario.setId_topico(rs.getInt("id_topico"));
                topico.setDescricao(rs.getString("descricao"));
               
            }
            stmt.close();
            return topico;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void altera(Topico topico) {
         String sql = "update topicos set descricao=?"+
                 " where id = "+topico.getId();        
         try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, topico.getDescricao());
           
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
         
    }
    
     public void remove(Topico topico) {
        String sql = "delete from topicos where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, topico.getId());

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
