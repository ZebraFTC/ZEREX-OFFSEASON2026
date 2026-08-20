package org.firstinspires.ftc.robotcontroller.zerex;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.pedropathing.util.Timer;
@Autonomous
public class PPtestZerex extends OpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;


    Neck arm;
    Mouth intake;
    MecanumDrive drive;

    private Follower follower;
    private Timer pathTimer,opModeTimer;

    public enum PathState{

    }
    @Override
    public void init() {
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
        }

    @Override
    public void loop() {

    }

}