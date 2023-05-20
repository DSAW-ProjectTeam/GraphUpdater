package com.gu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslNode {

    @Id
    @GeneratedValue
    private long id;

    private long uuid;

    private String val;

    private String word;

    private String source;

    private int likes;

    @Relationship(value = "出自", direction = Relationship.Direction.OUTGOING, type = "出自")
    private Set<SourceNode> sourceNodes;

    public void addSourceNode(SourceNode sourceNode){
        if (sourceNodes == null){
            sourceNodes = new HashSet<>();
        }
        sourceNodes.add(sourceNode);
    }
}
