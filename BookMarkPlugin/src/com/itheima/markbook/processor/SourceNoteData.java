package com.itheima.markbook.processor;

import com.itheima.markbook.data.NoteData;

import java.util.List;

public interface SourceNoteData {
    String getFileName();
    String getTopic();
    List<NoteData> getNoteList();
}
