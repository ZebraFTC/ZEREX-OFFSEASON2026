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

    public void up(){
        motorLeft.setPower(1.0);
        motorRight.setPower(1.0);
    }

    public void down(){
        motorLeft.setPower(-1.0);
        motorRight.setPower(-1.0);
    }

    public void stop(){
        motorLeft.setPower(0.0);
        motorRight.setPower(0.0);
    }

}
