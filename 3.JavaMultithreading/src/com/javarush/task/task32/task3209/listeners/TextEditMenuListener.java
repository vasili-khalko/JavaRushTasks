package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;


/**
 * Created by Basillio on 06.02.2018.
 */
public class TextEditMenuListener implements MenuListener {

    private View view;

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        JMenu menu = (JMenu) menuEvent.getSource();
        Component[] components = menu.getMenuComponents();
        for (Component component: components) {
            component.setEnabled(view.isHtmlTabSelected());
        }


    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }

    public TextEditMenuListener(View view){
        this.view = view;
    }
}
