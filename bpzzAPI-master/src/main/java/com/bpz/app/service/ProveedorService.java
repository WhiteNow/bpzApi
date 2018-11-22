package com.bpz.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpz.app.entity.Proveedor;
import com.bpz.app.repository.IProveedorRepository;

@Service
public class ProveedorService implements IProveedorService {

	@Autowired
	private IProveedorRepository proveedorRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Proveedor> findAll() throws Exception {
		return proveedorRepository.findAll();
	}

	@Transactional
	@Override
	public Proveedor save(Proveedor t) throws Exception {
		return proveedorRepository.save(t);
	}

	@Transactional
	@Override
	public Proveedor update(Proveedor t) throws Exception {
		return proveedorRepository.save(t);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Proveedor> findById(Long id) throws Exception {
		return proveedorRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		proveedorRepository.deleteById(id);

	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		proveedorRepository.deleteAll();

	}

}
