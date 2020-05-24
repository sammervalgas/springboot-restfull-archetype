#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.enums.Categoria;
import ${package}.model.motorista.Motorista;
import ${package}.model.motorista.MotoristaDTO;
import ${package}.model.motorista.MotoristaForm;
import ${package}.model.motorista.MotoristaUpdateForm;
import ${package}.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @GetMapping
    public List<Motorista> listAll(Categoria categoria) {
        if(categoria != null) {
            return motoristaRepository.buscaCnhPorCategoria(categoria);
        }
        return motoristaRepository.findAll();
    }

    @GetMapping("/cnh/{numero}")
    public ResponseEntity<MotoristaDTO> buscaMotoristaPorNumeroCNH(@PathVariable Long numeroCNH) {

        Motorista motorista = motoristaRepository.getMotoristaByCnhNumero(numeroCNH);
        return ResponseEntity.ok(new MotoristaDTO(motorista));
    }

    @GetMapping("{id}")
    public ResponseEntity<MotoristaDTO> detalhe(@PathVariable Long id) {
        Optional<Motorista> motoristaOpt = motoristaRepository.findById(id);
        if(motoristaOpt.isPresent()) {
            return ResponseEntity.ok(new MotoristaDTO(motoristaOpt.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Motorista> salva(@RequestBody @Valid MotoristaForm form, UriComponentsBuilder builder) {
        Motorista motorista = form.converter();
        motoristaRepository.save(motorista);

        URI uri = builder.path("/motoristas/{id}").buildAndExpand(motorista.getId()).toUri();
        return ResponseEntity.created(uri).body(motorista);
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<MotoristaDTO> atualiza(@PathVariable Long id, @RequestBody @Valid MotoristaUpdateForm form) {
        Optional<Motorista> motoristaOpt = motoristaRepository.findById(id);
        if(motoristaOpt.isPresent()) {
            Motorista motorista = form.atualiza(id, motoristaRepository);
            return ResponseEntity.ok(new MotoristaDTO(motorista));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Motorista> motoristaOpt = motoristaRepository.findById(id);
        if(motoristaOpt.isPresent()) {
            motoristaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
