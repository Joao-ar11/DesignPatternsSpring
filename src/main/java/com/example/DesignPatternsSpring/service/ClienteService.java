package com.example.DesignPatternsSpring.service;

import com.example.DesignPatternsSpring.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserirCliente(Cliente cliente);

    void atualizarCliente(Long id, Cliente cliente);

    void deletar(Long id);
}
