package com.team871.livewindow.widgets;

import com.team871.livewindow.types.CompositeLimitedSpeedControllerType;
import edu.wpi.first.smartdashboard.gui.elements.bindings.AbstractTableWidget;
import edu.wpi.first.smartdashboard.gui.elements.bindings.NumberBindable;
import edu.wpi.first.smartdashboard.livewindow.elements.NameTag;
import edu.wpi.first.smartdashboard.properties.Property;
import edu.wpi.first.smartdashboard.types.DataType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CompositeLimitedSpeedControllerWidget extends AbstractTableWidget {

    public static final DataType[] TYPES = { CompositeLimitedSpeedControllerType.get() };

    private UneditableBooleanField[] upperLimits;
    private UneditableBooleanField[] lowerLimits;
    private BooleanTableCheckBox yoloMode;
    private UneditableBooleanField atUpper;
    private UneditableBooleanField atLower;
    private BooleanTableCheckBox inverted;
    private NumberSlider controller;
    private NumberBindable speed = getTableEntryBindable("speed");
    private UneditableNumberField actualSpeed;

    private final JPanel upperHolder = new JPanel(new FlowLayout());
    private final JPanel lowerHolder = new JPanel(new FlowLayout());

    private final JButton zeroButton = new JButton("Zero");

    @Override
    public void init() {
        setLayout(new GridBagLayout());

        upperLimits = new UneditableBooleanField[10];
        lowerLimits = new UneditableBooleanField[10];

        for(int i = 0; i<upperLimits.length; i++) {
            upperLimits[i] = new UneditableBooleanField();
            setBooleanBinding("ul"+i, upperLimits[i]);

            lowerLimits[i] = new UneditableBooleanField();
            setBooleanBinding("ll"+i, lowerLimits[i]);
        }

        nameTag = new NameTag(getFieldName());
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

        zeroButton.addActionListener(e -> reset());

        yoloMode = new BooleanTableCheckBox("yoloMode");
        atUpper = new UneditableBooleanField();
        atUpper.setText(" --- ");
        setBooleanBinding("isAtUpperLimit", atUpper);

        atLower = new UneditableBooleanField();
        atLower.setText(" --- ");
        setBooleanBinding("isAtLowerLimit", atUpper);

        inverted = new BooleanTableCheckBox("inverted");

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 1;
        c.gridx = 0;
        add(nameTag, c);

        JLabel temp = new JLabel("Upper Limits: ");
        temp.setBorder(BorderFactory.createLineBorder(Color.black));
        c.gridy = 2;
        c.gridx = 0;
        add(temp, c);

        c.gridx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.LINE_START;
        upperHolder.setBorder(BorderFactory.createLineBorder(Color.black));
        add(upperHolder, c);

        for(int i = 0; i<upperLimits.length; i++) {
            upperHolder.add(upperLimits[i]);
        }

        temp = new JLabel("Lower Limits: ");
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.0;
        c.gridy = 3;
        c.gridx = 0;
        add(temp, c);

        c.gridx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 1.0;
        lowerHolder.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lowerHolder, c);

        for(int i = 0; i<lowerLimits.length; i++) {
            lowerHolder.add(lowerLimits[i]);
        }

        c.gridy = 4;
        c.gridx = 0;
        temp = new JLabel("At Upper?");
        add(temp, c);
        c.gridx = 1;
        add(atUpper, c);

        c.gridy = 5;
        c.gridx = 0;
        temp = new JLabel("At Lower?");
        add(temp, c);
        c.gridx = 1;
        add(atLower, c);

        JPanel motorPanel = new JPanel(new FlowLayout());

        c.gridy = 6;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.gridwidth = 3;
        add(motorPanel, c);
        motorPanel.add(controller);
        motorPanel.add(actualSpeed);
        motorPanel.add(zeroButton);

        c.gridwidth = 1;
        c.gridy = 7;
        c.gridx = 0;
        inverted.setText("Inverted?");
        add(inverted, c);

        c.gridx = 1;
        yoloMode.setText("YOLO?");
        add(yoloMode, c);
    }

    /**
     * Resets the slider value to zero and tells the robot
     * to turn the motor off.
     */
    public void reset() {
        controller.setBindableValue(0.0);
        speed.setBindableValue(0.0);
    }

    @Override
    public void propertyChanged(Property property) {

    }
}