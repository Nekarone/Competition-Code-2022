// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveRotaters;

public class SetWheelDirections extends CommandBase {

  SwerveRotaters rotators;
  double fR, fL, bR, bL;

  /** Creates a new SetWheelDirections. */
  public SetWheelDirections(SwerveRotaters rotators, double fR, double fL, double bR, double bL) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.rotators = rotators;
    this.fR = fR;
    this.fL = fL;
    this.bR = bR;
    this.bL = bL;
    addRequirements(rotators);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rotators.setWheelDirection(fR, fL, bR, bL);
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
    return rotators.reachedPosition(fR, fL, bR, bL);
  }
}
