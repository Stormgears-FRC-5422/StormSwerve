package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {

//    private DoubleSupplier driveSpeedSupplier;
//    private DoubleSupplier turnSpeedSupplier;
    private Drive drive;
//    private double turnPositionCount = 0;
    private DoubleSupplier xSupplier;
    private DoubleSupplier ySupplier;

    public DriveCommand(Drive drive, DoubleSupplier xSupplier, DoubleSupplier ySupplier) {
        this.drive = drive;
//        this.driveSpeedSupplier = driveSpeedSupplier;
//        this.turnSpeedSupplier = turnSpeedSupplier;
        this.xSupplier = xSupplier;
        this.ySupplier = ySupplier;
        addRequirements(drive);
    }


    @Override
    public void initialize() {
//        turnPositionCount = 0;
    }

    public void execute() {
        double x = xSupplier.getAsDouble();
        double y = ySupplier.getAsDouble();

        double speed = (x*x) + (y*y);
        drive.setDriveSpeed(Math.sqrt(speed));

        int wheelAngle = calcWheelAngle(x, y);
        drive.setAllWheelPositions(wheelAngle);
        SmartDashboard.putNumber("X", x);
        SmartDashboard.putNumber("Y", y);
        SmartDashboard.putNumber("wheelAngle", wheelAngle);
//        double driveSpeed = driveSpeedSupplier.getAsDouble();
//        double turnSpeed = turnSpeedSupplier.getAsDouble();
//        drive.setDriveSpeed(driveSpeed * Math.abs(driveSpeed));
//        turnPositionCount += (turnSpeed * Math.abs(turnSpeed) * 50);
//        drive.setTurnPosition(turnPositionCount);
    }

    private int calcWheelAngle(double x, double y) {
        double theta = -Math.atan2(-x, y);
        double wheelAngle = (int) ((theta/(2*Math.PI)) * 4096.);
        return (int) ((wheelAngle % 4096 < 0)? wheelAngle+4096 : wheelAngle);
    }
}
