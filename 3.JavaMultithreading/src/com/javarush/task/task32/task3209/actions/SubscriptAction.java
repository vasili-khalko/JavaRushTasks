package com.javarush.task.task32.task3209.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

/**
 * Created by Basillio on 06.02.2018.
 */
public class SubscriptAction extends StyledEditorKit.StyledTextAction {
    /**
     * Creates a new StyledTextAction from a string action name.
     *
     * @param nm the name of the action
     */
    public SubscriptAction(String nm) {
        super(nm);
    }

    public SubscriptAction(){
        super(StyleConstants.Subscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane editor = getEditor(e);
        if (editor != null){
            MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
            SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
            StyleConstants.setSubscript(simpleAttributeSet, !StyleConstants.isSubscript(mutableAttributeSet));
            setCharacterAttributes(editor, simpleAttributeSet,false);
        }
    }
}
