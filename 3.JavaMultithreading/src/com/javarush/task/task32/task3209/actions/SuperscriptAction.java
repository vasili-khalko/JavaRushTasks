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
public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    /**
     * Creates a new StyledTextAction from a string action name.
     *
     * @param nm the name of the action
     */
    public SuperscriptAction(String nm) {
        super(nm);
    }

    public SuperscriptAction(){
        super(StyleConstants.Superscript.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane editor = getEditor(e);
        MutableAttributeSet mutableAttributeSet = getStyledEditorKit(editor).getInputAttributes();
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setSuperscript(simpleAttributeSet, !StyleConstants.isSuperscript(mutableAttributeSet));
        setCharacterAttributes(editor, simpleAttributeSet, false);
    }
}
