// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  public Joystick stick = new Joystick(0);
  private DriveSubsystem driveSubsystem;
  //private ClimbSubsystem climbSubsystem;
  //private IntakeSubsystem intakeSubsystem;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveSubsystem = new DriveSubsystem();
    //climbSubsystem = new ClimbSubsystem();
    // if something like getx needs a parameter use ()->function.call(parameters)
    driveSubsystem.setDefaultCommand(new Drive(driveSubsystem, stick::getY, stick::getX));
    // Configure the button bindings
    configureButtonBindings();

    NetworkTableInstance tableInstance = NetworkTableInstance.getDefault();
    // tableInstance.setServerTeam(4829);
    NetworkTable baseTable = tableInstance.getTable("climbZerosTable");
    
    NetworkTableEntry leftClimbZeroEntry = baseTable.getEntry("leftClimbHookZero");
    NetworkTableEntry rightClimbZeroEntry = baseTable.getEntry("rightClimbHookZero");

    NetworkTableEntry leftClimbEntry = baseTable.getEntry("leftClimbHookHeight");
    NetworkTableEntry rightClimbEntry = baseTable.getEntry("arightClimbHookHeight");

    leftClimbEntry.setDouble(69);
    rightClimbEntry.setDouble(42);

    SmartDashboard.putNumber("test", leftClimbZeroEntry.getDouble(-1));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*JoystickButton climbButton = new JoystickButton(stick, 1);
    climbButton.whenPressed(new ToggleClimbArm(climbSubsystem));
    JoystickButton intakeButton = new JoystickButton(stick, 2);
    intakeButton.whenPressed(new ToggleIntake(intakeSubsystem));*/
  }

  public Command getAutonomousCommand() {
    return null;
  }
}