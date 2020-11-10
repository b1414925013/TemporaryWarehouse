package com.itheima.markbook.processor;

import com.itheima.markbook.data.NoteData;

import java.util.List;

public class DefaultSourceNoteData implements SourceNoteData {
    public String fileName;
    public String topic;
    public List<NoteData> noteList;

    public DefaultSourceNoteData(String fileName, String topic, List<NoteData> noteList) {
        this.fileName = fileName;
        this.topic = topic;
        this.noteList = noteList;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public List<NoteData> getNoteList() {
        return noteList;
    }
}
