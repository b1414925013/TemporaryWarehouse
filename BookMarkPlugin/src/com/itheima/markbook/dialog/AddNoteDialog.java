package com.itheima.markbook.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.ui.EditorTextField;
import com.itheima.markbook.data.DataCenter;
import com.itheima.markbook.data.DataConvert;
import com.itheima.markbook.data.NoteData;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class AddNoteDialog extends DialogWrapper {
    /**
     * 标题输入框
     */
    private EditorTextField etfTitle;
    /**
     * 内容输入框
     */
    private EditorTextField etfMark;


    public AddNoteDialog() {
        super(true);
        init();
        setTitle("添加笔记注释");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        etfTitle = new EditorTextField("笔记标题");
        etfMark = new EditorTextField("笔记内容");
        etfMark.setPreferredSize(new Dimension(200,100));
        panel.add(etfTitle, BorderLayout.NORTH);
        panel.add(etfMark, BorderLayout.CENTER);
        return panel;
    }

    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton btnAdd = new JButton("添加到笔记列表");
        //按钮点击事件处理
        btnAdd.addActionListener(e -> {
            //获取标题
            String title = etfTitle.getText();
            //获取内容
            String content = etfMark.getText();
            System.out.println(title + ":" + content);
            DataCenter.CURRENT_FILE_TYPE = DataCenter.CURRENT_FILE_NAME.substring(DataCenter.CURRENT_FILE_NAME.lastIndexOf(".") + 1);
            NoteData noteData = new NoteData(title, content, DataCenter.SELECTED_TEXT, DataCenter.CURRENT_FILE_NAME, DataCenter.CURRENT_FILE_TYPE);
            DataCenter.NOTE_LIST.add(noteData);
            System.out.println(DataCenter.NOTE_LIST);
            //添加
            DataCenter.TABLE_MODEL.addRow(DataConvert.convert(noteData));
            MessageDialogBuilder.yesNo("操作结果","添加成功!").show();
            AddNoteDialog.this.dispose();
        });
        panel.add(btnAdd);
        return panel;
    }
}