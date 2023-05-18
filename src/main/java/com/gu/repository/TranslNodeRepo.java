package com.gu.repository;

import com.gu.entity.TranslNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslNodeRepo extends CrudRepository<TranslNode,Long>{

    List<TranslNode> queryAllByVal(String translation);

}
