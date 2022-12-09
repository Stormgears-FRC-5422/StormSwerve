package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.utils.motorcontrol.StormSpark;
import frc.utils.motorcontrol.StormTalon;

import static java.lang.Math.abs;

public class HomeWheels extends CommandBase {

    private final Drive drive;

    public HomeWheels(Drive drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.setAllWheelPositions(0);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Home Command Done ------------------------");
//        drive.stopWheels();
    }

    @Override
    public boolean isFinished() {
        System.out.println(drive.isHome());
        return drive.isHome();
    }
}
