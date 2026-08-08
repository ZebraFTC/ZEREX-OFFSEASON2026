package org.firstinspires.ftc.robotcontroller.zerex;
import com.qualcomm.ftccommon.SoundPlayer;
import android.content.Context;
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

    private double drive;
    private double strafe;
    private double turn;
    private final double SPEED = 0.65;
    private final double SLOW_SPEED = 0.25;
    Neck arm;
    Mouth intake;

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

        rightArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm = new Neck(leftArmMotor, rightArmMotor);
        intake = new Mouth(intakeMotor);
    }

    @Override
    public void loop() {
        if (!gamepad1.left_bumper) {
            drive = -SPEED * gamepad1.left_stick_y; //y inputs are reversed
            strafe = SPEED * gamepad1.left_stick_x;
            turn = SPEED * gamepad1.right_stick_x;
        } else {
            drive = -SLOW_SPEED * gamepad1.left_stick_y; //y inputs are reversed
            strafe = SLOW_SPEED * gamepad1.left_stick_x;
            turn = SLOW_SPEED * gamepad1.right_stick_x;
        }
        frontLeft.setPower(drive + turn + strafe);
        frontRight.setPower(drive - turn - strafe); //
        backLeft.setPower(drive + turn - strafe); //
        backRight.setPower(drive - turn + strafe); //


        if (gamepad2.left_stick_y > 0.2) {
            arm.down(0.5);
        }
        else if (gamepad2.left_stick_y < -0.2){
            arm.up(0.5);
        }
        else {
            arm.hold();
        }

        if (gamepad2.right_trigger > 0.2) {
            intake.outtake();
        }
        else if (gamepad2.left_trigger > 0.2){
            intake.intake();
        }
        else {
            intake.stop();
        }

    }
    }
