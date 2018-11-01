package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Basillio on 30.01.2018.
 */
public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(2);
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View(){
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        this.setVisible(true);

    }

    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);

    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPane);
        JScrollPane jScrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPane1);
        tabbedPane.setPreferredSize(new Dimension(600, 300));
        TabbedPaneChangeListener paneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(paneChangeListener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void exit(){
        controller.exit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand){
            case "Новый": controller.createNewDocument();
                            break;
            case "Открыть": controller.openDocument();
                            break;
            case "Сохранить": controller.saveDocument();
                            break;
            case "Сохранить как...": controller.saveDocumentAs();
                            break;
            case "Выход": controller.exit();
                            break;
            case "О программе": showAbout();
                            break;
        }
    }

    public void selectedTabChanged(){
        int selectedTab = tabbedPane.getSelectedIndex();
        if (selectedTab == 0){
            controller.setPlainText(plainTextPane.getText());
        } else if (selectedTab == 1){
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public boolean canUndo(){
        return undoManager.canUndo();
    }

    public boolean canRedo(){
        return undoManager.canRedo();
    }

    public void undo(){
        try{
            undoManager.undo();
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void redo(){
        try{
            undoManager.redo();
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(getContentPane(), 6, "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
