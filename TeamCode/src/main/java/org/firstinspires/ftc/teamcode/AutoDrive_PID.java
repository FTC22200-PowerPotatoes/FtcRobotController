package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="AutoDrivepid", group="Linear Opmode")
public class AutoDrive_PID extends LinearOpMode {

    DcMotor leftMotorFront;
    DcMotor rightMotorFront;
    DcMotor leftMotorBack;
    DcMotor rightMotorBack;
    double integralSum = 0.0;
    double Kp = 14.0; // These need to be tuned
    double Ki = 0.01;
    double Kd = 11.0;
    ElapsedTime timer = new ElapsedTime();
    double lastError = 0.0;
    @Override
    public void runOpMode() { //throws InterruptedException;

        leftMotorFront = hardwareMap.dcMotor.get("leftMotorFront");
        leftMotorBack = hardwareMap.dcMotor.get("leftMotorBack");
        rightMotorFront = hardwareMap.dcMotor.get("rightMotorFront");
        rightMotorBack = hardwareMap.dcMotor.get("rightMotorBack");
        // Setting Direction of motors
        leftMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotorBack.setDirection(DcMotorSimple.Direction.REVERSE);
        // Setting motors usability with encoders
        leftMotorFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        waitForStart();
        while (opModeIsActive()) {
            double power = PIDControl(1704, leftMotorFront.getCurrentPosition());
            leftMotorFront.setPower(power);
            leftMotorBack.setPower(power);
            rightMotorBack.setPower(power);
            rightMotorFront.setPower(power);



        }


    }
    public double PIDControl(double reference, double state){
        double error = reference - state;
        integralSum += (error * timer.seconds());
        double derivative = (error - lastError) / (timer.seconds());
        lastError = error;

        timer.reset();

        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }
}

