// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
<<<<<<< Updated upstream
=======
import frc.robot.Constants;
>>>>>>> Stashed changes
import frc.robot.subsystems.BallLoader;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class runBallLoader extends Command {
<<<<<<< Updated upstream
  private final BallLoader m_ballLoader;
  private final double speed;

  /** Creates a new runBallLoader. */
  public runBallLoader(BallLoader ballLoader, double speed) {
    m_ballLoader = ballLoader;
    this.speed = speed;

    addRequirements(m_ballLoader);
=======
  private final BallLoader b_loader;
  private final double speed;
  /** Creates a new runBallLoader. */
  public runBallLoader(BallLoader BallLoader, boolean isOutake) {
    b_loader = BallLoader;
    speed = isOutake ? Constants.outtakeSpeed : -Constants.intakeSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
>>>>>>> Stashed changes
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
<<<<<<< Updated upstream
    m_ballLoader.run(speed);
=======
    b_loader.run(speed);
>>>>>>> Stashed changes
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
<<<<<<< Updated upstream
    m_ballLoader.run(0);
=======
    b_loader.run(0);
>>>>>>> Stashed changes
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
