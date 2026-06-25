package com.myspring.practice.SpringLearning.intw.tesco.search;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class WordMatchRule implements SearchRule {

    private String matchingString;

    @Override
    public boolean matches(ProductItem productItem) {
        // Empty/Null checks

        String desc =  productItem.description();

        Set<String> productWords = Set.of(desc.trim().toLowerCase().split("\\s+"));
        Set<String> searchWords = Set.of(matchingString.trim().toLowerCase().split("\\s+"));

        for(String s:searchWords) {
            if(!productWords.contains(s)) {
                return false;
            }
        }

        return true;
    }
}
