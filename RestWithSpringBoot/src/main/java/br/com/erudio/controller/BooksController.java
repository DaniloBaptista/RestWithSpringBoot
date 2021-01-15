package br.com.erudio.controller;

import java.util.List;

import br.com.erudio.data.vo.v1.BooksVO;
import br.com.erudio.services.BooksServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;

@RestController
@RequestMapping("/api/books/v1")
public class BooksController {

    @Autowired
    private BooksServices service;

    @ApiOperation(value = "find all books recorded")
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<PagedResources<BooksVO>> findAll(
            @RequestParam(value ="page", defaultValue = "0") int page,
            @RequestParam(value ="limit", defaultValue = "12") int limit,
            @RequestParam(value ="direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler assembler) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "author"));
        Page<BooksVO> booksVO = service.findAll(pageable);
        booksVO.stream().forEach(p -> p.add(linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel()));
        return new ResponseEntity<>(assembler.toResource(booksVO), HttpStatus.OK);
    }

    @ApiOperation(value = "find by id books recorded")
    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml"})
    public BooksVO findById(@PathVariable("id") Long id) {
        BooksVO booksVO = service.findById(id);
        booksVO.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return booksVO;
    }

    @ApiOperation(value = "create books")
    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml"},
            consumes =  {"application/json", "application/xml", "application/x-yaml"})
    public BooksVO create(@RequestBody BooksVO books) {
        BooksVO booksVO = service.create(books);
        booksVO.add(linkTo(methodOn(BooksController.class).findById(booksVO.getKey())).withSelfRel());
        return booksVO;
    }

    @ApiOperation(value = "update books")
    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml"},
            consumes =  {"application/json", "application/xml", "application/x-yaml"})
    public BooksVO update(@RequestBody BooksVO books) {
        BooksVO booksVO = service.update(books);
        booksVO.add(linkTo(methodOn(BooksController.class).findById(booksVO.getKey())).withSelfRel());
        return booksVO;
    }

    @ApiOperation(value = "delete books")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
