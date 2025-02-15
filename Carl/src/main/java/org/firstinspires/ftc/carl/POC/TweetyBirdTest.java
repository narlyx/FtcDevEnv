package org.firstinspires.ftc.carl.POC;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.carl.Configuration;

@Autonomous(name = "TweetyBird Test",group = "poc")
public class TweetyBirdTest extends LinearOpMode {
    private final Configuration robot = new Configuration(this);

    @Override
    public void runOpMode() {
        robot.init();
        robot.initTweetyBird();

        telemetry.addLine("Robot is ready");
        telemetry.update();
        waitForStart();

        robot.tweetyBird.sendTargetPosition(-12,25,0);
        robot.tweetyBird.sendTargetPosition(-12,31,0);
        robot.tweetyBird.waitWhileBusy();
        sleep(1000);

        robot.tweetyBird.sendTargetPosition(-14,27,0);
        robot.tweetyBird.sendTargetPosition(-14+35,27,180);
        robot.tweetyBird.waitWhileBusy();
        robot.tweetyBird.sendTargetPosition(-14+35,49,180);
        robot.tweetyBird.waitWhileBusy();

        robot.tweetyBird.sendTargetPosition(29+3,49,180);
        robot.tweetyBird.waitWhileBusy();
        robot.tweetyBird.sendTargetPosition(32,25,180);
        robot.tweetyBird.sendTargetPosition(42,1,180);
        robot.tweetyBird.waitWhileBusy();

        robot.tweetyBird.sendTargetPosition(32,25,180);
        robot.tweetyBird.sendTargetPosition(32,49,180);
        robot.tweetyBird.waitWhileBusy();
        robot.tweetyBird.sendTargetPosition(32+10,49,180);
        robot.tweetyBird.waitWhileBusy();
        robot.tweetyBird.sendTargetPosition(32+10,1,180);
        robot.tweetyBird.waitWhileBusy();

        robot.tweetyBird.sendTargetPosition(42,12,180);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(26, 0, 180);
        robot.tweetyBird.waitWhileBusy();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(-12, 25, 0);
        robot.tweetyBird.sendTargetPosition(-12, 31, 0);
        robot.tweetyBird.waitWhileBusy();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(26, 0, 180);
        robot.tweetyBird.waitWhileBusy();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(-12+1, 25, 0);
        robot.tweetyBird.sendTargetPosition(-12+1, 31, 0);
        robot.tweetyBird.waitWhileBusy();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(26, 0, 180);
        robot.tweetyBird.waitWhileBusy();
        sleep(500);

        while (opModeIsActive());
        robot.tweetyBird.close();
    }
}
