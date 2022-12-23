// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.HomeWheels;
import frc.robot.commands.TurnCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.NavX;
import frc.utils.joysticks.StormLogitechController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final StormLogitechController logitechController = new StormLogitechController(Constants.logitechControllerPort);
  private final JoystickButton homeButton = new JoystickButton(logitechController, 1);
  private final JoystickButton turnButton = new JoystickButton(logitechController, 2);

  //create subsytems here
  private final Drive drive = new Drive();
  private final NavX navX = new NavX();

 // private final HomeWheels homeWheels = new HomeWheels(drive);
  private final DriveCommand driveCommand = new DriveCommand(drive, logitechController::getXAxis, logitechController::getYAxis);
  private final TurnCommand turnCommand = new TurnCommand(drive, logitechController::getZAxis);



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    drive.setDefaultCommand(driveCommand);
//    drive.setDefaultCommand(turnCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //homeButton.whenPressed(homeWheels);
    turnButton.whileHeld(turnCommand);
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
}
