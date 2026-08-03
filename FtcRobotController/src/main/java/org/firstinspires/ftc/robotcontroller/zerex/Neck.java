package org.firstinspires.ftc.robotcontroller.zerex;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Neck {
    DcMotor motorLeft;
    DcMotor motorRight;
    private boolean isHolding = false;




    public Neck(DcMotor motorLeft, DcMotor motorRight){
        this.motorLeft = motorLeft;
        this.motorRight = motorRight;


        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void up(double power){
        isHolding=false;
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    public void down(double power){
        isHolding=false;
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        motorLeft.setPower(-power);
        motorRight.setPower(-power);
    }

    public void stop(){
        isHolding=false;
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        motorLeft.setPower(0.0);
        motorRight.setPower(0.0);
    }

    public void hold() {
        if (!isHolding) {
            motorLeft.setTargetPosition(motorLeft.getCurrentPosition());
            motorRight.setTargetPosition(motorRight.getCurrentPosition());

            motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorLeft.setPower(1.0);
            motorRight.setPower(1.0);

            isHolding = true;
        }
    }
}


