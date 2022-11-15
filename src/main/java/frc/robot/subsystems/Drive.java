package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import java.util.function.DoubleSupplier;

public class Drive extends SubsystemBase {
    private final CANSparkMax frontLeftDrive;
    private final CANSparkMax frontLeftSwivel;
    private final CANSparkMax frontRightDrive;
    private final CANSparkMax frontRightSwivel;
    private final CANSparkMax backLeftDrive;
    private final CANSparkMax backLeftSwivel;
    private final CANSparkMax backRightDrive;
    private final CANSparkMax backRightSwivel;

    private DoubleSupplier driveSpeedSupplier;
    private double driveSpeed;
    private DoubleSupplier turnSpeedSupplier;
    private double turnSpeed;

    public Drive() {
        driveSpeedSupplier = () -> 0;
        turnSpeedSupplier = () -> 0;

        frontLeftDrive = new CANSparkMax(frontLeftDriveID, CANSparkMaxLowLevel.MotorType.kBrushed);
        frontRightDrive = new CANSparkMax(frontRightDriveID, CANSparkMaxLowLevel.MotorType.kBrushed);
        backLeftDrive = new CANSparkMax(backLeftDriveID, CANSparkMaxLowLevel.MotorType.kBrushed);
        backRightDrive = new CANSparkMax(backRightDriveID, CANSparkMaxLowLevel.MotorType.kBrushed);

        frontLeftSwivel = new CANSparkMax(frontLeftSwivelID, CANSparkMaxLowLevel.MotorType.kBrushed);
        frontRightSwivel = new CANSparkMax(frontRightSwivelID, CANSparkMaxLowLevel.MotorType.kBrushed);
        backLeftSwivel = new CANSparkMax(backLeftSwivelID, CANSparkMaxLowLevel.MotorType.kBrushed);
        backRightSwivel = new CANSparkMax(backRightSwivelID, CANSparkMaxLowLevel.MotorType.kBrushed);
    }

    @Override
    public void periodic() {
        driveSpeed = driveSpeedSupplier.getAsDouble();
        turnSpeed = turnSpeedSupplier.getAsDouble();
        System.out.println("driveSpeed=" + driveSpeed + "  turnSpeed=" + turnSpeed);

        frontLeftDrive.set(driveSpeed);
        frontRightDrive.set(driveSpeed);
        backLeftDrive.set(driveSpeed);
        backRightDrive.set(driveSpeed);

        frontLeftSwivel.set(turnSpeed);
        frontRightSwivel.set(turnSpeed);
        backLeftSwivel.set(turnSpeed);
        backRightSwivel.set(turnSpeed);
    }

    public void setDriveSpeedSupplier(DoubleSupplier driveSpeedSupplier) {
        this.driveSpeedSupplier = driveSpeedSupplier;
    }

    public void setTurnSpeedSupplier(DoubleSupplier turnSpeedSupplier) {
        this.turnSpeedSupplier = turnSpeedSupplier;
    }
}
