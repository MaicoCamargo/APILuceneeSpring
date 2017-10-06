package br.csi.controller;

import br.csi.model.Tarefa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

    @RequestMapping("CadastraTarefa")
    public void pegadados(Tarefa tarefa){
        System.out.println("texto descrição: "+tarefa.getDescricao());
        System.out.println("texto tituloaaass: "+tarefa.getTitulo());
        
    }
}
