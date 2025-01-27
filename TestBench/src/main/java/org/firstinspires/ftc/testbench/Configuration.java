package org.firstinspires.ftc.testbench;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Configuration {
    // Classes
    public LinearOpMode opMode;
    public HardwareMap hwMap;
    public FtcDashboard ftcDashboard;

    // Constructor
    public Configuration(LinearOpMode opMode) {
        // Setting current OpMode
        this.opMode = opMode;

        // Hooking ftc dashboard telemetry
        ftcDashboard = FtcDashboard.getInstance();
        opMode.telemetry = new MultipleTelemetry(opMode.telemetry, ftcDashboard.getTelemetry());
    }

    // Hardware devices
    public Limelight3A limelight;

    // Initialization phase
    public void init() {
        // Setting hardware map
        this.hwMap = opMode.hardwareMap;

        // Initialize hardware devices
        limelight = hwMap.get(Limelight3A.class, "Limelight");
    }
}
