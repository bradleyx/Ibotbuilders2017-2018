package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

 /**

 */
public class autonomoushardware {
    /* Public OpMode members. */
    public DcMotor leftm   = null;
    public DcMotor  rightm  = null;
    public Servo    arm = null;
    public final static double ARM_HOME = 0.2;
    public final static double ARM_MIN_RANGE  = 0.20;
    public final static double ARM_MAX_RANGE  = 0.90;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftm   = hwMap.dcMotor.get("leftmotor_drive");
        rightm  = hwMap.dcMotor.get("rightmotor_drive");
        leftm.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightm.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        arm = hwMap.servo.get("arm");

        // Set all motors to zero power
        leftm.setPower(0);
        rightm.setPower(0);
        arm.setPosition(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}