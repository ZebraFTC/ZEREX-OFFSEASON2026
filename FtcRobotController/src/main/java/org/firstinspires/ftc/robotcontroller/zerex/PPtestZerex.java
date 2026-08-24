package org.firstinspires.ftc.robotcontroller.zerex;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcontroller.pedroPathing.Constants;

@Autonomous
@Configurable
public class PPtestZerex extends OpMode {
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
        follower.setStartingPose(new Pose(10, 70, Math.toRadians(0)));
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
        public Paths(Follower follower) {
            autoChainOne = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(10.000, 70.000),
                                    new Pose(25.495, 70.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();
            autoChainTwo=follower.pathBuilder()
                    //arm up
                    .addPath(
                            new BezierLine(
                                    new Pose(25.495, 70.000),
                                    new Pose(38.000, 70.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();
            autoChainThree=follower.pathBuilder()
                    //outtake
                    .addPath(
                            new BezierLine(
                                    new Pose(38.000, 70.000),
                                    new Pose(25.559, 70.000)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();
            autoChainFour=follower.pathBuilder()
                    //arm down
                    .addPath(
                            new BezierLine(
                                    new Pose(25.559, 70.000),
                                    new Pose(52.335, 121.437)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .build();
            autoChainFive=follower.pathBuilder()
                    //intake
                    .addPath(
                            new BezierLine(
                                    new Pose(52.335, 121.437),
                                    new Pose(25.686, 70.000)
                            )
                    )
                    .setConstantHeadingInterpolation(Math.toRadians(0))
                    .build();
            autoChainSix=follower.pathBuilder()
                    //arm up
                    .addPath(
                            new BezierLine(
                                    new Pose(25.686, 70.000),
                                    new Pose(40.000, 70.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                    .build();
            autoChainSeven=follower.pathBuilder()
                    //outtake
                    .addPath(
                            new BezierLine(
                                    new Pose(40.000, 70.000),
                                    new Pose(31.000, 70.000)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                    .addPath(
                            new BezierLine(
                                    new Pose(31.000, 70.000),
                                    new Pose(9.974, 11.359)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
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
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
                    arm.up(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=2;
                }
                break;
            case 2:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    follower.followPath(paths.autoChainTwo);
                    pathState = 3;
                }
                break;
            case 3:
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
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
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
                    arm.down(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=6;
                }
                break;
            case 6:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    follower.followPath(paths.autoChainFour);
                    pathState = 7;
                }
                break;
            case 7:
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
                    intake.intake();
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
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
                    arm.up(0.5);
                    actionTimer=System.currentTimeMillis();
                    pathState=10;
                }
                break;
            case 10:
                if (System.currentTimeMillis()-actionTimer>750) {
                    arm.stop();
                    follower.followPath(paths.autoChainSix);
                    pathState = 11;
                }
                break;
            case 11:
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
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
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
                    pathState = 14;
                }
                break;

            case 14:
                break;
        }
        return pathState;
    }
}
