package org.firstinspires.ftc.robotcontroller.zerex;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class RedAutoZerex extends OpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;

    int step = 0;

    Neck arm;
    Mouth intake;
    MecanumDrive drive;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        DcMotor leftArmMotor = hardwareMap.get(DcMotor.class, "leftArmMotor");
        DcMotor rightArmMotor = hardwareMap.get(DcMotor.class, "rightArmMotor");
        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake");


        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        rightArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm = new Neck(leftArmMotor, rightArmMotor);
        intake = new Mouth(intakeMotor);
        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

    }

    @Override
    public void loop() {
        drive.drive(1000,1001,1000,1000,1.0);
        switch (step){

        }
    }
}
