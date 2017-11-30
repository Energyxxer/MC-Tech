package com.energyxxer.commodore.functions;

import com.energyxxer.commodore.entity.Entity;
import com.energyxxer.commodore.entity.GenericEntity;
import com.energyxxer.commodore.selector.Selector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Function {
    private String name;
    private ArrayList<FunctionWriter> content = new ArrayList<>();
    private Entity sender;

    public Function(String name) {
        this(name, new GenericEntity(new Selector(Selector.BaseSelector.SENDER)));
    }

    public Function(String name, Entity sender) {
        this.name = name;
        this.sender = sender;
    }

    public Entity getSender() {
        return sender;
    }

    public void append(FunctionWriter... writers) {
        append(Arrays.asList(writers));
    }

    public void append(Collection<FunctionWriter> writers) {
        this.content.addAll(writers);
        writers.forEach(FunctionWriter::onAppend);
    }

    public String getContent() {
        StringBuilder sb = new StringBuilder("# ");
        sb.append(name);
        sb.append('\n');

        for(FunctionWriter writer : content) {
            String content = writer.toFunctionContent(this);
            if(content != null) {
                sb.append(content);
                sb.append('\n');
            }
        }

        return sb.toString();
    }
}
