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

    public static final int ticksPerRotation = StormProp.getInt("ticksPerRotation",0);
    public static final int frontLeftOffsetTicks = StormProp.getInt("frontLeftOffsetTicks",0);
    public static final int frontRightOffsetTicks = StormProp.getInt("frontRightOffsetTicks",0);
    public static final int backLeftOffsetTicks = StormProp.getInt("backLeftOffsetTicks",0);
    public static final int backRightOffsetTicks = StormProp.getInt("backRightOffsetTicks",0);

    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 1.0; // FIXME Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 1.0; // FIXME Measure and set wheelbase

    public static final int DRIVETRAIN_PIGEON_ID = 0; // FIXME Set Pigeon ID

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = frontLeftDriveID;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = frontLeftSwivelID;
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = frontLeftTalonID;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(360.0 * frontLeftOffsetTicks / ticksPerRotation);

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = frontRightDriveID;
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = frontRightSwivelID;
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = frontRightTalonID;
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(360.0 * frontRightOffsetTicks / ticksPerRotation);

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = backLeftDriveID;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = backLeftSwivelID;
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = backLeftTalonID;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(360.0 * backLeftOffsetTicks / ticksPerRotation);

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = backRightDriveID;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = backRightSwivelID;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = backRightTalonID;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(360.0 * backRightOffsetTicks / ticksPerRotation);
}