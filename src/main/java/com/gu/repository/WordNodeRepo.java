package com.gu.repository;

import com.gu.entity.WordNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordNodeRepo extends CrudRepository<WordNode,Long> {

    List<WordNode> queryAllByVal(String word);
}
