package org.firstinspires.ftc.teamcode.POC;

import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Configuration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Autonomous(name = "TweetyBird Replay", group = "poc")
public class TweetyBirdReplay extends LinearOpMode {
  private final Configuration robot = new Configuration(this);

  private JSONObject recordedMatch;
  private JSONArray recordedActions;

  @Override
  public void runOpMode() {
    robot.init();
    robot.initTweetyBird();

    // Loading file
    telemetry.addLine("Loading last replay...");
    telemetry.update();
    try {
      File recordFile = new File(Environment.getExternalStorageDirectory(), "tweetyBirdRecord.json");
      BufferedReader buffer = new BufferedReader(new FileReader(recordFile));
      StringBuilder builder = new StringBuilder();
      String line;
      while ((line = buffer.readLine()) != null && opModeInInit()) {
        builder.append(line).append("\n");
      }
      recordedMatch = new JSONObject(builder.toString());
      recordedActions = recordedMatch.getJSONArray("actions");
    } catch (JSONException | IOException e) {
      throw new RuntimeException(e);
    }
    telemetry.addLine("Loaded " + recordedActions.length() + " keys!");
    telemetry.addLine();

    // Waiting for start
    telemetry.addLine("Robot is ready");
    telemetry.update();
    waitForStart();

    for (int i = 0; i < recordedActions.length(); i++) {
      if (!opModeIsActive()) {
        break;
      }

      try {
        JSONObject currentAction = recordedActions.getJSONObject(i);

        if (currentAction.get("type").equals("waypoint")) {
          robot.tweetyBird.sendTargetPosition(
                  currentAction.getDouble("x"),
                  currentAction.getDouble("y"),
                  currentAction.getDouble("z")
          );
        }

        if (currentAction.get("type").equals("wait")) {
          robot.tweetyBird.waitWhileBusy();
        }
      } catch (JSONException e) {
        throw new RuntimeException(e);
      }
    }

    telemetry.update();

    while(opModeIsActive());
    robot.tweetyBird.close();
  }
}
