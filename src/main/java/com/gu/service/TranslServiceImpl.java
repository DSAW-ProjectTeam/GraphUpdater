package com.gu.service;

import com.gu.entity.Persistence;
import com.gu.repository.PersistenceRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TranslServiceImpl implements TranslService{

    @Autowired
    private PersistenceRepo persistenceRepo;

    @Override
    public List<Persistence> queryAll() {
        return persistenceRepo.findAll();
    }

    @Override
    public List<Persistence> queryAllIdGreaterThan(int id) throws IllegalArgumentException{
        if (id <= 0){
            log.error("id不合法");
            throw new IllegalArgumentException("id参数不合法");
        }
        return persistenceRepo.findAllByIdGreaterThan(id);
    }

    @Override
    public List<Persistence> queryTransl(String transl) {
        return persistenceRepo.findByTranslation(transl);
    }

}
