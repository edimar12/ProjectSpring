package br.com.example.teste.projetoTeste.controller;



import br.com.example.teste.projetoTeste.model.Dado;
import br.com.example.teste.projetoTeste.repository.CodeRepository;
import br.com.example.teste.projetoTeste.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CodeController {

    @Autowired
    CodeService codeService;
    @Autowired
    CodeRepository codeRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dado>> getAll(){

        return ResponseEntity.ok().body(codeService.findAll());
    }


    @RequestMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(required = false) Long id) {
        codeService.deleteById(id);
        return ResponseEntity.ok().body("Deletado!");
    }


    @RequestMapping(value = "/api/dados",method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dado>> dados(@RequestParam(required = false) String nome, @RequestParam(required = false) Long quantidade, @RequestParam(required = false) Float preco, @RequestParam(required = false) LocalDate data) {

        return ResponseEntity.ok().body(codeService.findDados(nome,quantidade,preco,data));
    }
    @RequestMapping("/add")
    public ResponseEntity<?> saveDados(){

        List<Dado> dadoList = new ArrayList<Dado>();
        Dado dado1 = new Dado();
        dado1.setNome("Eu");
        dado1.setData(LocalDate.now());
        dado1.setQuantidade((long) 3);

        dado1.setPreco((float) 12334.0);

        Dado dado2 = new Dado();
        dado2.setNome("Eu dnv");
        dado2.setData(LocalDate.now());
        dado2.setQuantidade((long) 2);
        dado2.setPreco((float) 123.01);

        dadoList.add(dado1);
        dadoList.add(dado2);

        for(Dado dado: dadoList){
            Dado dadoSaved = codeRepository.save(dado);
            System.out.println(dadoSaved.getId());
        }
        return ResponseEntity.ok().body("OK");
    }

}
