package org.firstinspires.ftc.robotcontroller.zerex;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Mouth {

    DcMotor mouth;

    public Mouth(DcMotor mouth){
        this.mouth = mouth;
    }

    public void intake(){
        mouth.setPower(0.2);
    }

    public void outtake(){
        mouth.setPower(-1.0);
    }

    public void stop(){
        mouth.setPower(0.0);
    }

}
