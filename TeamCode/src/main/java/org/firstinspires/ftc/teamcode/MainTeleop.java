package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Configuration;

@TeleOp(name = "Main Teleop")
public class MainTeleop extends LinearOpMode {
  private Configuration robot = new Configuration(this);

  @Override
  public void runOpMode() {
    robot.init();

    setBreaking(true);

    waitForStart();

    while (opModeIsActive()) {
      double axialControl = -gamepad1.left_stick_y;
      double lateralControl = gamepad1.left_stick_x;
      double yawControl = gamepad1.right_stick_x;
      double throttleControl = (gamepad1.right_trigger/0.8)+0.2;

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
