package com.example.DesignPatternsSpring.service.impl;

import com.example.DesignPatternsSpring.model.Cliente;
import com.example.DesignPatternsSpring.model.Endereco;
import com.example.DesignPatternsSpring.repository.ClienteRepository;
import com.example.DesignPatternsSpring.repository.EnderecoRepository;
import com.example.DesignPatternsSpring.service.ClienteService;
import com.example.DesignPatternsSpring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public void inserirCliente(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizarCliente(Long id, Cliente novoCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.save(novoCliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
           Endereco novoEndereco = viaCepService.consultarCep(cep);
           enderecoRepository.save(novoEndereco);
           return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
