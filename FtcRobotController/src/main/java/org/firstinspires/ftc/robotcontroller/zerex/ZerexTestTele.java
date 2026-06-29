package org.firstinspires.ftc.robotcontroller.zerex;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class  ZerexTestTele extends OpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor leftArmMotor;
    private DcMotor rightArmMotor;
    private DcMotor intakeMotor;

    private double drive;
    private double strafe;
    private double turn;
    private final double SPEED = 1.0;

    Neck arm;
    Mouth intake;


    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        leftArmMotor = hardwareMap.get(DcMotor.class, "leftArmMotor");
        rightArmMotor = hardwareMap.get(DcMotor.class, "rightArmMotor");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        arm = new Neck(leftArmMotor, rightArmMotor);
        intake = new Mouth(intakeMotor);
    }

    @Override
    public void loop() {

        drive = -SPEED * gamepad1.left_stick_y; //y inputs are reversed
        strafe = SPEED * gamepad1.left_stick_x;
        turn = SPEED * gamepad1.right_stick_x;

        frontLeft.setPower(drive + turn + strafe);
        frontRight.setPower(drive - turn - strafe); //
        backLeft.setPower(drive + turn - strafe); //
        backRight.setPower(drive - turn + strafe); //

        if (gamepad2.left_trigger > 0.2){
            arm.down();
        } else {
            arm.stop();
        }

        if (gamepad2.right_trigger > 0.2){
            arm.up();
        } else {
            arm.stop();
        }

        if (gamepad2.b){
            intake.intake();
        } else {
            intake.stop();
        }

        if (gamepad2.a){
            intake.outtake();
        } else {
            intake.stop();
        }

    }
    }
