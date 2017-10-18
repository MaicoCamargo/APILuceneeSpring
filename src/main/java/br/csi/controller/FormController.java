package br.csi.controller;

import br.csi.lucene.luceneDAO;
import br.csi.model.Tarefa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Controller
public class FormController {

    @RequestMapping("CadastraTarefa")
    public String pegadados(Tarefa tarefa, RedirectAttributes redirectAttributes) throws IOException {

        /**
         * se o retorno do metodo que cadastra o texto for true exibe msg de sucesso
         */
       if(new luceneDAO().cadastraTexto(tarefa)){
           redirectAttributes.addFlashAttribute("Msg", "ok");
       }
       else{
           redirectAttributes.addFlashAttribute("Msg", "erro");
       }
       return "../../index";
    }

    @RequestMapping("pesquisar")
    public String pesquisaTXT(String pesquisa, Model model) throws Exception {

        luceneDAO luceneDAO = new luceneDAO();
        if (pesquisa!=null){
            try{
                System.out.println("sucesso");
                model.addAttribute("documentos",luceneDAO.Pesquisa(pesquisa));

            }catch (Exception e){
                System.out.println("erro");
                e.printStackTrace();
            }
        }

        //retornar a pesquisa para uma tabela
        return "pagpesquisar";
    }

    /**
     *
     * @param documento recebe o documento escolhido para ser aberto
     * @return o caminho para a pagina que sera exibido o doc
     * @throws InterruptedException
     * @throws IOException
     */
    @RequestMapping("abrir")
    public String abrirDoc(String documento) throws InterruptedException, IOException {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(documento);
        desktop.open(file);
        return "redirect:pesquisar";
    }

    /**
     * manda paga a  pagina de pesquisa
     * @return
     */
    @RequestMapping("pesquisando")
    public String pagPesquisa(){ return "pagpesquisar";}


}
