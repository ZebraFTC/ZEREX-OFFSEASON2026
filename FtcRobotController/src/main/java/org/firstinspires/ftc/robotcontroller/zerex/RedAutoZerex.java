package org.firstinspires.ftc.robotcontroller.zerex;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class RedAutoZerex extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;


    Neck arm;
    Mouth intake;
    MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        DcMotor leftArmMotor = hardwareMap.get(DcMotor.class, "leftArmMotor");
        DcMotor rightArmMotor = hardwareMap.get(DcMotor.class, "rightArmMotor");
        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake");


        arm = new Neck(leftArmMotor, rightArmMotor);
        intake = new Mouth(intakeMotor);
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);


        waitForStart();
        if (opModeIsActive()) {
            drive.targetDrive(1000, 1000, 1000, 1000, 0.6);
            double startTime = getRuntime();
            while (opModeIsActive() && drive.isBusy() && (getRuntime() - startTime < 5.0)) {
                idle();
            }
            drive.stopMotors();
            arm.up(-1.0);
            sleep(1500);
            arm.stop();
            drive.targetDrive(500, 500, 500, 500, 0.6);
            startTime = getRuntime();
            while (opModeIsActive() && drive.isBusy() && (getRuntime() - startTime < 5.0)) {
                idle();
            }
            drive.stopMotors();
            intake.outtake();
            sleep(1500);
            drive.targetDrive(-1500, -1500, -1500, -1500, 0.6);
            startTime = getRuntime();
            while (opModeIsActive() && drive.isBusy() && (getRuntime() - startTime< 5.0)) {
                idle();
            }
            drive.stopMotors();
            arm.down(-1.0);//why is both down and up negative
            sleep(1300);
            arm.stop();
            drive.targetDrive(1500, -1500, -1500, 1500, 0.6);
            startTime = getRuntime();
            while (opModeIsActive() && drive.isBusy() && (getRuntime() - startTime< 5.0)) {
                idle();
            }
            drive.stopMotors();
        }

    }

}