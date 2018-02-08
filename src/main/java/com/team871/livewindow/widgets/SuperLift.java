package com.team871.livewindow.widgets;

import com.team871.livewindow.types.SuperLiftType;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.livewindow.elements.NameTag;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SuperLift extends AbstractTableWidget {
    public static final DataType[] TYPES = { SuperLiftType.get() };

    private final UneditableStringField setPos = new UneditableStringField();
    private NumberTableField spGround;
    private NumberTableField spLowSwitch;
    private NumberTableField spScaleLow;
    private NumberTableField spScaleMid;
    private NumberTableField spScaleHigh;
    private NumberTableField height;

    private NumberSlider controller;
    private NumberBindable speed = getTableEntryBindable("speed");
    private UneditableNumberField actualSpeed;
    private final JButton zeroButton = new JButton("Zero");

    @Override
    public void init() {
        nameTag = new NameTag(getFieldName());
        setStringBinding("currentSetpoint", setPos, " --- ");
        spGround = new NumberTableField("GROUND");
        spLowSwitch = new NumberTableField("LOW_SWITCH");
        spScaleLow = new NumberTableField("SCALE_LOW");
        spScaleMid = new NumberTableField("SCALE_MID");
        spScaleHigh = new NumberTableField("SCALE_HIGH");
        height = new NumberTableField("height");

        controller = new NumberSlider(speed);
        controller.setMin(-1.0);
        controller.setMax(1.0);
        controller.setBindableValue(0.0);
        controller.setSnapToTicks(false);
        controller.setMajorTickSpacing(50);
        controller.setPaintTicks(true);

        actualSpeed = new UneditableNumberField();
        actualSpeed.setText("0.0");
        actualSpeed.setColumns(4);
        actualSpeed.setEditable(false);
        setNumberBinding("speed", new NumberMultiBindable(actualSpeed));

        controller.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    zeroButton.doClick();
                }
            }
        });

        zeroButton.addActionListener(e -> {
            speed.setBindableValue(0.0);
            controller.setBindableValue(0.0);
        });

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 1.0;
        add(nameTag, c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        JLabel temp = new JLabel("Height:");
        add(temp, c);
        c.gridx++;
        c.weightx = 1.0;
        add(height, c);

        JPanel motorPanel = new JPanel(new FlowLayout());

        c.gridy++;
        c.gridx = 0;
        c.weightx = 1.0;
        c.gridwidth = 2;
        add(motorPanel, c);
        motorPanel.add(controller);
        motorPanel.add(actualSpeed);
        motorPanel.add(zeroButton);

        c.gridy++;
        c.weightx = 0.0;
        temp = new JLabel("Setpoint:");
        add(temp, c);
        c.weightx = 1.0;
        c.gridx++;
        add(setPos, c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        temp = new JLabel("Sp Ground:");
        add(temp, c);
        c.gridx++;
        c.weightx = 1.0;
        add(spGround, c);
        c.weightx = 0.0;
        c.gridx++;
        add(new JButton(new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent e) {
                spGround.setBindableValue((0.0 * 12.0) + 0.00);
            }
        }), c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        temp = new JLabel("SP Low Switch:");
        add(temp, c);
        c.weightx = 1.0;
        c.gridx++;
        add(spLowSwitch, c);
        c.weightx = 0.0;
        c.gridx++;
        add(new JButton(new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent e) {
                spLowSwitch.setBindableValue((1.0 * 12.0) + 6.75);
            }
        }), c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        temp = new JLabel("SP Scale Low:");
        add(temp, c);
        c.weightx = 1.0;
        c.gridx++;
        add(spScaleLow, c);
        c.weightx = 0.0;
        c.gridx++;
        add(new JButton(new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent e) {
                spScaleLow.setBindableValue((4.0 * 12.0) + 4.00);
            }
        }), c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        temp = new JLabel("SP Scale Mid:");
        add(temp, c);
        c.weightx = 1.0;
        c.gridx++;
        add(spScaleMid, c);
        c.weightx = 0.0;
        c.gridx++;
        add(new JButton(new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent e) {
                spScaleMid.setBindableValue((5.0 * 12.0) + 4.00);
            }
        }), c);

        c.gridy++;
        c.gridx = 0;
        c.weightx = 0.0;
        temp = new JLabel("SP Scale High:");
        add(temp, c);
        c.weightx = 1.0;
        c.gridx++;
        add(spScaleHigh, c);
        c.weightx = 0.0;
        c.gridx++;
        add(new JButton(new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent e) {
                spScaleHigh.setBindableValue((6.0 * 12.0) + 4.00);
            }
        }), c);
    }

    @Override
    public void propertyChanged(Property property) {

    }
}
