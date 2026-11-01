// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallLoader extends SubsystemBase {
  /** Creates a new BallLoader. */
  private final TalonFX motor = new TalonFX(32);

  public BallLoader() {}

  public void run(double power) {
    motor.set(power);
  }
}
