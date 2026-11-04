// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
<<<<<<< Updated upstream
import edu.wpi.first.epilogue.Logged;
=======

>>>>>>> Stashed changes
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Logged
public class BallLoader extends SubsystemBase {
  /** Creates a new BallLoader. */
<<<<<<< Updated upstream
  private final TalonFX motor = new TalonFX(32);
=======

  private final TalonFX motor01 = new TalonFX(54);
  private final TalonFX motor10 = new TalonFX(57);
>>>>>>> Stashed changes

  public BallLoader() {}

  public void run(double power) {
<<<<<<< Updated upstream
    motor.set(power);
=======
    motor01.set(power);
    motor10.set(power);
>>>>>>> Stashed changes
  }
}
