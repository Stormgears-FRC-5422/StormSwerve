package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.utils.motorcontrol.StormSpark;
import frc.utils.motorcontrol.StormTalon;

import static frc.robot.Constants.*;
import static java.lang.Math.*;

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

    private double driveSpeed;
    private final double speedDampen = 0.35;

    public void setDriveSpeed(double driveSpeed) {
        this.driveSpeed = driveSpeed;
    }

    public void setTurnPosition(double turnPosition) {
        this.turnPosition = -turnPosition;
    }

    private double turnPosition;

    public Drive() {
        System.out.println("Drive----------------------------");

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

        frontLeftTalon.setOffset(frontLeftOffsetTicks);
        frontRightTalon.setOffset(frontRightOffsetTicks);
        backLeftTalon.setOffset(backLeftOffsetTicks);
        backRightTalon.setOffset(backRightOffsetTicks);
    }

    @Override
    public void periodic() {
        //System.out.println("frontLeftTalon: " + getPosition(frontLeftTalon));

        SmartDashboard.putNumber("FL Encoder", frontLeftTalon.getPositionTicks());
        SmartDashboard.putNumber("FR Encoder", frontRightTalon.getPositionTicks());
        SmartDashboard.putNumber("BL Encoder", backLeftTalon.getPositionTicks());
        SmartDashboard.putNumber("BR Encoder", backRightTalon.getPositionTicks());

        frontLeftDrive.set(driveSpeed * speedDampen);
        frontRightDrive.set(driveSpeed * speedDampen);
        backLeftDrive.set(driveSpeed * speedDampen);
        backRightDrive.set(driveSpeed * speedDampen);

        setAllWheelPositions((int) turnPosition);
    }

    public void stopWheels() {
        frontLeftSwivel.set(0);
        frontRightSwivel.set(0);
        backLeftSwivel.set(0);
        backRightSwivel.set(0);
    }

    public void setAllWheelPositions(int targetPosition) {
        setWheelPosition(frontLeftTalon, frontLeftSwivel, targetPosition);
        setWheelPosition(frontRightTalon, frontRightSwivel, targetPosition);
        setWheelPosition(backLeftTalon, backLeftSwivel, targetPosition);
        setWheelPosition(backRightTalon, backRightSwivel, targetPosition);
    }

    public void setWheelPosition(StormTalon talon, StormSpark swivel, int targetPosition) {
        int error = targetPosition - talon.getPositionTicks();
        double Kp = 0.0005;
        double tolerance = 0.01;
        if ( abs(error / 4096.) > tolerance) {
            swivel.set(-Kp * error);
            talon.atHome = false;
           // swivel.set(-0.1 * signum(error));
        } else {
            swivel.set(0);
            talon.atHome = true;
        }
    }

    public boolean isHome() {
        return frontLeftTalon.atHome &&
                frontRightTalon.atHome &&
                backLeftTalon.atHome &&
                backRightTalon.atHome;
    }

    /**
     *
     * @return frontLeft, frontRight, backLeft, backRight
     */
    public int[] getWheelPositions() {
        return new int[] {
                frontLeftTalon.getPositionTicks(),
                frontRightTalon.getPositionTicks(),
                backLeftTalon.getPositionTicks(),
                backRightTalon.getPositionTicks()
        };
    }
}
