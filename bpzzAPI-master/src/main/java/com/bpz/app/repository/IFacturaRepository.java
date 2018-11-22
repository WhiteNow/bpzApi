package com.bpz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpz.app.entity.Factura;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Long>{

}
