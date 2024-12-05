package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "TweetyBird Test")
public class TweetyBirdTest extends LinearOpMode {
    private Configuration robot = new Configuration(this);

    @Override
    public void runOpMode() {
        robot.init();
        robot.initTweetyBird();

        telemetry.addLine("Robot is ready");
        telemetry.update();
        waitForStart();

        robot.tweetyBird.sendTargetPosition(0,10,0);
        robot.tweetyBird.sendTargetPosition(0,64,179);


        while (opModeIsActive()) {
        }
    }

}
