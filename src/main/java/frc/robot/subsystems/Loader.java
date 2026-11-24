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
public class Loader extends SubsystemBase {
  // private final MotorController motor1 = new SparkMax(10, SparkLowLevel.MotorType.kBrushless);
  // private final MotorController motor2 = new SparkMax(11, SparkLowLevel.MotorType.kBrushless);
  private final SparkMax motor1 = new SparkMax(40, MotorType.kBrushless);
  private final SparkMax motor2 = new SparkMax(41, MotorType.kBrushless);

  public Loader() {}

  public void run(double power) {
    motor1.set(power);
    motor2.set(power);
  }
}
