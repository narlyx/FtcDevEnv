package org.firstinspires.ftc.teamcode;

import dev.narlyx.ftc.tweetybird.TweetyBirdProcessor;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

public class Configuration {
  private LinearOpMode opMode;
  private HardwareMap hwMap;

  public TweetyBirdProcessor tweetyBird;

  public DcMotor FL, FR, BL, BR;
  public DcMotor leftEncoder, rightEncoder, centerEncoder;
  public IMU imu;

  public Configuration(LinearOpMode opMode) {
    this.opMode = opMode;
  }

  public void init() {
    hwMap = opMode.hardwareMap;

    leftEncoder = hwMap.get(DcMotor.class, "FL");
    leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    rightEncoder = hwMap.get(DcMotor.class, "FR");
    rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    centerEncoder = hwMap.get(DcMotor.class, "BL");
    centerEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    FL = hwMap.get(DcMotor.class, "FL");
    FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FL.setDirection(DcMotor.Direction.FORWARD);

    FR = hwMap.get(DcMotor.class, "FR");
    FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FR.setDirection(DcMotor.Direction.REVERSE);

    BL = hwMap.get(DcMotor.class, "BL");
    BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BL.setDirection(DcMotor.Direction.FORWARD);

    BR = hwMap.get(DcMotor.class, "BR");
    BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BR.setDirection(DcMotor.Direction.REVERSE);

    /*
    imu = hwMap.get(IMU.class, "imu");
    imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
    )));*/

  }

  public void initTweetyBird() {
    tweetyBird = new TweetyBirdProcessor.Builder()
            // Setting opmode
            .setOpMode(opMode)

            // Hardware config
            .setFrontLeftMotor(FL)
            .setBackLeftMotor(BL)
            .setFrontRightMotor(FR)
            .setBackRightMotor(BR)

            .setLeftEncoder(leftEncoder)
            .setRightEncoder(rightEncoder)
            .setMiddleEncoder(centerEncoder)

            .flipLeftEncoder(true)
            .flipRightEncoder(false)
            .flipMiddleEncoder(false)

            .setSideEncoderDistance(6.625)
            .setMiddleEncoderOffset(-.1)

            .setTicksPerEncoderRotation(2000)
            .setEncoderWheelRadius(1.25984/2)

            // Software config
            .setMinSpeed(0.2)
            .setMaxSpeed(0.75)
            .setStartSpeed(0.4)
            .setSpeedModifier(0.025)
            .setStopForceSpeed(0.1)
            .setCorrectionOverpowerDistance(2)
            .setDistanceBuffer(.25)
            .setRotationBuffer(.1)

            .build();
  }

}
