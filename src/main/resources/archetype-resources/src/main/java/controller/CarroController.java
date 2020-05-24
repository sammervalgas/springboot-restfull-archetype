#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.model.carro.Carro;
import ${package}.model.carro.CarroDTO;
import ${package}.model.carro.CarroForm;
import ${package}.model.motorista.Motorista;
import ${package}.repository.CarroRepository;
import ${package}.repository.MotoristaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("carros")
@Api(value = "Rest Controller de carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @GetMapping
    @ApiOperation(value = "Lista carros com paginacao",
            notes = "Exemplo:${symbol_escape}n " +
                    "<ul>" +
                    "   <li> http://host.example.com/carros?page=0&size=10&sort=modelo,desc&sort=id,asc </li>" +
                    "</ul>")
    public Page<CarroDTO> listaComPaginacaoComOrdenacao(@RequestParam(required = false) Long idMotorista,
                                                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                                Pageable paginacao) {
        if(idMotorista == null) {
            return CarroDTO.converter(carroRepository.findAll(paginacao));
        }
        return CarroDTO.converter(carroRepository.findCarrosByMotoristaId(idMotorista, paginacao));
    }


    @GetMapping("{placa}")
    @ApiOperation(value = "Busca carro por placa", notes = "Exemplo: http://host.example/carros/ABC-1234")
    public ResponseEntity<CarroDTO> buscaCarroPorPlaca(
            @PathVariable String placa) {
        Optional<Carro> carroOpt = carroRepository.findByPlaca(placa);
        if(!carroOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CarroDTO.converter(carroOpt.get()));
    }

    @Transactional
    @PostMapping
    @ApiOperation(value = "Cadastra de carro")
    public ResponseEntity<CarroDTO> cadastra(
            @RequestBody @Valid CarroForm form,
            UriComponentsBuilder builder) {
        Carro carro = form.converter(motoristaRepository);
        carroRepository.save(carro);

        URI uri = builder.path("/carros/{id}").buildAndExpand(carro.getId()).toUri();
        return ResponseEntity.created(uri).body(new CarroDTO(carro));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta carro por ID")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Carro> carroOptional = carroRepository.findById(id);
        if(carroOptional.isPresent()) {
            carroRepository.delete(carroOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping("/troca")
    @ApiOperation(value = "Troca motorista de carro",
            notes = "Exemplo:${symbol_escape}n " +
            "<ul>" +
            "   <li> http://host.example.com/carros/troca?idCarro=ID_DO_CARRO&idMotorista=ID_DO_MOTORISTA </li>" +
            "</ul>")
    public ResponseEntity<CarroDTO> trocaMotoristaDeCarro(
            @RequestParam Long idCarro,
            @RequestParam Long idMotorista) {
        Optional<Carro> carroOptional = carroRepository.findById(idCarro);
        Optional<Motorista> motoristaOptional = motoristaRepository.findById(idMotorista);

        if(carroOptional.isPresent() && motoristaOptional.isPresent()) {
            Carro carro = carroRepository.getOne(idCarro);
            carro.setMotorista(motoristaOptional.get());
            CarroDTO carroDTO = CarroDTO.converter(carro);
            return ResponseEntity.ok(carroDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
