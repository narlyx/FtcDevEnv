package org.firstinspires.ftc.teamcode.POC;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Configuration;

@TeleOp(name = "HuskyLens Color Tracking", group = "poc")
public class HuskyColorTracking extends LinearOpMode {
  private final Configuration robot = new Configuration(this);

  @Override
  public void runOpMode() {
    robot.init();

    robot.huskyLens.selectAlgorithm(HuskyLens.Algorithm.COLOR_RECOGNITION);

    telemetry.addLine("Robot is ready to start");
    telemetry.update();
    waitForStart();

    while (opModeIsActive()) {
      Configuration.sampleColors selectedColor = null;
      if (gamepad1.y) {
        selectedColor = Configuration.sampleColors.YELLOW;
      }
      if (gamepad1.x) {
        selectedColor = Configuration.sampleColors.BLUE;
      }
      if (gamepad1.b) {
        selectedColor = Configuration.sampleColors.RED;
      }

      HuskyLens.Block[] blocks = robot.huskyLens.blocks();

      telemetry.addLine("To select a sample, use the following buttons:");
      telemetry.addLine("X = blue");
      telemetry.addLine("Y = yellow");
      telemetry.addLine("B = red");
      telemetry.addLine();
      telemetry.addData("Selected sample color", selectedColor);
      telemetry.addData("Samples detected", blocks.length);
      telemetry.addLine();

      double targetY = 0;
      double targetX = 0;
      double targetHeight = 0;
      double targetWidth = 0;
      Configuration.sampleColors targetColor = null;

      for (HuskyLens.Block block : blocks) {
        if (!opModeIsActive()) {
          break;
        }

        Configuration.sampleColors color = robot.sampleIds.get(block.id);

        double xOffset = (double) (block.x - 160) / 160;
        double yOffset = (double) (block.y - 120) / 120;

        if (block.width*block.height > targetWidth*targetHeight && color == selectedColor) {
          targetColor = color;
          targetX = xOffset;
          targetY = yOffset;
          targetWidth = block.width;
          targetHeight = block.height;
        }
      }

      if (selectedColor != null && targetColor == selectedColor) {
        telemetry.addLine("Found target sample");
        telemetry.addData("Sample color",targetColor);
        telemetry.addData("X",targetX);
        telemetry.addData("Y",targetY);
        telemetry.addData("Width",targetWidth);
        telemetry.addData("Height",targetHeight);
        robot.driver.setHeading(-targetY,targetX,0,.75);
      } else {
        robot.driver.setHeading(0,0,0,0);
      }

      telemetry.update();
    }
  }
}
