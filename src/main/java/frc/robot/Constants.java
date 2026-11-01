// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class DriveConstants {
    public static final double slowSpeed = 0.1;
    public static final double fastSpeed = 0.5;
  }

  public static class Shooter {
    public static final double inIndexSpeed = 0.5;
    public static final double outIndexSpeed = 0.5;

    public static final double loaderSpeed = 0.5;
    public static final double loaderOutSpeed = 0.5;

    public static final double defaultShooterSpeed = 0.4;
  }

  public static final double intakeSpeed = 0.5;
  public static final double outtakeSpeed = 0.5;

  public class Elevator {
    public static final int elevatorMotorID1 = 31;
    public static final int elevatorMotorID2 = 32;
    public static final int milkPlaceMotorID3 = 13;
    public static final int milkPlaceMotorID4 = 14;
    public static final double millisToL4 = 2710;
    public static final double millisDownL4 = 2100;
    public static final double millisToSpit = 250;
    public static final double millisToIntake = 800;
    public static final double ticksToL1 = 17.5;
    public static final double ticksToL2 = 34.2;
    public static final double ticksToL3 = 49.7;
    public static final double ticksToL4 = 61.3; // added 3
  }
}
