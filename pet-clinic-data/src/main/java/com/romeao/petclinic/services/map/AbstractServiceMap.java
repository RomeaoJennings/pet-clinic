package com.romeao.petclinic.services.map;

import com.romeao.petclinic.models.BaseEntity;
import com.romeao.petclinic.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractServiceMap<T extends BaseEntity> implements CrudService<T, Long> {
    protected Map<Long, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T findById(Long id) {
        return map.get(id);
    }

    public T save(T object) {
        if (object == null)
            throw new IllegalArgumentException("Unable to save.  Argument 'object' cannot be null!");
        if (object.getId() == null)
            object.setId(getNextId());
        map.put(object.getId(), object);
        return object;
    }

    public void deleteById(Long id) {
        map.remove(id);
    }

    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private long getNextId() {
        return map.size();
    }
}
