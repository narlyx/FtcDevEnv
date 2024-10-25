package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "TweetyBird Hold Position")
public class TweetyBirdHold extends LinearOpMode {
    private Configuration robot = new Configuration(this);

    @Override
    public void runOpMode() {
        robot.init();
        robot.initTweetyBird();

        robot.tweetyBird.engage();

        telemetry.addLine("Robot is ready");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("X",robot.tweetyBird.getX());
            telemetry.addData("Y",robot.tweetyBird.getY());
            telemetry.addData("Z",robot.tweetyBird.getZ());
            telemetry.update();
        }

        robot.tweetyBird.stop();
    }

}
