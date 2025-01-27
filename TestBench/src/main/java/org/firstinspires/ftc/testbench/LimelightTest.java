package org.firstinspires.ftc.testbench;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Limelight Test")
public class LimelightTest extends LinearOpMode {
    // Pulling configuration
    public Configuration robot = new Configuration(this);

    // OpMode
    @Override
    public void runOpMode() {
        // Initialization
        robot.init();

        // Initializing limelight
        robot.limelight.pipelineSwitch(0);
        robot.limelight.start();

        // Wait for start
        telemetry.addLine("Ready for start");
        telemetry.update();
        waitForStart();

        // Runtime
        while (opModeIsActive()) {
            // Polling limelight status
            LLStatus status = robot.limelight.getStatus();
            telemetry.addLine("Limelight Status >>>");
            telemetry.addData("Name", status.getName());
            telemetry.addData("Temp", status.getTemp());
            telemetry.addData("FPS", status.getFps());
            telemetry.addData("CPU Load", status.getCpu());
            telemetry.addData("RAM Load", status.getRam());
            telemetry.addLine();

            // Polling limelight pipeline
            telemetry.addLine("Limelight Pipeline >>>");
            telemetry.addData("Pipeline Type", status.getPipelineType());
            telemetry.addData("Pipeline ID", status.getPipelineIndex());

            // Polling limelight results
            LLResult result = robot.limelight.getLatestResult();
            telemetry.addLine("Limelight Result >>>");

            // Ensuring there is a result
            if (result != null) {
                // Result general data
                telemetry.addData("Result", result.toString());

                if (result.isValid()) {
                    telemetry.addData("Botpose", result.getBotpose().toString());
                }
            } else {
                telemetry.addLine("No result returned");
            }


            // Sending telemetry
            telemetry.update();
        }

        // Shutdown
        robot.limelight.stop();
    }
}
