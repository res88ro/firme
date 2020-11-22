package com.example.demo.controllers;

import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {


    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;


    @RequestMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Client> listClients = clientService.listAll(keyword);
        model.addAttribute("listClients", listClients);
        model.addAttribute("keyword", keyword);

        return "index";
    }



    //arata pagina de client nou
    @RequestMapping("/new")
    public String showNewClientPage(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);

        return "new_client";
    }

    //cand apasam pe submit
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.save(client);

        return "redirect:/"; //redirect la homepage
    }

    //editarea unui client
    @GetMapping("/index/edit{id}")
    public String showEditClientPage(@PathVariable Long id, Model model) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        model.addAttribute("client", client);
        return "edit_client";
    }

    @GetMapping("/index/edit_client.html")
    public String goToEditPage(@ModelAttribute Client client) {
        return "edit_client";
    }


    //delete
    @GetMapping("/index/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id: " + id));
        clientRepository.delete(client);
        return "redirect:/";
    }

   /* @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,
                           @PathVariable(name = "pageNum") int pageNum,
                           @Param("sortField") String sortField,
                           @Param("sortDir") String sortDir) {

        Page<Client> page = clientService.listAll(pageNum, sortField, sortDir);

        List<Client> listClients = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listClients", listClients);

        return "index";
    }*/


}