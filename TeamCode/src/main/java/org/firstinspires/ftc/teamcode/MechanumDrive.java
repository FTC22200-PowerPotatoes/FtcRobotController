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



        waitForStart();

        while(opModeIsActive()){
            if (gamepad2.left_trigger > 0.0){
                linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                linearSlide.setPower(gamepad2.left_trigger);
            }
            if (gamepad2.right_trigger > 0.0){
                linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                linearSlide.setPower(-gamepad2.right_trigger);
            }
            linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            double rightStick = Range.clip(-gamepad1.right_stick_y, -1.0, 0.6) * 1.1;
            double leftStick = Range.clip(-gamepad1.left_stick_y, -1.0, 1.0);

            double linearStick = Range.clip(-gamepad1.right_trigger, 0.0, 0.5);

            linearSlide.setPower(gamepad2.left_trigger);
            linearSlide.setPower(-gamepad2.right_trigger);





            // open the gripper on X button if not already at most open position.
            if (gamepad2.a && gripPosition < MAX_POSITION) gripPosition = gripPosition + .01;

            // close the gripper on Y button if not already at the closed position.
            if (gamepad2.b && gripPosition > MIN_POSITION) gripPosition = gripPosition - .01;





            gripServo.setPosition(Range.clip(gripPosition, MIN_POSITION, MAX_POSITION));


            //side to side
            leftMotorFront.setPower(-gamepad1.right_stick_x);
            leftMotorBack.setPower(gamepad1.right_stick_x);
            rightMotorBack.setPower(-gamepad1.right_stick_x);
            rightMotorFront.setPower(-gamepad1.right_stick_x);
            //turning
            leftMotorFront.setPower(-leftStick);
            leftMotorBack.setPower(-leftStick);
            rightMotorFront.setPower(-leftStick);
            rightMotorBack.setPower(leftStick);
            // Forwards and Backward
            leftMotorFront.setPower(-rightStick);
            leftMotorBack.setPower(rightStick);
            rightMotorFront.setPower(rightStick);
            rightMotorBack.setPower(rightStick);









        }

    }
}
