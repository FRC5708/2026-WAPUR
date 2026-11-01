// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

@Logged
public class Shooter extends SubsystemBase {
  /** Creates a new ShootBall. */
  private final TalonFX motor = new TalonFX(31);

  private DoubleSupplier speedSupplier;

  public Shooter(DoubleSupplier supplier) {
    speedSupplier = supplier;
  }

  public void run(boolean stop) {
    if (stop) {
      motor.set(0);
    } else {
      motor.set(speedSupplier.getAsDouble());
    }
  }
}
