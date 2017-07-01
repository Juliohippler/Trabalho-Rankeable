/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.rankeable.controllers;

import br.ufpr.rankeable.dao.JdbcUsuarioDao;
import br.ufpr.rankeable.modelo.Usuario;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.lang.IllegalStateException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author julio
 */
@Controller
public class LoginController {
    
    @RequestMapping("/loginForm")
    public String loginForm() {
        return "formulario-login";
    }
 
    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }
     @RequestMapping("/cadastrarForm")
    public String cadastrarForm() {
        return "novo-usuario";
    }
    
    @RequestMapping("/efetuaCadastro")
    public String efetuaCadastro(Usuario usuario, BindingResult result) throws SQLException{
         if (result.hasErrors()){
            return "castrarUsuario";
        }
         JdbcUsuarioDao dao = new JdbcUsuarioDao();
         dao.adicionaUsuario(usuario);
         return "redirect:loginForm";
    }   
       
    
    @RequestMapping("/efetuaLogin")
    public String efetuaLogin(Usuario usuario, HttpSession session) throws SQLException {
      
        if (new JdbcUsuarioDao().existeUsuario(usuario) != null) {
            session.setAttribute("usuario", usuario);           
            
            return "menu";
        } else {
            return "/loginForm";
        }
        //return "redirect:listaTopicos?id="+usuario.getId();
        //return "redirect:telaVotacao";
    }
    

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:loginForm";
}
}
