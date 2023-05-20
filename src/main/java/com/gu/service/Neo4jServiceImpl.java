package com.gu.service;

import com.gu.entity.SourceNode;
import com.gu.entity.TranslNode;
import com.gu.entity.WordNode;
import com.gu.repository.SourceNodeRepo;
import com.gu.repository.TranslNodeRepo;
import com.gu.repository.WordNodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class Neo4jServiceImpl implements Neo4jService{

    @Autowired
    private SourceNodeRepo sourceNodeRepo;

    @Autowired
    private TranslNodeRepo translNodeRepo;

    @Autowired
    private WordNodeRepo wordNodeRepo;

    @Override
    public void saveSourceNode(HashMap<String,SourceNode> sourceNodes) {
        sourceNodeRepo.saveAll(sourceNodes.values());
    }

    @Override
    public void saveSourceNode(SourceNode sourceNode) {
        sourceNodeRepo.save(sourceNode);
    }

    @Override
    public void saveTranslNode(HashMap<String,TranslNode> translNodes) {
        translNodeRepo.saveAll(translNodes.values());
    }

    @Override
    public void saveTranslNode(TranslNode translNode) {
        translNodeRepo.save(translNode);
    }

    @Override
    public void saveWordNode(HashMap<String,WordNode> wordNodes) {
        wordNodeRepo.saveAll(wordNodes.values());
    }

    @Override
    public void saveWordNode(WordNode wordNode) {
        wordNodeRepo.save(wordNode);
    }

    @Override
    public List<SourceNode> queryAllSource(String source) {
        return sourceNodeRepo.queryAllByUuid(source.hashCode());
    }

    @Override
    public List<TranslNode> queryAllTransl(String translation) {
        return translNodeRepo.queryAllByUuid(translation.hashCode());
    }

    @Override
    public List<WordNode> queryAllWord(String word) {
        return wordNodeRepo.queryAllByUuid(word.hashCode());
    }
}
