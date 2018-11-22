package com.bpz.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bpz.app.entity.Proveedor;
import com.bpz.app.service.ProveedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/proveedores")
@Api(description="REST API para proveedores")
public class ProveedorController {
	
	@Autowired
	private ProveedorService proveedorService;
	
	@ApiOperation("Lista de Proveedores")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Proveedor>> fecthProveedores(){
		try {
			List<Proveedor> proveedores = new ArrayList<>();
			proveedores = proveedorService.findAll();
			return new ResponseEntity<List<Proveedor>>(proveedores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Proveedor>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Obtener proveedores por id")
	@GetMapping( value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Proveedor> fetchProveedor(@PathVariable("id") Long id){
		try {
			Optional<Proveedor> proveedor = proveedorService.findById(id);
			if(!proveedor.isPresent()) {
				return new ResponseEntity<Proveedor>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Proveedor>(proveedor.get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Proveedor>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation("Registro de Proveedores")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveProveedor(@Valid @RequestBody Proveedor proveedor){
		try {
			Proveedor prove = new Proveedor();
			prove = proveedorService.save(proveedor);
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(prove.getIdProveedor())
					.toUri();
			return ResponseEntity.created(location).build();
					
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualizar datos de Proveedores")
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateProveedor(@Valid @RequestBody Proveedor proveedor){
		try {
			proveedorService.update(proveedor);
			return new ResponseEntity<Object>(HttpStatus.OK);
					
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar un roveedor por id")
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProveedor(@PathVariable("id") Long id) {
		try {
			Optional<Proveedor> proveedor = proveedorService.findById(id);
			
			if(!proveedor.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else {
				proveedorService.deleteById(id);
				return new ResponseEntity<>("Proveedor eliminado", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar todos los proveedores")
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAllProveedores(){
		try {
			proveedorService.deleteAll();
			return new ResponseEntity<>("Proveedores eliminados", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	

}
