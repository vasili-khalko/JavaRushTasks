package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Created by Basillio on 06.02.2018.
 */
public class UndoMenuListener implements MenuListener {

    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;



    @Override
    public void menuSelected(MenuEvent e) {
        undoMenuItem.setEnabled(view.canUndo());
        redoMenuItem.setEnabled(view.canRedo());

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem){
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }
}
