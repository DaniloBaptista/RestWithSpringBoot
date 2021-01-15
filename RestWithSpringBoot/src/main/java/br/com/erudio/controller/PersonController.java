package br.com.erudio.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;

//@CrossOrigin(origins="http//localhost:8080")
@Api(value = "Person EndPoint", description =  "Description for person", tags={"PersonEndpoint"})
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonServices service;

	@ApiOperation(value = "find all people recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<PagedResources<PersonVO>> findAll(
			@RequestParam(value ="page", defaultValue = "0") int page,
			@RequestParam(value ="limit", defaultValue = "12") int limit,
			@RequestParam(value ="direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> personVO = service.findAll(pageable);
		personVO.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<>(assembler.toResource(personVO), HttpStatus.OK);
	}
	@ApiOperation(value = "find all people recorded")
	@GetMapping(value = "findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<PagedResources<PersonVO>> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value ="page", defaultValue = "0") int page,
			@RequestParam(value ="limit", defaultValue = "12") int limit,
			@RequestParam(value ="direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page,limit, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> personVO = service.findPersonByName(firstName,pageable);
		personVO.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<>(assembler.toResource(personVO), HttpStatus.OK);
	}

	@ApiOperation(value = "find by id people")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = service.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "create people")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml"},
				consumes =  {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO personVO = service.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "update people")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml"},
			consumes =  {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = service.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}
	@ApiOperation(value = "Disable a specific person by your id")
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml"})
	public PersonVO disablePerson(@PathVariable("id") Long id) {
		PersonVO personVO = service.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	@ApiOperation(value = "delete people")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}