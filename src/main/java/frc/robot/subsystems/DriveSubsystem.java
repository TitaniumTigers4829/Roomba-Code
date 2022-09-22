// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class DriveSubsystem extends SubsystemBase {
  public final WPI_TalonFX m_frontLeftDrive;
  public final WPI_TalonFX m_backLeftDrive;
  public final MotorControllerGroup m_leftDrive;

  public final WPI_TalonFX m_frontRightDrive;
  public final WPI_TalonFX m_backRightDrive;
  public final MotorControllerGroup m_rightDrive;

  public final DifferentialDrive m_robotDrive;

  NetworkTableInstance tableInstance;
  NetworkTable baseTable;
  NetworkTableEntry entry;

  public DriveSubsystem() {
    m_frontLeftDrive = new WPI_TalonFX(2);
    m_frontLeftDrive.setInverted(true);
    m_backLeftDrive = new WPI_TalonFX(0);
    m_backLeftDrive.setInverted(true);
    m_leftDrive = new MotorControllerGroup(m_frontLeftDrive, m_backLeftDrive);

    m_frontRightDrive = new WPI_TalonFX(3);
    m_frontRightDrive.setInverted(true);
    m_backRightDrive = new WPI_TalonFX(1);
    m_backRightDrive.setInverted(true);
    m_rightDrive = new MotorControllerGroup(m_frontRightDrive, m_backRightDrive);

    m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);

    this.tableInstance = NetworkTableInstance.getDefault();
    this.baseTable = this.tableInstance.getTable("climbZerosTable");
    this.entry = this.baseTable.getEntry("leftClimbHookZero");
  }


  @Override
  public void periodic() {
    SmartDashboard.putNumber("test", entry.getDouble(-1));
  }

  /**Arcade Joystick. 
   * @param speed throttle speed.
   * @param rotation direction; 1 is turning clockwise.*/
  public void drive(double speed, double rotation) {
    m_robotDrive.arcadeDrive(speed, -rotation);
  }

  //Stops the motors.
  public void stopMotors() {
    m_robotDrive.stopMotor();
  }

}