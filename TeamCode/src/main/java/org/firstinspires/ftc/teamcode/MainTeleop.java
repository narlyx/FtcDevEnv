package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configuration;

@TeleOp(name = "Main Teleop")
public class MainTeleop extends LinearOpMode {
  private Configuration robot = new Configuration(this);

  @Override
  public void runOpMode() {
    robot.init();

    waitForStart();

    while (opModeIsActive()) {
      double axialControl = -gamepad1.left_stick_y;
      double lateralControl = gamepad1.left_stick_x;
      double yawControl = gamepad1.right_stick_x;
      double throttleControl = (gamepad1.right_trigger/0.8)+0.2;
      double breakControl = gamepad2.left_trigger;

      setMovementPower(axialControl, lateralControl, yawControl, throttleControl);
    }
  }
  
  public void setMovementPower(double axial, double lateral, double yaw, double speed) {
    double frontLeftPower  = ((axial + lateral + yaw) * speed);
    double frontRightPower = ((axial - lateral - yaw) * speed);
    double backLeftPower   = ((axial - lateral + yaw) * speed);
    double backRightPower  = ((axial + lateral - yaw) * speed);
    robot.FL.setPower(frontLeftPower);
    robot.FR.setPower(frontRightPower);
    robot.BL.setPower(backLeftPower);
    robot.BR.setPower(backRightPower);
  }
}
