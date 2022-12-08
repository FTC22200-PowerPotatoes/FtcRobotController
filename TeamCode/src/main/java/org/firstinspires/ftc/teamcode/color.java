package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="color")

public class color extends LinearOpMode {
    private ColorSensor color;

    public void runOpMode() throws InterruptedException {
        float currentColor;
        float parkingSpot = 0;

        color = hardwareMap.get(ColorSensor.class, "color");
        waitForStart();
        while (opModeIsActive()) {
            currentColor = JavaUtil.rgbToHue(color.red(), color.green(), color.blue());
            telemetry.addData("Hue", color);
            telemetry.update();

            // blue
            if (currentColor < 280 && currentColor > 250) {
                //blue
                parkingSpot = 1;
                telemetry.addData("parking spot:", parkingSpot);
            } else if (currentColor < 85 && currentColor > 55) {
                parkingSpot = 2;
                telemetry.addData("parking spot:", parkingSpot);
            }
            else if (currentColor < 155 && currentColor > 100){
                parkingSpot = 3;
                telemetry.addData("parking spot:", parkingSpot);
            }

        }

    }
}
