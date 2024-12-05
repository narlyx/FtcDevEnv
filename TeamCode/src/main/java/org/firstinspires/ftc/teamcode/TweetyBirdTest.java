package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "TweetyBird Test",group = "1")
public class TweetyBirdTest extends LinearOpMode {
    private Configuration robot = new Configuration(this);

    @Override
    public void runOpMode() {
        robot.init();
        robot.initTweetyBird();

        telemetry.addLine("Robot is ready");
        telemetry.update();
        waitForStart();

        robot.tweetyBird.sendTargetPosition(-12,25,0);
        robot.tweetyBird.sendTargetPosition(-12,31,0);
        waitDebug();
        sleep(1000);

        robot.tweetyBird.sendTargetPosition(-14,27,0);
        robot.tweetyBird.sendTargetPosition(-14+35,27,180);
        robot.tweetyBird.sendTargetPosition(-14+35,49,180);
        waitDebug();

        robot.tweetyBird.sendTargetPosition(29+3,49,180);
        waitDebug();
        robot.tweetyBird.sendTargetPosition(32,25,180);
        robot.tweetyBird.sendTargetPosition(42,1,180);
        waitDebug();

        robot.tweetyBird.sendTargetPosition(32,25,180);
        robot.tweetyBird.sendTargetPosition(32,49,180);
        waitDebug();
        robot.tweetyBird.sendTargetPosition(32+10,49,180);
        waitDebug();
        robot.tweetyBird.sendTargetPosition(32+10,1,180);
        waitDebug();

        robot.tweetyBird.sendTargetPosition(42,12,180);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(26, 0, 180);
        telemetry.addLine("Waiting");
        waitDebug();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(-12, 25, 0);
        robot.tweetyBird.sendTargetPosition(-12, 31, 0);
        waitDebug();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(26, 0, 180);
        waitDebug();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(-12+1, 25, 0);
        robot.tweetyBird.sendTargetPosition(-12+1, 31, 0);
        waitDebug();
        sleep(500);

        robot.tweetyBird.sendTargetPosition(26, 12, 180);
        robot.tweetyBird.sendTargetPosition(26, 0, 180);
        waitDebug();
        sleep(500);


        while (opModeIsActive()) {
        }
        robot.tweetyBird.close();
    }

    private int count = 0;
    private void waitDebug() {
        count++;
        telemetry.addLine("Waiting "+count);
        telemetry.update();
        robot.tweetyBird.waitWhileBusy();
        telemetry.clearAll();
    }

}
