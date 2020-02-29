package br.com.example.teste.projetoTeste.repository;



import br.com.example.teste.projetoTeste.model.Dado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Dado, Long> {

}
