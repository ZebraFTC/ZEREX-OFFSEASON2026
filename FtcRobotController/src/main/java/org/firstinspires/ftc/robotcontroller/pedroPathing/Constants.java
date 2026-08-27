package org.firstinspires.ftc.robotcontroller.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(10)
            .forwardZeroPowerAcceleration(-27.33507688337675)
            .lateralZeroPowerAcceleration(-59.14274802499472)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.06,0,0.0001,0.024))
            .headingPIDFCoefficients(new PIDFCoefficients(0.4,0,0.0015,0.024))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.6,0,0.0001,0.6,0.023))
            .centripetalScaling(0.0006)
            ;
    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(0.75)
            .rightFrontMotorName("frontRight")
            .rightRearMotorName("backRight")
            .leftRearMotorName("backLeft")
            .leftFrontMotorName("frontLeft")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .xVelocity(83.88761937899852)
            .yVelocity(61.0368133304626)
            ;
    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-7)
            .strafePodX(2.5)
            .distanceUnit(DistanceUnit.MM)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);
    public static PathConstraints pathConstraints = new PathConstraints
            (0.99,
            100,
            1.3,
            1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}
