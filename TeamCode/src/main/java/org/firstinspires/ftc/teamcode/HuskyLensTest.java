package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "HuskyLens Test", group = "1")
public class HuskyLensTest extends LinearOpMode {
  private final Configuration robot = new Configuration(this);

  @Override
  public void runOpMode() {
    robot.init();

    waitForStart();

    while (opModeIsActive()) {
      HuskyLens.Block[] blocks = robot.huskyLens.blocks();
      telemetry.addData("Huskylens block count", blocks.length);
      telemetry.addLine();
      for (HuskyLens.Block block : blocks) {
        telemetry.addData("Block", block.toString());
      }
      telemetry.update();
    }
  }
}
