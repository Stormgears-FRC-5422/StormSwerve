package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;

public class TurnCommand extends CommandBase {

    private final Drive drive;
    private DoubleSupplier turnSpeedSupplier;

    public TurnCommand(Drive drive, DoubleSupplier turnSpeedSupplier) {
        this.drive = drive;
        this.turnSpeedSupplier = turnSpeedSupplier;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        drive.circlePosition();
        double speed = turnSpeedSupplier.getAsDouble();
        drive.setDriveSpeed(speed * Math.abs(speed));
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
