package com.example.demo.service;

import com.example.demo.models.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repo;

    public List<Client> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public void save(Client client) {
        repo.save(client);
    }

    public Client get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}


