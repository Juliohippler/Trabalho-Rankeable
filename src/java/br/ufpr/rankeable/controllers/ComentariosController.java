/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.rankeable.controllers;

import br.ufpr.rankeable.modelo.Comentarios;
import br.ufpr.rankeable.dao.JdbcComentariosDao;
import br.ufpr.rankeable.modelo.Topico;
import br.ufpr.rankeable.modelo.Usuario;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author julio
 */

@Controller
 @SessionAttributes("usuario")
public class ComentariosController {
    
     @RequestMapping("/novoComentario")
    public String form(Topico topico, Usuario usuario,  Model model){
        model.addAttribute("topico", topico); // passa o topico para frente 
        model.addAttribute("usuario", usuario);
        return "comentario/formulario";
    }

    @RequestMapping("/adicionaComentario")
    public String adicionar(Usuario usuario, Comentarios comentario, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "comentario/formulario";
        }
        JdbcComentariosDao dao = new JdbcComentariosDao();
      
        dao.adiciona(comentario, comentario.getId_topico(), usuario.getId() );
        return "redirect:listaComentarios?id_topico="+comentario.getId_topico();
    }  
    
    /**
     *
     * @param topico
     * @param model
     * @return
     */
    @RequestMapping("/listaComentarios")
    public String listar(Topico topico, Model model) {
        JdbcComentariosDao dao = new JdbcComentariosDao();
        List<Comentarios> comentarios = dao.getLista(topico);        
        model.addAttribute("comentarios",comentarios);
        return "comentario/lista";
    }
    
      @RequestMapping("/removeComentario")
    public String remover(Comentarios comentario) {
        JdbcComentariosDao dao = new JdbcComentariosDao();
        dao.remove(comentario);       
        return "redirect:listaComentarios?id_topico="+comentario.getId_topico();
    }
    
       @RequestMapping("mostraComentario")
    public String mostra(Comentarios comentario, Model model){
        JdbcComentariosDao dao = new JdbcComentariosDao();
        model.addAttribute("comentario",dao.buscaPorId(comentario.getId()));
        return "comentario/mostra";
    }
    
    @RequestMapping("/alteraComentario")
    public String altera(Comentarios comentario) {
        JdbcComentariosDao dao = new JdbcComentariosDao();
        dao.altera(comentario);
        return "redirect:listaComentarios?id_topico"+comentario.getId_topico();
    }
    
     
}
