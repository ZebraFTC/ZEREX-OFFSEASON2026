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
                telemetry.addData("Status", "Driving by ticks...");
                telemetry.update();
                idle();
            }
            drive.stopMotors();
            arm.up(-1.0);
            telemetry.addData("Arm status", "Moving Up");
            telemetry.update();

            sleep(10000);

            arm.up(1);
        }

    }

}