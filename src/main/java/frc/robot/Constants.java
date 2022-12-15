// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static final int logitechControllerPort = StormProp.getInt("LogitechControllerPort", -1);
    public static final double kTemperatureRampThreshold = StormProp.getNumber("SparkMaxTemperatureRampThreshold", 45.0);
    public static final double kTemperatureRampLimit = StormProp.getNumber("SparkMaxTemperatureRampLimit", 60.0);
    public static final double kSparkMaxCurrentLimit = StormProp.getNumber("SparkMaxCurrentLimit", 35.0);
    public static final double kSparkMaxCurrentLimit550 = StormProp.getNumber("SparkMax550CurrentLimit", 25.0);


    public static final int frontLeftDriveID = StormProp.getInt("frontLeftDriveID", 0);
    public static final int frontRightDriveID = StormProp.getInt("frontRightDriveID",0);
    public static final int backLeftDriveID = StormProp.getInt("backLeftDriveID",0);
    public static final int backRightDriveID = StormProp.getInt("backRightDriveID",0);

    public static final int frontLeftSwivelID = StormProp.getInt("frontLeftSwivelID",0);
    public static final int frontRightSwivelID = StormProp.getInt("frontRightSwivelID",0);
    public static final int backLeftSwivelID = StormProp.getInt("backLeftSwivelID",0);
    public static final int backRightSwivelID = StormProp.getInt("backRightSwivelID",0);

    public static final int frontLeftTalonID = StormProp.getInt("frontLeftTalonID",0);
    public static final int frontRightTalonID = StormProp.getInt("frontRightTalonID",0);
    public static final int backLeftTalonID = StormProp.getInt("backLeftTalonID",0);
    public static final int backRightTalonID = StormProp.getInt("backRightTalonID",0);

    public static final int frontLeftOffset = StormProp.getInt("frontLeftOffset",0);
    public static final int frontRightOffset = StormProp.getInt("frontRightOffset",0);
    public static final int backLeftOffset = StormProp.getInt("backLeftOffset",0);
    public static final int backRightOffset = StormProp.getInt("backRightOffset",0);
    public static final double WIDTH = StormProp.getNumber("RobotWidth", 0.3);
    public static final double LENGTH = StormProp.getNumber("RobotLength", 0.3);
    public static final double kWheelDiameter = StormProp.getNumber("WheelDiameter", 0.2);
    public static final double kSwivelMotorGearRatio = StormProp.getNumber("SwivelMotorGearRatio", 10.);
    public static final double kDriveMotorGearRatio = StormProp.getNumber("DriveMotorGearRatio", 10.);
    public static final double kSwivelP = StormProp.getNumber("SwivelP", 0.1);
    public static final double kSwivelI = StormProp.getNumber("SwivelI", 0.);
    public static final double kSwivelD = StormProp.getNumber("SwivelD", 0.);
    public static final double kMaxVelocity = StormProp.getNumber("MaxVelocity", 1.);
    public static final double kMaxAngularVelocity = StormProp.getNumber("MaxAngularVelocity", 1.);
}

