package com.team871.livewindow.types;

import com.team871.livewindow.widgets.SubLift;
import edu.wpi.first.smartdashboard.types.NamedDataType;

public class SubLiftType extends NamedDataType {
    public static final String LABEL = "SubLift";

    private SubLiftType() {
        super(LABEL, SubLift.class);
    }

    public static NamedDataType get() {
        if(NamedDataType.get(LABEL) != null) {
            return NamedDataType.get(LABEL);
        } else {
            return new SubLiftType();
        }
    }
}
