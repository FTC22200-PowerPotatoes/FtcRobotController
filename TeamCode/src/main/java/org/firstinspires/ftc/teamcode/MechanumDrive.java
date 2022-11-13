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

@TeleOp(name="MechanumDrive", group="Linear Opmode")

public class MechanumDrive extends LinearOpMode {

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
        rightMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);

        double leftMotorFrontPower = 0.0;
        double leftMotorBackPower = 0.0;
        double rightMotorFrontPower = 0.0;
        double rightMotorBackPower = 0.0;

        waitForStart();

        while(opModeIsActive()){
            double leftStick = -gamepad1.left_stick_y;
            double rightStick = -gamepad1.right_stick_y * 1.1;

            leftMotorFrontPower = Range.clip(leftStick, -1.0, 0.6);
            leftMotorBackPower = Range.clip(leftStick, -1.0, 0.6);
            rightMotorFrontPower = Range.clip(leftStick, -1.0, 0.6);
            rightMotorBackPower = Range.clip(leftStick, -1.0, 0.6);
            double sideLeftMotorFront = Range.clip(rightStick, -1.0, 0.5);
            double sideRightMotorFront = Range.clip(rightStick, -1.0, 0.5);
            double sideLeftMotorBack = Range.clip(rightStick, -1.0, 0.5);
            double sideRightMotorBack = Range.clip(rightStick, -1.0, 0.5);


            // open the gripper on X button if not already at most open position.
            if (gamepad1.a && gripPosition < MAX_POSITION) gripPosition = gripPosition + .01;

            // close the gripper on Y button if not already at the closed position.
            if (gamepad1.b && gripPosition > MIN_POSITION) gripPosition = gripPosition - .01;
            while (gamepad1.right_bumper){
                linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
                linearSlide.setPower(0.5);
            }
            while (gamepad1.left_bumper) {
                linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
                linearSlide.setPower(0.5);
            }
            if(gamepad1.dpad_down){
                linearSlide.setPower(0);
            }



            gripServo.setPosition(Range.clip(gripPosition, MIN_POSITION, MAX_POSITION));


            //side to side
            leftMotorFront.setPower(sideLeftMotorFront);
            leftMotorBack.setPower(sideLeftMotorBack);
            rightMotorBack.setPower(sideRightMotorBack);
            rightMotorFront.setPower(sideRightMotorFront);
            //forward to backward
            leftMotorFront.setPower(leftMotorFrontPower);
            leftMotorBack.setPower(-leftMotorBackPower);
            rightMotorFront.setPower(-rightMotorFrontPower);
            rightMotorBack.setPower(rightMotorBackPower);
            // turnturnturnturnturnturn
            leftMotorFront.setPower(gamepad1.right_stick_x);
            leftMotorBack.setPower(gamepad1.right_stick_x);
            rightMotorFront.setPower(-gamepad1.right_stick_x);
            rightMotorBack.setPower(-gamepad1.right_stick_x);





        }
    }
}
