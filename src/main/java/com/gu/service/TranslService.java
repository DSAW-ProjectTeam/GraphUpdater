package com.gu.service;

import com.gu.entity.Persistence;

import java.util.List;

public interface TranslService {

    List<Persistence> queryAll();

    List<Persistence> queryAllIdGreaterThan(int id) throws IllegalArgumentException;

    List<Persistence> queryTransl(String transl);

}
