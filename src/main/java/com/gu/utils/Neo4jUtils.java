package com.gu.utils;

import com.gu.entity.Persistence;
import com.gu.entity.SourceNode;
import com.gu.entity.TranslNode;
import com.gu.entity.WordNode;
import com.gu.service.Neo4jServiceImpl;
import com.gu.service.TranslServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class Neo4jUtils {

    @Autowired
    private TranslServiceImpl translService;

    @Autowired
    private Neo4jServiceImpl neo4jService;

    @Autowired
    private StringUtils stringUtils;


    /**
     * @Author Midsummra
     * <h3>存储[来源节点]</h3>
     */
    public void addSourceNodes(){
        HashMap<String, SourceNode> nodesToSave = new HashMap<>();
        for (Persistence data : translService.queryAll()) {
            if (data == null || data.getSource() == null){
                continue;
            }
            String source = data.getSource().trim().toLowerCase();

            // 如果数据库中存在当前来源节点则跳过
            if (stringUtils.isNullOrEmpty(source) || isSourceExist(source) != null){
                continue;
            }

            SourceNode sourceNode = new SourceNode(source.hashCode(), source.hashCode(), source, source);
            nodesToSave.put(source,sourceNode);
        }

        neo4jService.saveSourceNode(nodesToSave);
        log.debug("保存了" + nodesToSave.size() + "个来源节点");

    }

    /**
     * @Author Midsummra
     * <h3>存储[释义节点]</h3>
     * @// TODO: 2023/5/20  节点重复添加，待修复
     */
    public void addTranslationNodes(){
        HashMap<String, TranslNode> nodesToSave = new HashMap<>();
        for (Persistence data : translService.queryAll()) {
            if (data == null){
                continue;
            }
            String source = data.getSource();
            if (source != null){
                source = source.trim().toLowerCase();
            }

            String translation = data.getTranslation().trim().toLowerCase();
            TranslNode translNode = null;
            SourceNode sourceNode = null;

            // 获得当前data的source
            sourceNode = isSourceExist(source);
            if (!stringUtils.isNullOrEmpty(source) && sourceNode == null){
                sourceNode = new SourceNode(source.hashCode(), source.hashCode(), source, source);
                neo4jService.saveSourceNode(sourceNode);
            }

            // 如果存在当前释义节点存在，为它添加新的出处
            if (!stringUtils.isNullOrEmpty(source) && nodesToSave.containsKey(translation)){
                    translNode = nodesToSave.get(translation);
                    translNode.addSourceNode(sourceNode);
                    continue;
            }

            translNode = isTranslExist(translation);
            if (!stringUtils.isNullOrEmpty(source) && translNode != null){
                translNode.addSourceNode(sourceNode);
                nodesToSave.put(translation,translNode);
                continue;
            }

            // 否则新建
            translNode = new TranslNode(translation.hashCode(), translation.hashCode(), data.getTranslation(),data.getWord(),null,0,null);

            if (sourceNode != null){
                translNode.addSourceNode(sourceNode);
            }
            nodesToSave.put(translation,translNode);
        }

        neo4jService.saveTranslNode(nodesToSave);
        log.debug(("保存了" + nodesToSave.size() + "个释义节点"));
    }

    /**
     * @Author Midsummra
     * <h3>存储[词条节点]</h3>
     */
    public void addWordNodes(){
        HashMap<String, WordNode> nodesToSave = new HashMap<>();
        for (Persistence data : translService.queryAll()) {
            if (data == null){
                continue;
            }
            String word = data.getWord().trim().toLowerCase();
            String translation = data.getTranslation().trim().toLowerCase();
            TranslNode translNode = null;
            WordNode wordNode = null;

            // 获取当前data的translation
            translNode = isTranslExist(translation);
            if (!stringUtils.isNullOrEmpty(translation) && translNode == null){
                translNode = new TranslNode(translation.hashCode(), translation.hashCode(), data.getTranslation(),data.getWord(),null,0,null);
                neo4jService.saveTranslNode(translNode);
            }

            // 如果当前词条节点存在，为它添加新的释义
            if (!stringUtils.isNullOrEmpty(translation) && nodesToSave.containsKey(word)){
                wordNode = nodesToSave.get(word);
                wordNode.addTranslationNode(translNode);
                continue;
            }

            wordNode = isWordExist(word);
            if (!stringUtils.isNullOrEmpty(word) && wordNode != null){
                wordNode.addTranslationNode(translNode);
                nodesToSave.put(word,wordNode);
            }

            // 否则新建
            wordNode = new WordNode(word.hashCode(), word.hashCode(), data.getWord(), null);
            if (translNode != null){
                wordNode.addTranslationNode(translNode);
            }
            nodesToSave.put(word,wordNode);
        }

        neo4jService.saveWordNode(nodesToSave);
        log.debug(("保存了" + nodesToSave.size() + "个词条节点"));
    }

    /**
     *
     * @param source 来源
     * @return SourceNode 来源是否存在(不存在时返回null)
     */
    private @Nullable SourceNode isSourceExist(String source){
        if (source == null){
            return null;
        }
        List<SourceNode> sourceNodes = neo4jService.queryAllSource(source);
        return (sourceNodes != null && sourceNodes.size() >= 1) ? sourceNodes.get(0) : null;

    }

    /**
     *
     * @param translation 释义
     * @return TranslNode 释义是否存在(不存在时返回null)
     */
    private @Nullable TranslNode isTranslExist(String translation){
        if (translation == null){
            return null;
        }
        List<TranslNode> translNodes = neo4jService.queryAllTransl(translation);
        return (translNodes != null && translNodes.size() >= 1) ? translNodes.get(0) : null;
    }

    /**
     *
     * @param word 释义
     * @return WordNode 词条是否存在(不存在时返回null)
     */
    private @Nullable WordNode isWordExist(String word){
        if (word == null){
            return null;
        }
        List<WordNode> wordNodes = neo4jService.queryAllWord(word);
        return (wordNodes != null && wordNodes.size() >= 1) ? wordNodes.get(0) : null;
    }
}
