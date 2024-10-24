package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Configuration {
  private LinearOpMode opMode;
  private HardwareMap hwMap;

  public DcMotor FL, FR, BL, BR;
  public DcMotor leftEncoder, rightEncoder, centerEncoder;

  public HardwareMap(LinearOpMode opMode) {
    this.opMode = opMode;
  }

  public void init() {
    hwMap = opMode.hardwareMap;

    leftEncoder = hwMap.get(DcMotor.class, "FL");
    leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    rightEncoder = hwMap.get(DcMotor.class, "FR");
    rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    centerEncoder = hwMap.get(DcMotor.class, "BL");
    centerEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    FL = hwMap.get(DcMotor.class, "FL");
    FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FL.setDirection(DcMotor.Direction.FORWARD);

    FR = hwMap.get(DcMotor.class, "FR");
    FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FR.setDirection(DcMotor.Direction.REVERSE);

    BL = hwMap.get(DcMotor.class, "BL");
    BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BL.setDirection(DcMotor.Direction.FORWARD);

    BR = hwMap.get(DcMotor.class, "BR");
    BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BR.setDirection(DcMotor.Direction.REVERSE);

  }

}
