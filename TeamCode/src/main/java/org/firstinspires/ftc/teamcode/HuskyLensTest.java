package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "HuskyLens Test", group = "1")
public class HuskyLensTest extends LinearOpMode {
  private final Configuration robot = new Configuration(this);

  @Override
  public void runOpMode() {
    robot.init();

    waitForStart();

    while (opModeIsActive()) {
      Configuration.sampleColors targetColor = null;
      if (gamepad1.y) {
        targetColor = Configuration.sampleColors.YELLOW;
      }
      if (gamepad1.x) {
        targetColor = Configuration.sampleColors.BLUE;
      }
      if (gamepad1.b) {
        targetColor = Configuration.sampleColors.RED;
      }

      HuskyLens.Block[] blocks = robot.huskyLens.blocks();

      telemetry.addData("Huskylens block count", blocks.length);
      telemetry.addLine();

      for (HuskyLens.Block block : blocks) {
        Configuration.sampleColors color = robot.sampleIds.get(block.id);
        telemetry.addData("Color",color);

        double xOffset = (double) ((block.x - (block.width / 2) ) - 160) / 160;
        double yOffset = (double) ((block.y - (block.height / 2) ) - 120) / 120;
        telemetry.addData("X",xOffset);
        telemetry.addData("Y",yOffset);


        if (targetColor != null && color == targetColor) {
          robot.driver.setHeading(-yOffset,xOffset,0,.75);
        } else {
          robot.driver.setHeading(0,0,0,0);
        }

      }

      telemetry.update();
    }
  }
}
