package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "TweetyBird Benchmark")
public class TweetyBirdBenchmark extends LinearOpMode {
    private Configuration robot = new Configuration(this);

    @Override
    public void runOpMode() {
        robot.init();
        robot.initTweetyBird();

        robot.tweetyBird.engage();

        telemetry.addLine("Robot is ready");
        telemetry.update();
        waitForStart();

        telemetry.clearAll(); //28.125

        robot.tweetyBird.straightLineTo(10,28.125,0);
        //robot.tweetyBird.waitWhileBusy();

        robot.tweetyBird.straightLineTo(0,56.25,0);
        //robot.tweetyBird.waitWhileBusy();

        robot.tweetyBird.straightLineTo(10,84.375,0);
        //robot.tweetyBird.waitWhileBusy();

        robot.tweetyBird.straightLineTo(0,112.5,0);
        robot.tweetyBird.waitWhileBusy();

        sleep(2000);

        robot.tweetyBird.straightLineTo(0,0,0);
        robot.tweetyBird.waitWhileBusy();

        robot.tweetyBird.stop();
    }

}
