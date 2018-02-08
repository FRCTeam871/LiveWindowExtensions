package com.team871.livewindow.test;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Sam
 */
public class LiveWindowFakeRobot {
    
    private static final NetworkTable liveWindow = NetworkTable.getTable("LiveWindow");
    
    private static final ITable STATUS          = createTable(liveWindow, ".status", "LW Status"),
            
                                wrist           = createTable(liveWindow, "Wrist", "LW Subsystem"),
                                wPotentiometer  = createTable(wrist, "Potentiometer", "Analog Input"),
                                wLSC         = createTable(wrist, "LSC", "CompositeLimitedSpeedController"),
                                charlie         = createTable(wrist, "SL", "SubLift"),
                                dave         = createTable(wrist, "SSL", "SuperLift");
    
    public static void main(String[] args) {
        
        System.out.println();
        
        STATUS.putBoolean("LW Enabled", true);
        STATUS.putString("Robot", "Testing");
        wPotentiometer.putNumber("Value", 2.6);
        (new Timer()).schedule(
            new TimerTask(){
                @Override
                public void run() {
                    wPotentiometer.putNumber("Value", (Math.random()-.5) * 24);

                }}, 
            0, 500);
        
    }
    
    private static ITable createTable(ITable parent, String name, String type) {
        ITable table = parent.getSubTable(name);
        System.out.println(table);
        table.putString(".type", type);
        table.putString("Name", name);
        return table;
    }
}
