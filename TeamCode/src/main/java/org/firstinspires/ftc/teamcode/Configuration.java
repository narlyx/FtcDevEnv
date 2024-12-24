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

import java.util.Dictionary;
import java.util.Hashtable;

public class Configuration {
  // Classes
  private LinearOpMode opMode;
  private HardwareMap hwMap;

  // Var
  public enum sampleColors {YELLOW,BLUE,RED};
  public Dictionary<Integer, sampleColors> sampleIds;

  // TweetyBird
  public TweetyBird tweetyBird;
  public ThreeWheeled odometer;
  public Mecanum driver;

  // Hardware
  public DcMotor FL, FR, BL, BR;
  public DcMotor leftEncoder, rightEncoder, centerEncoder;
  public IMU imu;
  public HuskyLens huskyLens;

  // Constructor
  public Configuration(LinearOpMode opMode) {
    this.opMode = opMode;
  }

  // General initialization
  public void init() {
    // Setting classes
    hwMap = opMode.hardwareMap;

    // Setting vars
    sampleIds = new Hashtable<>();
    sampleIds.put(1,sampleColors.YELLOW);
    sampleIds.put(2,sampleColors.RED);
    sampleIds.put(3,sampleColors.BLUE);

    // Setting hardware
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

    /*
    imu = hwMap.get(IMU.class, "imu");
    imu.initialize(new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
    )));*/

    huskyLens = hwMap.get(HuskyLens.class, "huskylens");

    // TweetyBird classes
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

  // TweetyBird initialization
  public void initTweetyBird() {
    tweetyBird = new TweetyBird.Builder()
        .setLinearOpMode(opMode)
        .setOdometer(odometer)
        .setDriver(driver)
        .setMaximumSpeed(1)
        .setMinimumSpeed(.3)
        .setDistanceBuffer(1)
        .setRotationBuffer(2)
        .setSpeedModifier(0.035)
        .setDebuggingEnabled(false)
        .setLoggingEnabled(true)
        .build();
  }

}
