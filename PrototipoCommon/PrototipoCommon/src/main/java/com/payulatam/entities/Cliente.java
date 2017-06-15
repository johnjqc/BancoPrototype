package com.payulatam.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "Cliente" database table.
 * 
 */
@Entity
@Table(name="\"Cliente\"")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"Direccion\"")
	private String direccion;

	@Id
	private Integer id;

	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"Telefono\"")
	private String telefono;

	public Cliente() {
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}