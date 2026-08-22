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

    // Using a reliable system millisecond timestamp to remove any library timer bugs
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
        paths = new Paths(follower,arm,intake);

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
        public PathChain completeAutoChain;

        public Paths(Follower follower,Neck arm, Mouth intake) {
            completeAutoChain = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(10.000, 70.000),
                                    new Pose(35, 70.000)
                            )
                    )
                    .setTangentHeadingInterpolation()
                    .addTemporalCallback(0.0, () -> {
                        arm.up(0.5);
                    })

                    .addTemporalCallback(0.75, () -> {
                        arm.stop();
                        intake.outtake();
                    })

                    .addTemporalCallback(2.75, () -> {
                        intake.stop();
                        intake.intake();
                    })
                    .addTemporalCallback(5.75, () -> {
                        intake.stop();
                    })

                    .build();
        }
    }

    public int autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(paths.completeAutoChain);
                pathState = 1;
                break;

//            case 1:
//                if (follower.getCurrentPathNumber() == 1) {
//                    arm.up(0.5);
//                    actionTimer = System.currentTimeMillis();
//                    pathState = 2;
//                }
//                break;
//
//            case 2:
//                if ((System.currentTimeMillis() - actionTimer) >= 750) {
//                    arm.stop();
//                    intake.outtake();
//                    actionTimer = System.currentTimeMillis();
//                    pathState = 3;
//                }
//                break;
//
//            case 3:
//                if ((System.currentTimeMillis() - actionTimer) >= 2000) {
//                    intake.stop();
//                    pathState = 4;
//                }
//                break;
//
//            case 4:
//                if (follower.getCurrentPathNumber() == 2) {
//                    intake.intake();
//                    actionTimer = System.currentTimeMillis();
//                    pathState = 5;
//                }
//                break;
//
//            case 5:
//                if ((System.currentTimeMillis() - actionTimer) >= 3000) {
//                    intake.stop();
//                    pathState = 6;
//                }
//                break;
//
//            case 6:
//                if (follower.getCurrentPathNumber() == 4) {
//                    arm.up(0.5);
//                    actionTimer = System.currentTimeMillis();
//                    pathState = 7;
//                }
//                break;
//
//            case 7:
//                if ((System.currentTimeMillis() - actionTimer) >= 750) {
//                    arm.stop();
//                    intake.outtake();
//                    actionTimer = System.currentTimeMillis();
//                    pathState = 8;
//                }
//                break;
//
//            case 8:
//                if ((System.currentTimeMillis() - actionTimer) >= 2000) {
//                    intake.stop();
//                    pathState = 9;
//                }
//                break;

            case 1:
                if (!follower.isBusy() || follower.getVelocity().getMagnitude() < 0.1) {
                    pathState = 2;
                }
                break;

            case 2:
                break;
        }
        return pathState;
    }
}
