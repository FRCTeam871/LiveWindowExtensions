package com.team871.livewindow.widgets;

import com.team871.livewindow.types.SubLiftType;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.livewindow.elements.NameTag;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;

import javax.swing.*;
import java.awt.*;

public class SubLift extends AbstractTableWidget {
    public static final DataType[] TYPES = { SubLiftType.get() };

    private final UneditableStringField mode = new UneditableStringField();
    private final UneditableNumberField vE = new UneditableNumberField();
    private final UneditableNumberField dE = new UneditableNumberField();

    @Override
    public void init() {
        nameTag = new NameTag(getFieldName());
        setStringBinding("Mode", mode, " --- ");
        setNumberBinding("vError", vE);
        setNumberBinding("dError", dE);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 1.0;
        add(nameTag, c);

        c.gridy++;
        c.weightx = 0.0;
        JLabel temp = new JLabel("Mode:");
        add(temp, c);
        c.weightx = 1.0;
        c.gridx++;
        add(mode, c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        temp = new JLabel("Rate Error:");
        add(temp, c);
        c.gridx++;
        c.weightx = 1.0;
        add(vE, c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        temp = new JLabel("Disp Error:");
        add(temp, c);
        c.weightx = 1.0;
        c.gridx++;
        add(dE, c);
    }

    @Override
    public void propertyChanged(Property property) {

    }
}