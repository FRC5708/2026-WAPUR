// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Loader extends SubsystemBase {
  private final MotorController motor1 = new SparkMax(10, SparkLowLevel.MotorType.kBrushless);
  private final MotorController motor2 = new SparkMax(11, SparkLowLevel.MotorType.kBrushless);

  public Loader() {}

  public void run(double power) {}
}
