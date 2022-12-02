// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.utils.configfile.StormProp;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    public static final double kStickNullSize = StormProp.getNumber("StickNullSize", 0.1);
    public static final int logitechControllerPort = 0;
    public static final double kTemperatureRampThreshold = StormProp.getNumber("SparkMaxTemperatureRampThreshold", 45.0);
    public static final double kTemperatureRampLimit = StormProp.getNumber("SparkMaxTemperatureRampLimit", 60.0);
    public static final double kSparkMaxCurrentLimit = StormProp.getNumber("SparkMaxCurrentLimit", 35.0);
    public static final double kSparkMaxCurrentLimit550 = StormProp.getNumber("SparkMax550CurrentLimit", 25.0);


    public static final int frontLeftDriveID = 9;
    public static final int frontRightDriveID = 22;
    public static final int backLeftDriveID = 4;
    public static final int backRightDriveID = 8;

    public static final int frontLeftSwivelID = 2;
    public static final int frontRightSwivelID = 7;
    public static final int backLeftSwivelID = 21;
    public static final int backRightSwivelID = 32;

    public static final int frontLeftTalonID = 13;
    public static final int frontRightTalonID = 29;
    public static final int backLeftTalonID = 26;
    public static final int backRightTalonID = 6;

    public static final int frontLeftOffset=2901;
    public static final int frontRightOffset=3936;
    public static final int backLeftOffset=3391;
    public static final int backRightOffset=923;
}

