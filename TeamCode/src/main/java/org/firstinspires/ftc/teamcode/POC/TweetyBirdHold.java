package org.firstinspires.ftc.teamcode.POC;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Configuration;

@Autonomous(name = "TweetyBird Hold Position", group = "poc")
public class TweetyBirdHold extends LinearOpMode {
    private final Configuration robot = new Configuration(this);

    @Override
    public void runOpMode() {
        robot.init();
        robot.initTweetyBird();

        telemetry.addLine("Robot is ready");
        telemetry.update();
        waitForStart();

        while (opModeIsActive());
        robot.tweetyBird.close();
    }

}
