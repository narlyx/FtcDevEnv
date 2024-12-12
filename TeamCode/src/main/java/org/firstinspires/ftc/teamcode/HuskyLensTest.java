package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "HuskyLens Test", group = "1")
public class HuskyLensTest extends LinearOpMode {
  private Configuration robot = new Configuration(this);

  @Override
  public void runOpMode() {
    robot.init();

    waitForStart();

    while (opModeIsActive()) {
      HuskyLens.Block[] blocks = robot.huskyLens.blocks();
      telemetry.addData("Huskylens block count", blocks.length);
      telemetry.addLine();
      for (int i = 0; i < blocks.length; i++) {
        telemetry.addData("Block", blocks[i].toString());
      }
      telemetry.update();
    }
  }
}
