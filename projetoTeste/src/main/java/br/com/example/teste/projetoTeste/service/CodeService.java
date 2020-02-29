package br.com.example.teste.projetoTeste.service;

import br.com.example.teste.projetoTeste.model.Dado;

import java.time.LocalDate;
import java.util.List;

public interface CodeService {
    List<Dado> findAll();
    Dado findById(Long id);
    Dado save(Dado dado);
    void deleteById(Long id);
    List<Dado> findDados(String nome, Long quantidade, Float preco, LocalDate data);

}
