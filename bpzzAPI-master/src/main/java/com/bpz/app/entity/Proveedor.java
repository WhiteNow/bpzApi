package com.bpz.app.entity;

import java.io.Serializable;



import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "proveedor")
public class Proveedor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProveedor;
	
	@NotNull
	private String razonSocial;
	
	@NotNull
	private String ruc;
	
	@NotNull
	private String cuentaBcoNacion;
	
	@NotNull
	private String tipoPersona;
	
	
	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getCuentaBcoNacion() {
		return cuentaBcoNacion;
	}

	public void setCuentaBcoNacion(String cuentaBcoNacion) {
		this.cuentaBcoNacion = cuentaBcoNacion;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	
}
