import RPi.GPIO as GPIO
import time

control = [4,4.5,5,5.5,6,6.5,7,7.5,8,8.5,9,9.5,10,10.5,11,11.5]

# Rasbpberry pi pin#22, GPIO25
servo = 22

GPIO.setmode(GPIO.BOARD)

GPIO.setup(servo,GPIO.OUT)

#operte with 50hz frequency
p=GPIO.PWM(servo,50)

#always start servo motor at 0 degrees
p.start(2.5)

try:

	while True:
                #The number 15 must match the total number control values listed above
		for x in range(15):
			p.ChangeDutyCycle(control[x])
			time.sleep(0.03)

			print x

		for x in range(9,0,-1):
			p.ChangeDutyCycle(control[x])
			time.sleep(0.03)

			print x

#keyboard interrupt, control+C
except KeyboardInterrupt:

	GPIO.cleanup()
