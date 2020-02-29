package br.com.example.teste.projetoTeste.service.serviceImpl;


import br.com.example.teste.projetoTeste.model.Dado;
import br.com.example.teste.projetoTeste.repository.CodeRepository;
import br.com.example.teste.projetoTeste.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    CodeRepository codeRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Dado> findAll() {
        return (List<Dado>)codeRepository.findAll();
    }

    @Override
    public Dado findById(Long id) {
        return codeRepository.findById(id).get();
    }

    @Override
    public Dado save(Dado dado) {

        return codeRepository.save(dado);
    }
    @Override
    public void deleteById(Long id){
        codeRepository.deleteById(id);
    }
    @Override
    public List<Dado> findDados(String nome, Long quantidade, Float preco, LocalDate data){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Dado> cq = cb.createQuery(Dado.class);
        Root<Dado> quest = cq.from(Dado.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (nome != null) {

            predicates.add(cb.equal(quest.get("nome"), nome));
        }
        if (preco != null) {

            predicates.add(cb.equal(quest.get("preco"), preco));
        }
        if (data != null) {

            predicates.add(cb.equal(quest.get("data"), data));
        }
        if (quantidade != null) {

            predicates.add(cb.equal(quest.get("quantidade"),quantidade));
        }


        // other predicates

        cq.select(quest).where(predicates.toArray(new Predicate[] {}));
        List<Dado> dados = em.createQuery(cq).getResultList();
        return dados;
    }


}
