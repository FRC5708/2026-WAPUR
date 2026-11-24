// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Logged
public class BallIndex extends SubsystemBase {
  /** Creates a new BallIndex. */
  private final SparkMax motor = new SparkMax(44, MotorType.kBrushless);

  public BallIndex() {}

  public void run(double power) {
    motor.set(power);
  }
}
