package com.example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Slf4j
@Component
public class JoinTableInterceptor extends EmptyInterceptor
{

    @Override
    public void onCollectionRemove(final Object collection, final Serializable key) throws CallbackException
    {
        log.info("onCollectionRemovalCalled.");
    }

    @Override
    public void onCollectionRecreate(final Object collection, final Serializable key) throws CallbackException
    {
        super.onCollectionRecreate(collection, key);
    }

    @Override
    public void onDelete(final Object entity, final Serializable id, final Object[] state, final String[] propertyNames, final Type[] types)
    {
        log.info("onDelete() method called.");
    }

    @Override
    public String getEntityName(final Object object)
    {
        String string = super.getEntityName(object);
        log.info("getEntityName() called.");
        return string;
    }

    @Override
    public Object getEntity(final String entityName, final Serializable id)
    {
        Object object = super.getEntity(entityName, id);
        log.info("getEntity() called.");
        return object;
    }
}
