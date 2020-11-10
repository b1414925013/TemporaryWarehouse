package com.itheima.markbook.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.itheima.markbook.data.DataCenter;
import com.itheima.markbook.dialog.AddNoteDialog;

public class PopupAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        //获取当前编辑器对象
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        //获取选择的数据模型
        SelectionModel selectionModel = editor.getSelectionModel();
        //获取当前选择的文本
        String selectedText = selectionModel.getSelectedText();
        System.out.println(selectedText);
        DataCenter.SELECTED_TEXT =selectedText;
        String name = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile().getName();
        DataCenter.CURRENT_FILE_NAME=name;
        AddNoteDialog addNoteDialog = new AddNoteDialog();
        addNoteDialog.show();
    }
}
