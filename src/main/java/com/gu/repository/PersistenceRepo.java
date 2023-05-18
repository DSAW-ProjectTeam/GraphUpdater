package com.gu.repository;

import com.gu.entity.Persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersistenceRepo extends CrudRepository<Persistence, Integer>, JpaRepository<Persistence, Integer> {

    List<Persistence> findAllByIdGreaterThan(int id);

    List<Persistence> findByTranslation(String translation);
}
