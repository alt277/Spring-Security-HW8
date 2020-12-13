package com.example.SpringSecurityHW8.populators;

import java.util.ArrayList;
import java.util.List;

public interface Populator<SOURCE, TARGET> {
    TARGET populate(SOURCE source, TARGET target);
    TARGET populate(SOURCE source);

    default public List<TARGET> convertAll(List<SOURCE> sourceList) {
        List<TARGET> targetList = new ArrayList<>();
        sourceList.forEach(source -> {
            targetList.add(populate(source));
        });
        return targetList;
    }
}