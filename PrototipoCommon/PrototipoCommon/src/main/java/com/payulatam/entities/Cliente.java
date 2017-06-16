package com.payulatam.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;


/**
 * The persistent class for the "Cliente" database table.
 * 
 */
//@Entity
@Table(name="\"cliente\"")
@SpaceClass
@MappedSuperclass
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"direccion\"")
	private String direccion;

	@Id
	private Integer id;

	@Column(name="\"nombre\"")
	private String nombre;

	@Column(name="\"telefono\"")
	private String telefono;

	public Cliente() {
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@SpaceId(autoGenerate=false)
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