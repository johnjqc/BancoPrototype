package com.payulatam.prototipo.model;

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
	
	/**
	 * Id of Entity
	 */
	protected String id;
	
	/**
	 * Space routing of GigaSpaces
	 */
	protected Long spacerouting = 1L;

	public BaseEntity() {
	}

	/**
	 * Get Id of entity
	 * @return
	 */
	@Id
	@SpaceId(autoGenerate = true)
	public String getId() {
		return this.id;
	}

	/**
	 * Set Id of entity
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Get Space Rounting
	 * @return
	 */
	@Transient
	@SpaceRouting
	public Long getSpacerouting() {
		return this.spacerouting;
	}

	/**
	 * Set Space Routing
	 * @param spacerouting
	 */
	public void setSpacerouting(Long spacerouting) {
		this.spacerouting = spacerouting;
	}
	
	/**
	 * Clone entity
	 */
	public Object clone() {
        try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
    }
	
}
