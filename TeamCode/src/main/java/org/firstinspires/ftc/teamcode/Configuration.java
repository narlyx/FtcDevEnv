package org.firstinspires.ftc.teamcode;

import dev.narlyx.tweetybird.Odometers.ThreeWheeled;
import dev.narlyx.tweetybird.Drivers.Mecanum;
import dev.narlyx.tweetybird.TweetyBird;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorHuskyLens;

public class Configuration {
  private LinearOpMode opMode;
  private HardwareMap hwMap;

  public TweetyBird tweetyBird;
  public ThreeWheeled odometer;
  public Mecanum driver;

  public DcMotor FL, FR, BL, BR;
  public DcMotor leftEncoder, rightEncoder, centerEncoder;
  public DcMotor extendMotor;
  public IMU imu;

  public HuskyLens huskyLens;

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
    FL.setDirection(DcMotor.Direction.REVERSE);

    FR = hwMap.get(DcMotor.class, "FR");
    FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    FR.setDirection(DcMotor.Direction.FORWARD);

    BL = hwMap.get(DcMotor.class, "BL");
    BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BL.setDirection(DcMotor.Direction.REVERSE);

    BR = hwMap.get(DcMotor.class, "BR");
    BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    BR.setDirection(DcMotor.Direction.FORWARD);

    extendMotor = hwMap.get(DcMotor.class, "ExtendMotor");
    extendMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    extendMotor.setDirection(DcMotorSimple.Direction.FORWARD);

    /*
    imu = hwMap.get(IMU.class, "imu");
    imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
    )));*/

    huskyLens = hwMap.get(HuskyLens.class, "huskylens");

    odometer = new ThreeWheeled.Builder()
        .setLeftEncoder(leftEncoder)
        .setFlipLeftEncoder(false)
        .setFlipRightEncoder(true)
        .setFlipMiddleEncoder(true)
        .setRightEncoder(rightEncoder)
        .setMiddleEncoder(centerEncoder)
        .setSideEncoderDistance(6.625)
        .setMiddleEncoderOffset(-.1)
        .setEncoderTicksPerRotation(2000)
        .setEncoderWheelRadius(1.25984/2)
        .build();

    driver = new Mecanum.Builder()
        .setFrontLeftMotor(FL)
        .setFrontRightMotor(FR)
        .setBackLeftMotor(BL)
        .setBackRightMotor(BR)
        .build();
  }

  public void initTweetyBird() {
    tweetyBird = new TweetyBird.Builder()
        .setLinearOpMode(opMode)
        .setOdometer(odometer)
        .setDriver(driver)
        .setMaximumSpeed(1)
        .setMinimumSpeed(.3)
        .setDistanceBuffer(1)
        .setRotationBuffer(2)
        .setDebuggingEnabled(false)
        .setLoggingEnabled(true)
        .build();
  }

}
