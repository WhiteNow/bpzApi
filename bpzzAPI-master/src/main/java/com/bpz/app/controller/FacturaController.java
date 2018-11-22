package com.bpz.app.controller;

import java.net.URI;
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

import com.bpz.app.entity.Factura;
import com.bpz.app.service.FacturaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/facturas")
@Api(description ="REST API para facturas")
public class FacturaController {
	
	@Autowired
	private FacturaService facturaService;
	
	@ApiOperation("Lista de Facturas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Factura>> fetchFacturas(){
		try {
			List<Factura> facturas = facturaService.findAll();
			return new ResponseEntity<List<Factura>>(facturas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Factura>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Obtener factura por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Factura> fecthFactura(@PathVariable("id") Long id){
		try {
			Optional<Factura> factura = facturaService.findById(id);
			if(!factura.isPresent()) {
				return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Factura>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<Factura>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Registro de facturas")
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveFactura(@Valid @RequestBody Factura factura){
		try {
			Factura fac = new Factura();
			fac = facturaService.save(factura);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(fac.getId())
					.toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Actualizar datos de factura")
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateFactura(@Valid @RequestBody Factura factura){
		try {
			facturaService.update(factura);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar factura por id")
	@DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteFactura(@PathVariable("id") Long id) {
		try {
			Optional<Factura> factura = facturaService.findById(id);
			
			if(!factura.isPresent()) {
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			}else {
				facturaService.deleteById(id);
				return new ResponseEntity<>("Factura eliminada", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation("Eliminar todas las facturas")
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteAllFacturas(){
		try {
			facturaService.deleteAll();
			return new ResponseEntity<>("Facturas eliminados", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
