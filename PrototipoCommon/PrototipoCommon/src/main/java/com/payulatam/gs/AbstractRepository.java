package com.payulatam.gs;

import org.openspaces.core.GigaSpace;
import org.springframework.core.GenericTypeResolver;

import com.j_spaces.core.client.SQLQuery;
import com.payulatam.model.BaseEntity;

/**
 * Repository with implemented generic methods 
 * @author John Quiroga
 *
 * @param <J>
 */
public abstract class AbstractRepository<J extends BaseEntity> implements IGenericRepository <J> {
	
	protected GigaSpace gigaSpace;
	
	protected Class<J> entityClass;
	
	@SuppressWarnings("unchecked")
    public AbstractRepository() {
        this.entityClass = (Class<J>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractRepository.class);
    }
	
	public J findById( final String id ) {
        return gigaSpace.readById(entityClass, id);
    }
	
	public J[] findAll() {
		SQLQuery<J> query = new SQLQuery<>(entityClass, String.format(""));
        return gigaSpace.readMultiple(query);
	}
	
	public J[] findByCriteria( final String criteria) {
		SQLQuery<J> query = new SQLQuery<>(entityClass, criteria);
        return gigaSpace.readMultiple(query);
	}
	
    public void save( final J entity ) {
    	gigaSpace.write(entity);
    }
    
    public void deleteById( final String entityId ) {
    	gigaSpace.takeIfExistsById(entityClass, entityId);
    }

}
