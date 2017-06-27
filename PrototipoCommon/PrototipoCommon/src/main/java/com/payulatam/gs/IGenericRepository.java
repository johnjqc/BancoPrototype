package com.payulatam.gs;

import com.payulatam.model.BaseEntity;

/**
 * Interface with generic methods definition
 * @author John Quiroga
 *
 * @param <J>
 */
public interface IGenericRepository <J extends BaseEntity> {

	public J findById( final String id );
	public J[] findAll();
	public J[] findByCriteria( final String criteria);
    public void save( final J entity );
    public void deleteById( final String entityId );

}
