package com.itheima.markbook.processor;


import freemarker.template.Template;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

public abstract class AbstractFreeMarkerProcessor implements Processor {

    protected abstract Template getTemplate() throws Exception;

    protected abstract Object getModel(SourceNoteData sourceNoteData);

    protected abstract Writer getWriter(SourceNoteData sourceNoteData) throws Exception;


    @Override
    public final void process(SourceNoteData sourceNoteData) throws Exception {
        Template template = getTemplate();
        Object model = getModel(sourceNoteData);
        Writer writer = getWriter(sourceNoteData);
        template.process(model, writer);
    }
}

