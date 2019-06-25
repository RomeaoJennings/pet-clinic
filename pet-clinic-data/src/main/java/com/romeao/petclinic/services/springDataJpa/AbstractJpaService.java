package com.romeao.petclinic.services.springDataJpa;

import com.romeao.petclinic.models.BaseEntity;
import com.romeao.petclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractJpaService<T extends BaseEntity>
        implements CrudService<T, Long> {

    protected final CrudRepository<T, Long> repository;

    public AbstractJpaService(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Set<T> findAll() {
        Set<T> hash = new HashSet<>();
        repository.findAll().forEach(hash::add);
        return hash;
    }

    @Override
    public T findById(Long aLong) {
        if (aLong == null)
            return null;
        Optional<T> result = repository.findById(aLong);
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public T save(T obj) {
        if (obj == null)
            throw new IllegalArgumentException("Cannot save null object.");
        return repository.save(obj);
    }

    @Override
    public void delete(T obj) {
        repository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }
}
