package com.team871.livewindow.types;

import com.team871.livewindow.widgets.SuperLift;
import edu.wpi.first.smartdashboard.types.NamedDataType;

public class SuperLiftType extends NamedDataType {
    public static final String LABEL = "SuperLift";

    private SuperLiftType() {
        super(LABEL, SuperLift.class);
    }

    public static NamedDataType get() {
        if(NamedDataType.get(LABEL) != null) {
            return NamedDataType.get(LABEL);
        } else {
            return new SuperLiftType();
        }
    }
}
