// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSpinners;

public class MoveForward extends CommandBase {
  SwerveSpinners swerve;
  double d;

  /** Creates a new moveTo. */
  public MoveForward(SwerveSpinners swerve, double d) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
    this.swerve = swerve;
    this.d = d;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    swerve.driveDistance(d, d, d, d);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return swerve.reachedPosition(d, d, d, d);
  }
}
