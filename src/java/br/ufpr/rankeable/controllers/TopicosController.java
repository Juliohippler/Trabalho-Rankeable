/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.rankeable.controllers;


import br.ufpr.rankeable.dao.JdbcTopicosDao;

import br.ufpr.rankeable.modelo.Topico;
import br.ufpr.rankeable.modelo.Usuario;
import java.io.IOException;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author julio
 */
@Controller
 @SessionAttributes("usuario")
public class TopicosController {
  //  @ModelAttribute("usuario")
    
    @RequestMapping("/novoTopico")
    public String form(Usuario usuario, Model model){
        model.addAttribute("usuario", usuario);
       return "topicos/formulario";
    }
    
    @RequestMapping("/adicionaTopico")
    public String adicionar(Usuario usuario, Topico topico, BindingResult result, HttpSession session){        
        if (result.hasErrors()){
            return "topicos/formulario";
        }        
        JdbcTopicosDao dao = new JdbcTopicosDao();
       // session.setAttribute("usuario", usuario);
       // session.getAttribute("usuario");
        dao.adiciona(topico, usuario.getId());
        return "redirect:listaTopicos?="+usuario.getId();
    }

    
      @RequestMapping("/listaTopicos")
    public String listar(Model model, HttpSession session) {
        JdbcTopicosDao dao = new JdbcTopicosDao();
        List<Topico> topicos = dao.getLista();
        session.getAttribute("usuario");
        model.addAttribute("topicos",topicos);
        return "topicos/lista";
    }
    
    
     @RequestMapping("mostraTopico")
    public String mostra(Topico topico, Model model){
        JdbcTopicosDao dao = new JdbcTopicosDao();
        model.addAttribute("topico",dao.buscaPorId(topico.getId()));
        return "topicos/mostra";
    }
    
    @RequestMapping("alteraTopico")
    public String altera(Topico topico) {
        JdbcTopicosDao dao = new JdbcTopicosDao();
        dao.altera(topico);
        return "redirect:listaTopicos";
    }
    
     @RequestMapping("removeTopico")
    public String remover(Topico topico) {
        JdbcTopicosDao dao = new JdbcTopicosDao();
        dao.remove(topico);       
        return "redirect:listaTopicos";
    }
}
