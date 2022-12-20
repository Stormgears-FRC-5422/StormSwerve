// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.utils.joysticks.StormXboxController;

//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import frc.robot.commands.DriveCommand;
//import frc.robot.commands.HomeWheels;
//import frc.robot.subsystems.Drive;
//import frc.utils.joysticks.StormLogitechController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
//  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final StormXboxController m_controller = new StormXboxController(0);

//  From the original prototype
//  private final StormLogitechController logitechController = new StormLogitechController(Constants.logitechControllerPort);
//  private final JoystickButton homeButton = new JoystickButton(logitechController, 1);
//
//  private final Drive drive = new Drive();
//
//  private final HomeWheels homeWheels = new HomeWheels(drive);
//  private final DriveCommand driveCommand = new DriveCommand(drive, logitechController::getYAxis, logitechController::getZAxis);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(m_controller.getLeftJoystickY() * m_drivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND),
            () -> -modifyAxis(m_controller.getLeftJoystickX() * m_drivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND),
            () -> -modifyAxis(m_controller.getRightJoystickX() * m_drivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND)
    ));


//    // Configure the button bindings
//    configureButtonBindings();
//    drive.setDefaultCommand(driveCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
//    // Back button zeros the gyroscope
//    new Button(m_controller::getBackButton)
//            // No requirements because we don't need to interrupt anything
//            .whenPressed(m_drivetrainSubsystem::zeroGyroscope);

//    homeButton.whenPressed(homeWheels);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new RunCommand(() -> System.out.println("This is Auto --------"));
  }

  private static double modifyAxis(double value) {
    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }

}
