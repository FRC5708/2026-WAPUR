// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.BallIndex;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class runBallIndex extends Command {
  private final BallIndex b_indexer;
  private final double speed;

  /** Creates a new runBallLoader. */
  public runBallIndex(BallIndex BallIndex, boolean isOutake) {
    b_indexer = BallIndex;
    speed = isOutake ? Constants.Shooter.inIndexSpeed : -Constants.Shooter.outIndexSpeed;
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(b_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    b_indexer.run(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    b_indexer.run(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
