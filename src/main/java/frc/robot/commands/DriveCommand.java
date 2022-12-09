package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {

    private DoubleSupplier driveSpeedSupplier;
    private DoubleSupplier turnSpeedSupplier;
    private Drive drive;
    private double turnPositionCount = 0;

    public DriveCommand(Drive drive, DoubleSupplier driveSpeedSupplier, DoubleSupplier turnSpeedSupplier) {
        this.drive = drive;
        this.driveSpeedSupplier = driveSpeedSupplier;
        this.turnSpeedSupplier = turnSpeedSupplier;

        addRequirements(drive);
    }


    @Override
    public void initialize() {
        drive.setDriveSpeed(driveSpeedSupplier.getAsDouble());
    }

    public void execute() {
        drive.setDriveSpeed(driveSpeedSupplier.getAsDouble());
        turnPositionCount += turnSpeedSupplier.getAsDouble() * 50;
        drive.setTurnPosition(turnPositionCount);
    }
}
