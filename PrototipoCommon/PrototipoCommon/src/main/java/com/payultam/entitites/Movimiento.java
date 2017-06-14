package com.payultam.entitites;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;


/**
 * The persistent class for the "Movimiento" database table.
 * 
 */
@Entity
@Table(name="\"Movimiento\"")
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Time fecha;

	private String tipo;

	private BigDecimal valor;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	private Cuenta cuenta;

	public Movimiento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Time getFecha() {
		return this.fecha;
	}

	public void setFecha(Time fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}