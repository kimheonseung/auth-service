package com.devh.common.api.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
public abstract class TreeVO<T> {
    protected String text;
    protected final List<T> children = new ArrayList<>();

    public void addChild(T child) {
        this.children.add(child);
    }
    public abstract boolean isRoot();
    public abstract void setText();
}
