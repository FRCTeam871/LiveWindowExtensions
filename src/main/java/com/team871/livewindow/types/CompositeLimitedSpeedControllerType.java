package com.team871.livewindow.types;

import com.team871.livewindow.widgets.CompositeLimitedSpeedControllerWidget;
import edu.wpi.first.smartdashboard.types.NamedDataType;
import edu.wpi.first.smartdashboard.types.named.AccelerometerType;

public class CompositeLimitedSpeedControllerType extends NamedDataType {
    public static final String LABEL = "CompositeLimitedSpeedController";

    private CompositeLimitedSpeedControllerType() {
        super(LABEL, CompositeLimitedSpeedControllerWidget.class);
    }

    public static NamedDataType get() {
        if(NamedDataType.get(LABEL) != null) {
            return NamedDataType.get(LABEL);
        } else {
            return new CompositeLimitedSpeedControllerType();
        }
    }
}
