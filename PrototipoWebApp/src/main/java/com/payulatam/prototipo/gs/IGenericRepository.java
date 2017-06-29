package com.payulatam.prototipo.gs;

import com.payulatam.model.BaseEntity;

/**
 * Interface with generic methods definition
 * @author John Quiroga
 *
 * @param <J>
 */
public interface IGenericRepository <J extends BaseEntity> {

	/**
	 * Find objects by Id on Space
	 * @param id String with Id
	 * @return Object<J>
	 */
	public J findById( final String id );
	
	/**
	 * Find all object on Space
	 * @return Array of Object<J>
	 */
	public J[] findAll();
	
	/**
	 * Find by string criteria on Space
	 * @param criteria String with criteria
	 * @return Array of Object<J>
	 */
	public J[] findByCriteria( final String criteria);
	
	/**
	 * Save object<J> on Space
	 * @param entity
	 */
    public void save( final J entity );
    
    /**
     * Delete Object<J> by Id on Space
     * @param entityId String with Id
     */
    public void deleteById( final String entityId );

}
