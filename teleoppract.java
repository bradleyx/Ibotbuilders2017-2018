
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name="Teleop", group="ibot")
//@Disabled
public class teleoppract extends LinearOpMode {

    /* Declare OpMode members. */
    hardwareIbot robot = new hardwareIbot(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




    @Override
    public void runOpMode() {
        double left;
        double right;
        double fright;
        double diagonal1;
        double diagonal2;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.initialize(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Run wheels in tank mode




            robot.rightm.setPower(.52*gamepad1.right_stick_y);
            robot.leftm.setPower(.52*gamepad1.left_stick_y);
            robot.fleftm.setPower(.52*gamepad1.left_stick_y);
            robot.frightm.setPower(.52*gamepad1.right_stick_y);

            if(gamepad2.dpad_up)
            {
                robot.lifting.setPower(.3);
            }
            while(gamepad1.dpad_left==true)
            {
                robot.rightm.setPower(1);
                robot.leftm.setPower(-1);
                robot.fleftm.setPower(1);
                robot.frightm.setPower(-1);
            }
            while(gamepad1.dpad_right==true)
            {
                robot.rightm.setPower(-1);
                robot.leftm.setPower(1);
                robot.fleftm.setPower(-1);
                robot.frightm.setPower(1);
            }
            if(gamepad2.dpad_left)
            {
                robot.lifting.setPower(0);
            }
            if(gamepad2.dpad_right)
            {
                robot.lifting.setPower(.1);
            }
            if(gamepad2.dpad_down)
            {
                robot.lifting.setPower(-.02);
            }




            //normal driving

            //hroizontal driving





            if(gamepad2.a){

                robot.arml.setPosition(1);
                robot.armr.setPosition(.3);
            }
            if(gamepad2.x){
                robot.arml.setPosition(.7);
                robot.armr.setPosition(.856);
            }
            if(gamepad2.b){
                robot.arml.setPosition(.5);
                robot.armr.setPosition(1);
            }
          



        }
    }
}













