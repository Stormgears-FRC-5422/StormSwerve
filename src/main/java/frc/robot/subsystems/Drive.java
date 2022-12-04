package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.motorcontrol.StormSpark;
import frc.utils.motorcontrol.StormTalon;

import static frc.robot.Constants.*;
import static java.lang.Math.*;


import java.util.function.DoubleSupplier;

public class Drive extends SubsystemBase {
    private final StormSpark frontLeftDrive;
    private final StormSpark frontRightDrive;
    private final StormSpark backLeftDrive;
    private final StormSpark backRightDrive;

    private final StormSpark frontLeftSwivel;
    private final StormSpark frontRightSwivel;
    private final StormSpark backLeftSwivel;
    private final StormSpark backRightSwivel;

    private final StormTalon frontLeftTalon;
    private final StormTalon frontRightTalon;
    private final StormTalon backLeftTalon;
    private final StormTalon backRightTalon;

    private DoubleSupplier driveSpeedSupplier;
    private double driveSpeed;
    private DoubleSupplier turnSpeedSupplier;
    private double turnSpeed;

    public Drive() {
        System.out.println("Drive----------------------------");
        driveSpeedSupplier = () -> 0;
        turnSpeedSupplier = () -> 0;

        frontLeftDrive = new StormSpark(frontLeftDriveID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);
        frontRightDrive = new StormSpark(frontRightDriveID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);
        backLeftDrive = new StormSpark(backLeftDriveID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);
        backRightDrive = new StormSpark(backRightDriveID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);

        frontLeftSwivel = new StormSpark(frontLeftSwivelID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);
        frontRightSwivel = new StormSpark(frontRightSwivelID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);
        backLeftSwivel = new StormSpark(backLeftSwivelID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);
        backRightSwivel = new StormSpark(backRightSwivelID, StormSpark.MotorType.kBrushless, StormSpark.MotorKind.k550);

        frontLeftTalon = new StormTalon(frontLeftTalonID);
        frontRightTalon = new StormTalon(frontRightTalonID);
        backLeftTalon = new StormTalon(backLeftTalonID);
        backRightTalon = new StormTalon(backRightTalonID);

        frontLeftTalon.setOffset(frontLeftOffset);
        frontRightTalon.setOffset(frontRightOffset);
        backLeftTalon.setOffset(backLeftOffset);
        backRightTalon.setOffset(backRightOffset);
    }

    @Override
    public void periodic() {
        setAllWheelPositions(0);
        //System.out.println("frontLeftTalon: " + getPosition(frontLeftTalon));
        driveSpeed = driveSpeedSupplier.getAsDouble();
        turnSpeed = turnSpeedSupplier.getAsDouble();

        SmartDashboard.putNumber("FL Encoder", frontLeftTalon.getPosition());
        SmartDashboard.putNumber("FR Encoder", frontRightTalon.getPosition());
        SmartDashboard.putNumber("BL Encoder", backLeftTalon.getPosition());
        SmartDashboard.putNumber("BR Encoder", backRightTalon.getPosition());

//        frontLeftDrive.set(driveSpeed);
//        frontRightDrive.set(driveSpeed);
//        backLeftDrive.set(driveSpeed);
//        backRightDrive.set(driveSpeed);
//
//        frontLeftSwivel.set(turnSpeed);
//        frontRightSwivel.set(turnSpeed);
//        backLeftSwivel.set(turnSpeed);
//        backRightSwivel.set(turnSpeed);
    }

    public void setDriveSpeedSupplier(DoubleSupplier driveSpeedSupplier) {
        this.driveSpeedSupplier = driveSpeedSupplier;
    }

    public void setTurnSpeedSupplier(DoubleSupplier turnSpeedSupplier) {
        this.turnSpeedSupplier = turnSpeedSupplier;
    }

    public void setAllWheelPositions(int targetPosition) {
        setWheelPosition(frontLeftTalon, frontLeftSwivel, targetPosition);
        setWheelPosition(frontRightTalon, frontRightSwivel, targetPosition);
        setWheelPosition(backLeftTalon, backLeftSwivel, targetPosition);
        setWheelPosition(backRightTalon, backRightSwivel, targetPosition);
    }

    public void setWheelPosition(StormTalon talon, StormSpark swivel, int targetPosition) {
        int error = targetPosition - talon.getPosition();
        double Kp = 0.00025;
        double tolerance = 4;

        if ( abs(error) > tolerance) {
            swivel.set(-Kp * error);
           // swivel.set(-0.1 * signum(error));
        } else {
            swivel.set(0);
      }
    }
}
