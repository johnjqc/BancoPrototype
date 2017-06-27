package com.payulatam.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

/**
 * The persistent class base database table.
 * 
 * @author John
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable , Cloneable {
	
	private static final long serialVersionUID = 722732249299686246L;
	
	protected String id;
	
	protected Long spacerouting = 1L;

	public BaseEntity() {
	}

	@Id
	@SpaceId(autoGenerate = true)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Transient
	@SpaceRouting
	public Long getSpacerouting() {
		return this.spacerouting;
	}

	public void setSpacerouting(Long spacerouting) {
		this.spacerouting = spacerouting;
	}
	
	public Object clone() {
        try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
    }
	
}
