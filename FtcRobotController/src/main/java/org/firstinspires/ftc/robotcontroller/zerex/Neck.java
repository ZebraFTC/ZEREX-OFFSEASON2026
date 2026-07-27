package org.firstinspires.ftc.robotcontroller.zerex;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Neck {
    DcMotor motorLeft;
    DcMotor motorRight;



    public Neck(DcMotor motorLeft, DcMotor motorRight){
        this.motorLeft = motorLeft;
        this.motorRight = motorRight;

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void up(double power){
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    public void down(double power){
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    public void stop(){
        motorLeft.setPower(0.0);
        motorRight.setPower(0.0);
    }

}
