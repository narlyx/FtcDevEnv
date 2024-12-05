package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Entry class
 */
@TeleOp(name = "Main Teleop")
public class MainTeleop extends LinearOpMode {
  // Loading robot configuration
  private Configuration robot = new Configuration(this);

  /**
   * Runtime
   */
  @Override
  public void runOpMode() {
    // Starting robot
    robot.init();

    // Waiting for drivers to start
    telemetry.addLine("Robot is ready");
    telemetry.update();
    waitForStart();

    // Runtime loop
    while (opModeIsActive()) {
      // Controls
      double axialControl = -gamepad1.left_stick_y;
      double lateralControl = gamepad1.left_stick_x;
      double yawControl = gamepad1.right_stick_x;
      double throttleControl = (gamepad1.right_trigger/0.8)+0.2;
      boolean fcdReset = gamepad1.left_bumper;

      // Field centric yaw reset
      if (fcdReset) {
        robot.odometer.resetTo(robot.odometer.getX(),robot.odometer.getY(),0);
      }

      // Field centric calculations
      double gamepadRad = Math.atan2(lateralControl, axialControl);
      double gamepadHypot = Range.clip(Math.hypot(lateralControl, axialControl), 0, 1);
      double robotRadians = robot.odometer.getZ();
      double targetRad = gamepadRad - robotRadians;
      double axial = Math.cos(targetRad)*gamepadHypot;
      double lateral = Math.sin(targetRad)*gamepadHypot;

      // Setting motor powers
      setMovementPower(axial, lateral, yawControl, throttleControl);

      // Telemetry
      telemetry.addData("Z",robot.odometer.getZ());
      telemetry.addData("Left Encoder",robot.leftEncoder.getCurrentPosition());
      telemetry.addData("Right Encoder",robot.rightEncoder.getCurrentPosition());
      telemetry.addData("Middle Encoder",robot.centerEncoder.getCurrentPosition());
      telemetry.update();
    }
  }

  /**
   * Dynamically powers all four motors
   * @param axial
   * @param lateral
   * @param yaw
   * @param speed
   */
  public void setMovementPower(double axial, double lateral, double yaw, double speed) {
    if (axial == 0 && lateral == 0 && yaw == 0) {
      setBreaking(true);
    } else {
      setBreaking(false);
    }

    double frontLeftPower  = ((axial + lateral + yaw) * speed);
    double frontRightPower = ((axial - lateral - yaw) * speed);
    double backLeftPower   = ((axial - lateral + yaw) * speed);
    double backRightPower  = ((axial + lateral - yaw) * speed);
    robot.FL.setPower(frontLeftPower);
    robot.FR.setPower(frontRightPower);
    robot.BL.setPower(backLeftPower);
    robot.BR.setPower(backRightPower);
  }

  /**
   * Toggles zero power breaking for all four motors
   * @param choice
   */
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
