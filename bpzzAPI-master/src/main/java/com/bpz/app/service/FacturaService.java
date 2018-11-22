package com.bpz.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpz.app.entity.Factura;
import com.bpz.app.repository.IFacturaRepository;

@Service
public class FacturaService implements IFacturaService {

	@Autowired
	private IFacturaRepository facturaRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Factura> findAll() throws Exception {
		return facturaRepository.findAll();
	}

	@Transactional
	@Override
	public Factura save(Factura t) throws Exception {
		return facturaRepository.save(t);
	}

	@Transactional
	@Override
	public Factura update(Factura t) throws Exception {
		return facturaRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Factura> findById(Long id) throws Exception {
		return facturaRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		facturaRepository.deleteById(id);

	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		facturaRepository.deleteAll();

	}

}
