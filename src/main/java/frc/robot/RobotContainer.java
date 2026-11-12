// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveRequest;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.commands.FollowPathCommand;
import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
import frc.robot.commands.CreepMode;
import frc.robot.commands.Intake;
// import frc.robot.commands.ZeroEncoders;
// import frc.robot.commands.changeSetpoint;
import frc.robot.commands.elevatorDown;
import frc.robot.commands.elevatorUp;
import frc.robot.commands.runBallLoader;
import frc.robot.commands.runBallShooter;
// import frc.robot.commands.manualToggly;
import frc.robot.commands.travelToSetpoint;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.BallIndex;
import frc.robot.subsystems.BallLoader;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.Loader;
import frc.robot.subsystems.Shooter;

@Logged
public class RobotContainer {
  private static Loader m_Loader;
  private static ElevatorSubsystem m_elevator;
  private static BallLoader m_BallLoader;
  private static BallIndex m_BallIndexer;
  private static Shooter m_ShootBall;

  private double shootSpeed = Constants.Shooter.defaultShooterSpeed;
  private double MaxSpeed =
      1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond); // kSpeedAt12Volts desired top speed
  private double MaxAngularRate =
      RotationsPerSecond.of(0.75)
          .in(RadiansPerSecond); // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final SwerveRequest.RobotCentric drive =
      new SwerveRequest.RobotCentric()
          .withDeadband(MaxSpeed * 0.1)
          .withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
          .withDriveRequestType(
              DriveRequestType.OpenLoopVoltage); // Use open-loop control for drive motors
  // private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  // private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();

  private final Telemetry logger = new Telemetry(MaxSpeed);

  private final CommandXboxController driverController = new CommandXboxController(0);
  private final CommandXboxController operatorController = new CommandXboxController(1);

  public final CommandSwerveDrivetrain drivetrain = TunerConstants.createDrivetrain();

  private final SendableChooser<Command> autoChooser;

  public RobotContainer() {
    m_elevator = new ElevatorSubsystem();
    m_Loader = new Loader();
    m_BallIndexer = new BallIndex();
    m_ShootBall = new Shooter(() -> shootSpeed);
    m_BallLoader = new BallLoader();

    // drivetrain.
    autoChooser = AutoBuilder.buildAutoChooser("Tests");
    SmartDashboard.putData("Auto Chooser", autoChooser);

    configureBindings();

    // Warmup PathPlanner to avoid Java pauses     comment was copied from example code for
    // pathplanner
    CommandScheduler.getInstance().schedule(FollowPathCommand.warmupCommand());
  }

  private void configureBindings() {
    operatorController
        .rightBumper()
        .whileTrue(new runBallLoader(m_BallLoader, Constants.Shooter.loaderSpeed));
    operatorController
        .leftBumper()
        .whileTrue(
            new runBallLoader(m_BallLoader, -Constants.Shooter.loaderOutSpeed)); // outtake balls
    operatorController.rightTrigger().whileTrue(new runBallShooter(m_ShootBall));
    // operatorController.povUp().onTrue(new changeShootSpeed(m_ShootBall, 0.1));
    // operatorController.povDown().onTrue(new changeShootSpeed(m_ShootBall, -0.1));
    operatorController
        .povUp()
        .onTrue(
            Commands.runOnce(
                () -> {
                  shootSpeed += 0.1;
                  if (shootSpeed > 1.0) {
                    shootSpeed = 1;
                  }
                }));
    operatorController
        .povDown()
        .onTrue(
            Commands.runOnce(
                () -> {
                  shootSpeed -= 0.1;
                  if (shootSpeed < 0.0) {
                    shootSpeed = 0;
                  }
                }));

    // intake/outake
    driverController.rightBumper().whileTrue(new Intake(m_Loader, false));
    driverController.leftBumper().whileTrue(new Intake(m_Loader, true));

    // elevator
    m_elevator.setDefaultCommand(new travelToSetpoint(m_elevator));
    driverController.leftTrigger().whileTrue(new elevatorDown(m_elevator, -.53));
    driverController.rightTrigger().whileTrue(new elevatorUp(m_elevator, .53));
    //  driverController.x().onTrue(new changeSetpoint(m_elevator, 0));
    //  driverController.povUp().onTrue(new changeSetpoint(m_elevator, 1));
    //  driverController.povRight().onTrue(new changeSetpoint(m_elevator, 2));
    //  driverController.povDown().onTrue(new changeSetpoint(m_elevator, 3));
    //  driverController.povLeft().onTrue(new changeSetpoint(m_elevator, 4));
    // driverController.y().onTrue(new manualToggly(m_elevator));
    // driverController.back().onTrue(new ZeroEncoders(m_elevator));

    // // Note that X is defined as forward according to WPILib convention,
    // // and Y is defined as to the left according to WPILib convention.
    drivetrain.setDefaultCommand(
        drivetrain.applyRequest(
            () ->
                drive
                    .withVelocityX(
                        -driverController.getLeftY()
                            * MaxSpeed
                            * getSpeedScalar() // Drive forward with negative Y (forward)
                        -driverController.getLeftX()
                            * MaxSpeed
                            * getSpeedScalar()) // Drive left with negative X (left)
                    .withRotationalRate(
                        -driverController.getRightX()
                            * MaxAngularRate) // Drive counterclockwise with negative X (left)
            ));
    // Idle while the robot is disabled. This ensures the configured
    // neutral mode is applied to the drive motors while disabled.
    final var idle = new SwerveRequest.Idle();
    RobotModeTriggers.disabled()
        .whileTrue(drivetrain.applyRequest(() -> idle).ignoringDisable(true));
    // toggle creep mode
    driverController
        .leftStick()
        .and(driverController.rightStick())
        .onTrue((new CreepMode(drivetrain)));
    // Reset the field-centric heading on left bumper press.
    driverController.back().onTrue(drivetrain.runOnce(drivetrain::seedFieldCentric));

    drivetrain.registerTelemetry(logger::telemeterize);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  private double getSpeedScalar() {
    return drivetrain.getCreepMode()
        ? Constants.DriveConstants.fastSpeed
        : Constants.DriveConstants.slowSpeed;
  }
}
