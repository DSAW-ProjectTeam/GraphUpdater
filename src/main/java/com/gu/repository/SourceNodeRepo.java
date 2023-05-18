package com.gu.repository;

import com.gu.entity.SourceNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceNodeRepo extends CrudRepository<SourceNode,Long>{

    List<SourceNode> queryAllByVal(String source);
}
