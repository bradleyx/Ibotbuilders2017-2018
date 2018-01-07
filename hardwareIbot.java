package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.motors.NeveRest3_7GearmotorV1;
import com.qualcomm.hardware.motors.NeveRest40Gearmotor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by bradl on 7/19/2017.
 */

public class hardwareIbot {
    public DcMotor rightm = null;
    public DcMotor leftm = null;
    public DcMotor frightm=null;
    public DcMotor fleftm=null;
    public DcMotor lifting=null;
    public Servo armr=null;
    public Servo arml=null;
    public Servo lever=null;
    HardwareMap map =null;


   private DcMotor.RunMode initialstart=null;
     public hardwareIbot(DcMotor.RunMode enteredmode){
    initialstart = enteredmode;
    }

    public void initialize(HardwareMap aMap) {
        map = aMap;
        rightm = map.dcMotor.get("rightmotor_drive");
        leftm = map.dcMotor.get("leftmotor_drive");
        frightm=map.dcMotor.get("frontright_drive");
        fleftm=map.dcMotor.get("frontleft_drive");
        lifting = map.dcMotor.get("Lifting");
        armr=map.servo.get("armr");
       lever=map.servo.get("lever");
        arml=map.servo.get("arml");
        //ENCODERS
        leftm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fleftm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frightm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifting.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);;


      //  intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //ALL
       leftm.setMode(initialstart);
        rightm.setMode(initialstart);
        fleftm.setMode(initialstart);
        frightm.setMode(initialstart);
        armr.setPosition(.8);
        arml.setPosition(.25);
       lever.setPosition(1.2);
       // brightm.setMode(initialstart);
        //flyr.setMode(initialstart);
       lifting.setMode(initialstart);
        //intake.setMode(initialstart);
        ;
        //Direction
        leftm.setDirection(DcMotorSimple.Direction.REVERSE);
        rightm.setDirection(DcMotorSimple.Direction.FORWARD);
        fleftm.setDirection(DcMotorSimple.Direction.REVERSE);
        frightm.setDirection(DcMotorSimple.Direction.FORWARD);
        armr.setDirection(Servo.Direction.FORWARD);
        arml.setDirection(Servo.Direction.FORWARD);
       lever.setDirection(Servo.Direction.FORWARD);
       // brightm.setDirection(DcMotorSimple.Direction.FORWARD);
        //flyr.setDirection(DcMotorSimple.Direction.FORWARD);
         lifting.setDirection(DcMotorSimple.Direction.REVERSE);
        //intake.setDirection(DcMotorSimple.Direction.FORWARD);


    }

}
