package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import static frc.robot.Constants.kPercisionSpeedScale;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DefaultDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drivetrainSubsystem;

    private final DoubleSupplier m_translationXSupplier;
    private final DoubleSupplier m_translationYSupplier;
    private final DoubleSupplier m_rotationSupplier;
    private final BooleanSupplier m_fieldRelativeSupplier;

    public DefaultDriveCommand(DrivetrainSubsystem drivetrainSubsystem,
                               DoubleSupplier translationXSupplier,
                               DoubleSupplier translationYSupplier,
                               DoubleSupplier rotationSupplier,
                               BooleanSupplier fieldRelativeSupplier) {

        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_translationXSupplier = translationXSupplier;
        this.m_translationYSupplier = translationYSupplier;
        this.m_rotationSupplier = rotationSupplier;
        this.m_fieldRelativeSupplier = fieldRelativeSupplier;

        ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");
        ShuffleboardLayout layout = tab.getLayout("Input signals", BuiltInLayouts.kList)
                .withSize(1, 4)
                .withPosition(8, 0);
        layout.addNumber("tX", this.m_translationXSupplier);
        layout.addNumber("tY", this.m_translationYSupplier);
        layout.addNumber("rot", this.m_rotationSupplier);
        layout.addBoolean("notFieldRelative", this.m_fieldRelativeSupplier);
        layout.addNumber("gyro", () -> m_drivetrainSubsystem.getGyroscopeRotation().getDegrees());

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void execute() {
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        double m_tX = m_translationXSupplier.getAsDouble();
        double m_tY = m_translationYSupplier.getAsDouble();
        double m_rot = m_rotationSupplier.getAsDouble();
        boolean m_fieldRelative = m_fieldRelativeSupplier.getAsBoolean();
        Rotation2d m_gyro = m_drivetrainSubsystem.getGyroscopeRotation();

        if (m_fieldRelative == false)
            m_drivetrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        m_tX,
                        m_tY,
                        m_rot,
                        m_gyro
                )
            );
        else
            m_drivetrainSubsystem.drive(
                    new ChassisSpeeds(
                            m_tX * kPercisionSpeedScale,
                            m_tY * kPercisionSpeedScale,
                            m_rot * kPercisionSpeedScale
                    )
            );
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
