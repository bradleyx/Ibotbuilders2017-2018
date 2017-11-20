
package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;



/**
 * Created by bradleyxu on 11/1/2017.
 */
@Autonomous(name = "glyphred2", group = "Teamcode")
public class red2 extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    static final char     RIGHT                   = 'R';
    static final char     LEFT                    = 'L';
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DIST_WHEELS             = 15.0;
    static final double     PI                      = 3.1415926535897932;
    static final double     INCH_PER_DEG             = ((PI * DIST_WHEELS) / 360); //Two-wheel turn
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * PI);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;
    public Servo arml=null;
    public Servo armr= null;
    DcMotor drivemotorL = null;
    DcMotor drivemotorR = null;
    DcMotor lifting = null;
    ColorSensor color;
    public Servo lever=null;



    public void lifttime(double power, long time) throws InterruptedException{
        lifting.setPower(power);

        Thread.sleep(time);
        lifting.setPower(.01);
    }
    public void turnRightTime(double power, long time) throws InterruptedException{
        drivemotorL.setPower(-power);
        drivemotorR.setPower(power);
        Thread.sleep(time);
        drivemotorL.setPower(0);
        drivemotorR.setPower(0);
    }
    public void turnLeftTime(double power, long time) throws InterruptedException{
        drivemotorL.setPower(power);
        drivemotorR.setPower(-power);
        Thread.sleep(time);
        drivemotorL.setPower(0);
        drivemotorR.setPower(0);
    }

    public void DriveForward(double power)
    {
        drivemotorL.setPower(power);
        drivemotorR.setPower(power);
    }
    public void encoderDrivebyDistance2(double speed,double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;
        double leftCount = leftInches * 72.0;
        double rightCount = rightInches * 72.0;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            if (speed < 0) {
                newLeftTarget = drivemotorL.getCurrentPosition() - (int) (leftInches * COUNTS_PER_INCH);
                newRightTarget = drivemotorR.getCurrentPosition() - (int) (rightInches * COUNTS_PER_INCH);
            } else {
                newLeftTarget = drivemotorL.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                newRightTarget = drivemotorR.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            }
            drivemotorL.setTargetPosition(newLeftTarget);
            drivemotorR.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            drivemotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            drivemotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            drivemotorL.setPower(speed);
            drivemotorR.setPower(speed);
            if (speed < 0) {
                while (opModeIsActive() &&
                        (drivemotorL.getCurrentPosition() > newLeftTarget) &&
                        (drivemotorL.isBusy() && drivemotorR.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            drivemotorL.getCurrentPosition(),
                            drivemotorR.getCurrentPosition());
                    telemetry.update();
                }
            }

            // keep looping while we are still active, and there is time left, and both motors are running.
            else if (leftInches >= rightInches) {
                while (opModeIsActive() &&
                        (drivemotorL.getCurrentPosition() < newLeftTarget) &&
                        (drivemotorL.isBusy() && drivemotorR.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            drivemotorL.getCurrentPosition(),
                            drivemotorR.getCurrentPosition());
                    telemetry.update();
                }
            } else if (rightInches > leftInches) {
                while (opModeIsActive() &&
                        (drivemotorL.getCurrentPosition() < newRightTarget) &&
                        (drivemotorL.isBusy() && drivemotorR.isBusy())) {

                    // Display it for the driver.
                    telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                    telemetry.addData("Path2", "Running at %7d :%7d",
                            drivemotorL.getCurrentPosition(),
                            drivemotorR.getCurrentPosition());
                    telemetry.update();
                }
            }

            // Stop all motion;
            drivemotorL.setPower(0);
            drivemotorR.setPower(0);

            // Turn off RUN_TO_POSITION
            drivemotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drivemotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void encoderDrivebyDistance(double speed,
                                       double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        int mvLeftDir, mvRightDir;

        if( speed * leftInches > 0){
            mvLeftDir = 1;
        }
        else{
            mvLeftDir = 0;
        }

        if( speed * rightInches > 0){
            mvRightDir = 1;
        }
        else{
            mvRightDir = 0;
        }

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            if (speed < 0) {
                newLeftTarget = drivemotorL.getCurrentPosition() - (int) (leftInches * COUNTS_PER_INCH);
                newRightTarget = drivemotorR.getCurrentPosition() - (int) (rightInches * COUNTS_PER_INCH);
            } else {
                newLeftTarget = drivemotorL.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
                newRightTarget = drivemotorR.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            }
            drivemotorL.setTargetPosition(newLeftTarget);
            drivemotorR.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            drivemotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            drivemotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            drivemotorL.setPower(speed);
            drivemotorR.setPower(speed);


            while (opModeIsActive() &&
                    //        (drivemotorL.getCurrentPosition() > newLeftTarget) &&
                    (drivemotorL.isBusy() && drivemotorR.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d",
                        drivemotorL.getCurrentPosition(),
                        drivemotorR.getCurrentPosition());
                telemetry.update();
                //left to the position
                if( mvLeftDir == 1 ){
                    if( drivemotorL.getCurrentPosition() >= newLeftTarget ){
                        drivemotorL.setPower(0);
                    }
                }
                else{
                    if( drivemotorL.getCurrentPosition() <= newLeftTarget ) {
                        drivemotorL.setPower(0);
                    }
                }
                //right to the position
                if( mvRightDir == 1 ){
                    if( drivemotorR.getCurrentPosition() >= newRightTarget ){
                        drivemotorR.setPower(0);
                    }
                }
                else {
                    if (drivemotorR.getCurrentPosition() <= newRightTarget) {
                        drivemotorR.setPower(0);
                    }
                }
            }

            // Stop all motion;
            drivemotorL.setPower(0);
            drivemotorR.setPower(0);

            // Turn off RUN_TO_POSITION
            drivemotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drivemotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
    public void encoderTurnByDistance(double speed, double degree, char direction)
    {
        double numOfInches;
        numOfInches = (degree * INCH_PER_DEG);
        if(direction == RIGHT)
        {
            drivemotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            drivemotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            encoderDrivebyDistance(speed, numOfInches, -numOfInches);
        }
        else if(direction == LEFT)
        {
            drivemotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            drivemotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            encoderDrivebyDistance(speed, -numOfInches, numOfInches);
        }
        else
        {

        }

    }
    public void StopDriving()
    {
        DriveForward(0);
    }
    /*public void DriveForwardDistance(double power, int distance)
    {
        // Reset encoders
        drivemotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drivemotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //Set target positions
        drivemotorL.setTargetPosition(distance);
        drivemotorR.setTargetPosition(distance);
        //Set to RUN_TO_POSITION mode
        drivemotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        drivemotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //Drive forward
        DriveForward(power);
        while(drivemotorL.isBusy() && drivemotorR.isBusy())
        {
        }
        //Stop and change modes back to normal
        StopDriving();
        drivemotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivemotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }*/

    public void runOpMode() throws InterruptedException {
        // hsvValues is an array that will hold the hue, saturation, and value information.


        // bPrevState and bCurrState represent the previous and current state of the button.


        //bLedOn represents the state of the LED.
        float hsvValues[] = {0,0,0};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
        drivemotorL = hardwareMap.dcMotor.get("leftmotor_drive");
        drivemotorR = hardwareMap.dcMotor.get("rightmotor_drive");
        color=hardwareMap.colorSensor.get("color");
        lever=hardwareMap.servo.get("lever");
        arml=hardwareMap.servo.get("arml");
        armr=hardwareMap.servo.get("armr");
        lifting= hardwareMap.dcMotor.get("Lifting");
        drivemotorL.setDirection(DcMotor.Direction.FORWARD);
        drivemotorR.setDirection(DcMotor.Direction.REVERSE);
        lifting.setDirection(DcMotor.Direction.REVERSE);
        drivemotorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drivemotorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        color.enableLed(true);

        //Start dat robot

        // Set the LED in the beginning


        waitForStart();

        lever.setPosition(Servo.MAX_POSITION);
        //int ANDYMARK_TICKS_PER_REV = 1120;
        //liftAndLaunch(1, 3000);
        // Thread.sleep(400);
        // Go forward
        //driveForwardTime(.5, 850);
        arml.setPosition(.75);
        armr.setPosition(.4);
        Thread.sleep(1000);
        lifttime(.31,1010);
        encoderDrivebyDistance(.4,20,20);
        Thread.sleep(1000);
        encoderDrivebyDistance(.3,5,5);
        arml.setPosition(.55);
        armr.setPosition(.5);
        





    }

}