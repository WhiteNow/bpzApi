package com.bpz.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpz.app.entity.Proveedor;

@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Long> {

}
	