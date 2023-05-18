package com.gu.service;

import com.gu.entity.SourceNode;
import com.gu.entity.TranslNode;
import com.gu.entity.WordNode;

import java.util.HashMap;
import java.util.List;

public interface Neo4jService {

    void saveSourceNode(HashMap<String,SourceNode> sourceNodes);

    void saveSourceNode(SourceNode sourceNode);

    void saveTranslNode(HashMap<String,TranslNode> translNodes);

    void saveTranslNode(TranslNode translNode);

    void saveWordNode(HashMap<String,WordNode> wordNodes);

    void saveWordNode(WordNode wordNode);

    List<SourceNode> queryAllSource(String source);

    List<TranslNode> queryAllTransl(String translation);

    List<WordNode> queryAllWord(String word);
}
