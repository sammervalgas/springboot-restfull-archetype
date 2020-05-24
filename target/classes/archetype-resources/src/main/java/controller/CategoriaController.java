#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.enums.Categoria;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Classe de exemplo de cache simples.
 */
@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @GetMapping
    @ApiOperation(value = "Lista de categorias CNH", notes = "Lista de categorias com Spring Cache")
    @Cacheable(value = "listaCategorias")
    public List<Categoria> listaCategorias() {
        Categoria[] categorias = Categoria.values();
        System.out.println(categorias);
        return Arrays.asList(categorias);
    }

    @GetMapping(value = "limpar")
    @ApiOperation(value = "Limpa cache de lista categorias", notes = "Lista de categorias com Spring Cache")
    @CacheEvict(value = "listaCategorias")
    public ResponseEntity limpaCacheCategorias() {
        return ResponseEntity.ok().build();
    }
}
