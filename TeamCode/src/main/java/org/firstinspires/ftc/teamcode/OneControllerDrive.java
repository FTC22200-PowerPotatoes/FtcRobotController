package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * hola
**/

@TeleOp(name="OneControllerDrive", group="Linear Opmode")

public class OneControllerDrive extends LinearOpMode {

    // Declare Motors
    private DcMotor linearSlide = null;
    private DcMotor leftMotorFront = null;
    private DcMotor leftMotorBack = null;
    private DcMotor rightMotorFront = null;
    private DcMotor rightMotorBack = null;

    CRServo contServo;
    float   leftY, rightY;
    Servo gripServo;
    double  gripPosition, contPower;
    double  MIN_POSITION = 0, MAX_POSITION = 1;


    @Override
    
    public void runOpMode()  {//throws InterruptedException
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        gripServo = hardwareMap.servo.get("gripServo");
        leftMotorFront = hardwareMap.get(DcMotor.class, "leftMotorFront");
        leftMotorBack = hardwareMap.get(DcMotor.class, "leftMotorBack");
        rightMotorFront = hardwareMap.get(DcMotor.class, "rightMotorFront");
        rightMotorBack = hardwareMap.get(DcMotor.class, "rightMotorBack");

        leftMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotorBack.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();


        while(opModeIsActive()){

        if (gamepad2.left_trigger > 0.0) {
            linearSlide.setPower(-gamepad1.left_trigger);
        }
        if (gamepad2.right_trigger > 0.0) {
            linearSlide.setPower(gamepad1.right_trigger);
        }
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




            // open the gripper on X button if not already at most open position.
            if (gamepad1.a) {
                gripServo.setPosition(0.70);
            }
            else if (gamepad1.b) {
                gripServo.setPosition(0.01);
            }
            double y = Range.clip(-gamepad1.left_stick_x, -0.5, 0.5); // Remember, this is reversed!
            double x = Range.clip(gamepad1.left_stick_y, -0.5, 0.5);
            double rx = Range.clip(gamepad1.right_stick_x, -0.5, 0.50);

            leftMotorFront.setPower(y + x + rx);
            leftMotorBack.setPower(y - x + rx);
            rightMotorFront.setPower(y - x - rx);
            rightMotorBack.setPower(y + x - rx);
            telemetry.addData("value: ", y);
            telemetry.update();
            telemetry.addData("value: ", rx);
            telemetry.update();
            telemetry.addData("value: ", x);
            telemetry.update();

            // close the gripper on Y button if not already at the closed position.



            //side to side









        }

    }
}
