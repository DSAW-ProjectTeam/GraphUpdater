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
public class WordNode {

    @Id
    @GeneratedValue
    private long id;

    private String val;

    @Relationship(value = "释义", direction = Relationship.Direction.OUTGOING, type = "释义")
    private Set<TranslNode> translNodes;

    public void addTranslationNode(TranslNode translNode){
        if (translNodes == null){
            translNodes = new HashSet<>();
        }
        translNodes.add(translNode);
    }
}
