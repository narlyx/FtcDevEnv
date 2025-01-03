package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Configuration;

/**
 * Entry class
 */
@TeleOp(name = "Main Teleop")
public class MainTeleop extends LinearOpMode {
  // Loading robot configuration
  private final Configuration robot = new Configuration(this);

  // Public vars
  public static double pullRad = 0, pullStrength = 0;

  /**
   * Runtime
   */
  @Override
  public void runOpMode() {

    // Class for operations on gamepad 1
    class Gamepad1 extends Thread {
      private final LinearOpMode opMode;
      public Gamepad1(LinearOpMode opMode) {
        this.opMode = opMode;
      }

      @Override
      public void run() {
        while (opMode.opModeIsActive()) {
          // Controls
          double axialControl = -gamepad1.left_stick_y;
          double lateralControl = gamepad1.left_stick_x;
          double yawControl = gamepad1.right_stick_x;
          double throttleControl = (gamepad1.right_trigger / 0.8) + 0.2;
          boolean fcdReset = gamepad1.left_bumper;

          // Field centric yaw reset
          if (fcdReset) {
            robot.odometer.resetTo(robot.odometer.getX(), robot.odometer.getY(), 0);
          }

          // Field centric calculations
          double gamepadRad = Math.atan2(lateralControl, axialControl);
          double gamepadHypot = Range.clip(Math.hypot(lateralControl, axialControl), 0, 1);
          double robotRadians = robot.odometer.getZ();
          double targetRad = gamepadRad - robotRadians;

          // Combining pull
          double finalRad = targetRad - (((Math.abs(targetRad - pullRad)) * (targetRad / Math.abs(targetRad))) * pullStrength);

          // Final calculations
          double axial = Math.cos(finalRad) * gamepadHypot;
          double lateral = Math.sin(finalRad) * gamepadHypot;

          // Sending movement to drivetrain
          setMovementPower(axial, lateral, yawControl, throttleControl);
        }
      }
    }

    // Class for operations on gamepad 2
    class Gamepad2 extends Thread {
      private final LinearOpMode opMode;
      public Gamepad2(LinearOpMode opMode) {
        this.opMode = opMode;
      }

      @Override
      public void run() {
        while (opMode.opModeIsActive()) {
          // Controls
          double axialPullControl = -gamepad2.left_stick_y;
          double lateralPullControl = -gamepad2.left_stick_x;

          pullRad = Math.atan2(lateralPullControl, axialPullControl);
          pullStrength = Range.clip(Math.hypot(lateralPullControl, axialPullControl), 0, 1);
        }
      }
    }

    // Starting robot
    robot.init();

    // Creating threads
    Gamepad1 thread1 = new Gamepad1(this);
    Gamepad2 thread2 = new Gamepad2(this);

    // Waiting for drivers to start
    telemetry.addLine("Robot is ready for liftoff");
    telemetry.addLine();
    telemetry.addLine(">>> Controls for driver 1");
    telemetry.addData("Axial+Lateral", "Left stick X+Y");
    telemetry.addData("Yaw", "Right stick X");
    telemetry.addData("Speed", "Right trigger");
    telemetry.addData("Reset field centric", "Left bumper");
    telemetry.addLine();
    telemetry.addLine(">>> Controls for driver 2");
    telemetry.addData("Pull direction+strength (manual)", "Left stick X+Y");
    telemetry.update();
    waitForStart();

    // Starting threads
    thread1.start();
    thread2.start();

    // Operations separate from both threads
    while (opModeIsActive()) {
      telemetry.addLine(">>> Thread Status");
      telemetry.addData("Gamepad 1", thread1.getState());
      telemetry.addData("Gamepad 2", thread2.getState());
      telemetry.addLine();
      telemetry.addLine(">>> Position");
      telemetry.addLine(
          "X: "+robot.odometer.getX()+
          "Y: "+robot.odometer.getY()+
          "Z: "+robot.odometer.getZ());
      telemetry.update();
    }

    // Shutting down
    thread1.interrupt();
    thread2.interrupt();
  }

  public void setMovementPower(double axial, double lateral, double yaw, double speed) {
    setBreaking(axial == 0 && lateral == 0 && yaw == 0);

    double frontLeftPower  = ((axial + lateral + yaw) * speed);
    double frontRightPower = ((axial - lateral - yaw) * speed);
    double backLeftPower   = ((axial - lateral + yaw) * speed);
    double backRightPower  = ((axial + lateral - yaw) * speed);
    robot.FL.setPower(frontLeftPower);
    robot.FR.setPower(frontRightPower);
    robot.BL.setPower(backLeftPower);
    robot.BR.setPower(backRightPower);
  }

  public void setBreaking(boolean choice) {
    if (choice) {
      robot.FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      robot.FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      robot.BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      robot.BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    } else {
      robot.FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
      robot.FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
      robot.BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
      robot.BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
  }
}
