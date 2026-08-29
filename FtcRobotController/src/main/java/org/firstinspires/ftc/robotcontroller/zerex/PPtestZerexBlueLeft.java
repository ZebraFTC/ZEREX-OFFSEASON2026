package org.firstinspires.ftc.robotcontroller.zerex;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcontroller.pedroPathing.Constants;

@Autonomous
@Configurable
public class PPtestZerexBlueLeft extends OpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backRight;
    private DcMotor backLeft;
    Neck arm;
    Mouth intake;
    MecanumDrive drive;
    private TelemetryManager panelsTelemetry;
    public Follower follower;
    private int pathState;
    private Paths paths;
    private long actionTimer;

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

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(133, 70, Math.toRadians(-180)));
        paths = new Paths(follower);

        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        panelsTelemetry.debug("Status", "Initialized Successfully");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update();
        pathState = autonomousPathUpdate();

        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void start() {
        pathState = 0;
        actionTimer = System.currentTimeMillis();
    }

    public static class Paths {
        public PathChain autoChainOne;
        public PathChain autoChainTwo;
        public PathChain autoChainThree;
        public PathChain autoChainFour;
        public PathChain autoChainFive;
        public PathChain autoChainSix;
        public PathChain autoChainSeven;
        public PathChain autoChainEight;
        public PathChain autoChainNine;
        public PathChain autoChainTen;
        public PathChain autoChainEleven;

        public Paths(Follower follower) {
            autoChainOne = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(133.000, 70.000),
                                    new Pose(117.750, 70.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            autoChainTwo = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(117.750, 70.000),
                                    new Pose(111.123, 70.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            autoChainThree = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(111.123, 70.000),
                                    new Pose(117.750, 70.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(-180), Math.toRadians(-180))
                    .build();

            autoChainFour = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(117.750, 70.000),
                                    new Pose(89.668, 121.604)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            autoChainFive = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(89.668, 121.604),
                                    new Pose(125.000, 70.000)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(-180))
                    .build();

            autoChainSix = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(125.000, 70.000),
                                    new Pose(111.399, 69.723)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            autoChainSeven = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(111.399, 69.723),
                                    new Pose(117.287, 70.000)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(-180))
                    .build();

            autoChainEight = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(117.287, 70.000),
                                    new Pose(77.800, 124.093)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            autoChainNine = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(77.800, 124.093),
                                    new Pose(125.162, 70.000)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(-180))
                    .build();

            autoChainTen = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(125.162, 70.000),
                                    new Pose(111.312, 70.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();

            autoChainEleven = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(111.312, 70.000),
                                    new Pose(132.879, 70.083)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(-180))
                    .addPath(
                            new BezierLine(
                                    new Pose(132.879, 70.083),
                                    new Pose(131.875, 2.000)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(-180))
                    .build();
        }
    }




    public int autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(paths.autoChainOne);
                pathState = 1;
                break;
            case 1:
                if (!follower.isBusy()) {
                    arm.up(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=2;
                }
                break;
            case 2:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    follower.followPath(paths.autoChainTwo,0.5,true);
                    pathState = 3;
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    intake.outtake();
                    actionTimer=System.currentTimeMillis();
                    pathState=4;
                }
                break;
            case 4:
                if (System.currentTimeMillis()-actionTimer>2000) {
                    intake.stop();
                    follower.followPath(paths.autoChainThree);
                    pathState = 5;
                }
                break;
            case 5:
                if (!follower.isBusy()) {
                    arm.down(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=6;
                }
                break;
            case 6:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    intake.intake();
                    follower.followPath(paths.autoChainFour,0.5,true);
                    pathState = 7;
                }
                break;
            case 7:
                if (!follower.isBusy()) {
                    actionTimer=System.currentTimeMillis();
                    pathState=8;
                }
                break;
            case 8:
                if (System.currentTimeMillis()-actionTimer>2000) {
                    intake.stop();
                    follower.followPath(paths.autoChainFive);
                    pathState = 9;
                }
                break;
            case 9:
                if (!follower.isBusy()) {
                    arm.up(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=10;
                }
                break;
            case 10:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    follower.followPath(paths.autoChainSix,0.5,true);
                    pathState = 11;
                }
                break;
            case 11:
                if (!follower.isBusy() ) {
                    intake.outtake();
                    actionTimer=System.currentTimeMillis();
                    pathState=12;
                }
                break;
            case 12:
                if (System.currentTimeMillis()-actionTimer>2000) {
                    intake.stop();
                    follower.followPath(paths.autoChainSeven);
                    pathState = 13;
                }
                break;
            case 13:
                if (!follower.isBusy() ) {
                    arm.down(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=14;
                }
                break;
            case 14:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    intake.intake();
                    follower.followPath(paths.autoChainEight,0.5,true);
                    pathState = 15;
                }
                break;
            case 15:
                if (!follower.isBusy()) {
                    actionTimer=System.currentTimeMillis();
                    pathState=16;
                }
                break;
            case 16:
                if (System.currentTimeMillis()-actionTimer>2000) {
                    intake.stop();
                    follower.followPath(paths.autoChainNine);
                    pathState = 17;
                }
                break;
            case 17:
                if (!follower.isBusy() ) {
                    arm.up(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=18;
                }
                break;
            case 18:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    follower.followPath(paths.autoChainTen,0.5,true);
                    pathState = 19;
                }
                break;
            case 19:
                if (!follower.isBusy() ) {
                    intake.outtake();
                    actionTimer=System.currentTimeMillis();
                    pathState=20;
                }
                break;
            case 20:
                if(System.currentTimeMillis()-actionTimer>2000){
                    intake.stop();
                    follower.followPath(paths.autoChainEleven,0.75,true);
                    pathState=21;
                }
                break;
            case 21:
                if (!follower.isBusy()) {
                    pathState=22;
                }
                break;
            case 22:
                break;
        }
        return pathState;
    }
}