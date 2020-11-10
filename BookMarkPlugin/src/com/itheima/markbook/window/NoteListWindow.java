package com.itheima.markbook.window;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.itheima.markbook.data.DataCenter;
import com.itheima.markbook.processor.DefaultSourceNoteData;
import com.itheima.markbook.processor.MDFreeMarkProcessor;
import com.itheima.markbook.processor.Processor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NoteListWindow {
    private JTextField tfTopic;
    private JButton createBtn;
    private JButton clearBtn;
    private JButton closeBtn;
    private JTable tbConent;
    private JPanel contentPanel;


    public void init() {
        tbConent.setModel(DataCenter.TABLE_MODEL);
        tbConent.setEnabled(true);
    }

    public NoteListWindow(Project project, ToolWindow toolWindow) {
        init();
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topic = tfTopic.getText();
                String fileName = topic + ".md";
                if (topic == null || "".equals(topic)) {
                    MessageDialogBuilder.yesNo("操作结果", "文档标题未输入");
                    return;
                }
                VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFolderDescriptor(), project, project.getBaseDir());
                if (virtualFile != null) {
                    String path = virtualFile.getPath();
                    String fileFullPath = path + "/" + fileName;
                    System.out.println(fileFullPath);
                    Processor processor = new MDFreeMarkProcessor();
                    try {
                        processor.process(new DefaultSourceNoteData(fileFullPath, topic, DataCenter.NOTE_LIST));
                        NotificationGroup notificationGroup = new NotificationGroup("markbook_id", NotificationDisplayType.BALLOON, false);
                        /**
                         * content :  通知内容
                         * type  ：通知的类型，warning,info,error
                         */
                        Notification notification = notificationGroup.createNotification("生成文档成功："+fileFullPath, MessageType.INFO);
                        Notifications.Bus.notify(notification);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataCenter.reset();
            }
        });
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.hide(null);
            }
        });
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}

