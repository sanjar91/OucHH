# OucHH 

## Executive Summary
Waking up from peaceful sleep…... One of the hardest tasks known to college students. According to a research paper (You May be Able to Teach Early Classes, but Students May Not be Awake Yet!) by American Society for Engineering Education, early morning classes have a higher percentage of student absentees and each absence after the third occurrence reduced the student’s overall class grade by 5% [1]. Now if you ask me, 5% is a lot! But, we all have slept through our alarm once or twice. So, how do we solve this problem? How can we be sure that an alarm clock will do its due diligence and wake us up from our beauty sleep? I propose the all new OucHH. For my semester project I will design an IoT alarm clock with a prop hand that will literally slap you when the alarm goes off. The Ouch Alarm clock consists of a prop hand attached to a motor that is controlled by the OucHH android mobile app and Raspberry Pi 3 B+.

## Goals
* Most importantly WAKE YOU UP!
* Build an android app with a simple alarm clock interface
* OucHH mobile app sends an alert to Raspberry Pi at specified time set by the user. 
* The motor rotates the prop hand when alarm goes off and stops when the alarm is snoozed. 

## Merit 

OucHH is a new technological innovation that will make certain of waking up college students from their beauty sleep so they don’t miss their morning class. This new alarm clock will solve the issue of students missing morning classes and receiving a lower grade due to the inability of waking up from their sleep. OucHH will provide students a helping hand to their face to ensure they are up when their alarm goes off. We hope this new alarm clock technology helps students make it to class on time and not lose that 5% class grade over each absence. 

## Application Requirements
### User stories 
As a **sleep loving college student**, I want to be certain that my new **OucHH alarm clock wakes me up on time**, so that I **don’t miss my morning class**

**Acceptance Criteria:**
* OucHH mobile app sends an alert to Raspberry Pi when the set alarm goes off
* Raspberry Pi sends a command to rotate the motor causing the prop hand to rotate and slap the sleep loving college student 

As a **sleep loving college student**, I want to be certain that my new **OucHH alarm clock will stop slapping me when I hit the snooze button on my OucHH mobile app**, so that I **don’t get a black eye**

**Acceptance Criteria:**
* OucHH mobile app sends an alert to Raspberry Pi when the user hits snooze button 
* Raspberry Pi sends a command to stop the motor causing the prop hand to stop rotating

As a **non-traditional student**, I want to be certain that **I can set more than one alarm on my new OucHH mobile app for different days**, so that I **don’t have to update the alarm everyday**

**Acceptance Criteria:**
* OucHH mobile app can set more than one alarm
* OucHH mobile app supports weekday input along with time input

### Misuser stories 
As an **evil spirited roommate**, I want to **turn off my roommate’s fancy new alarm clock**, so that s/he **misses her/his morning class**

**Mitigations:**
* OucHH mobile app alerts the user when the attached Raspberry Pi is offline. The alert will continue to beep on user’s mobile device until the users turns it off

As an **evil spirited roommate**, I want to **connect to my roommate’s OucHH alarm clock hardware and alter the alarm**, so that s/he **wakes up early and lose their precious sleep**

**Mitigations:**
* Only one device can be connected to hardware at any given time. If another device connects to the hardware the initial user will receive an offline alert (described in misuse case 1).


## High Level Design
[![data_flow_1](https://github.com/sanjar91/CYBR8480-project/blob/master/OucHH.png)](https://github.com/sanjar91/CYBR8480-project/blob/master/OucHH.png)

## Component List

### Hardware Components
* Synchronous electric Motor: This motor will receive relay from Raspberry Pi and rotate a prop arm. Below are the specifications of the motor:
  * Frequency 50/60Hz, Power 4W, Voltage AC 100 ~ 127V, Speed 5 ~ 6R/min 
* Plastic prop arm: This prop arm will be attached to the motor. When the motor starts the arm will rotate and continuously slap the user until the users stops the alarm
* Raspberry Pi 3 B+:  Using Wi-Fi or Bluetooth protocol Raspberry Pi will receive alerts from mobile app and relay it to the motor to turn on and rotate the prop arm 
* Android mobile phone: Samsung Galaxy S4 used to download and test the OucHH app


### Software Components
* Android Studio for app interface design and emulator 
* GitHub for app repository 
* Wi-Fi or Bluetooth protocols for connectivity between Android phone and Raspberry Pi

## Security analysis

| Component name | Category of vulnerability | Issue Description | Mitigation |
|----------------|---------------------------|-------------------|------------|
| Raspberry Pi 3 B+ | Denial of Service | The whole concept architecture relies on Raspberry Pi receiving an alert from mobile application and relaying to the motor in order to rotate the prop arm. A malicious actor can turn off the Raspberry Pi and cause a denial of service. | OucHH mobile app will alert the user when the attached Raspberry Pi is offline. This alert will continue to beep on user’s mobile device until the users turns it off. |
| Raspberry Pi 3 B+ & OucHH mobile App | Man-in-the-Middle/Unauthorized Access | A malicious actor may alter the alarm time by connecting to the Raspberry Pi using another mobile device. | Add rules to only allow one mobile device connection to hardware at any given time. If another device connects to the hardware the initial user will receive an offline alert. |

## User story realization

### User story 1:
As a **sleep loving college student**, I want to be certain that my new **OucHH alarm clock wakes me up on time**, so that I **don’t miss my morning class**

**Acceptance Criteria:**
* OucHH mobile app sends an alert to Raspberry Pi when the set alarm goes off *(**In progress**)*
* Raspberry Pi sends a command to rotate the motor causing the prop hand to rotate and slap the sleep loving college student  *(**Implemented in smaller scale**)* 

#### Progress Details:
- For the first acceptance criteria I am using the IFTTT to receive the alarm time from OucHH (with use of intent) and send command (alarm time in hours and minutes) to raspberry pi using a webhook. 
- For the second acceptance criteria a small python program will rotate a servo motor connected to raspberry pi from 0 to 180 degrees and back. 

### User story 2:
As a **sleep loving college student**, I want to be certain that my new **OucHH alarm clock will stop slapping me when I hit the snooze button on my OucHH mobile app**, so that I **don’t get a black eye**

**Acceptance Criteria:**
* OucHH mobile app sends an alert to Raspberry Pi when the user hits snooze button *(**In progress**)*
* Raspberry Pi sends a command to stop the motor causing the prop hand to stop rotating *(**Implemented, change of scope**)*

#### Progress Details:
- The first acceptance criteria is still in progress, I have added a "SNOOZE" button on the mobile app interface that turn off the alarm locally. In addition to this, I will implement the same procedure from User story 1 to send the snooze/stop command to raspberry pi.
- I have added an exception in python program for KeyboardInterrupt which will stop the motor if program is running from a terminal, and the next step is to add an interrupt which will stop the motor if the snooze button has been hit by the user. 

### User story 3:
As a **non-traditional student**, I want to be certain that **I can set more than one alarm on my new OucHH mobile app for different days**, so that I **don’t have to update the alarm everyday**

**Acceptance Criteria:**
* OucHH mobile app can set more than one alarm *(**Out of scope**)*
* OucHH mobile app supports weekday input along with time input *(**Out of scope**)*

#### Progress Details:
- Due to time constrains I will not be adding the functionality of having more than one alarm by the semester's end, but I will work on this project throughout the summer to build a commercial product. So *stay tuned!*

#### Screen shots of OucHH:

**Home Screen**

[![data_flow_1](https://github.com/sanjar91/OucHH/blob/master/updated%20home.png)](https://github.com/sanjar91/OucHH/blob/master/updated%20home.png)

**Select Alarm Screen**

[![data_flow_1](https://github.com/sanjar91/CYBR8480-project/blob/master/select%20screen.png)](https://github.com/sanjar91/CYBR8480-project/blob/master/select%20screen.png)

**Alarm Screen**

[![data_flow_1](https://github.com/sanjar91/OucHH/blob/master/Updated%20alarm.png)](https://github.com/sanjar91/OucHH/blob/master/Updated%20alarm.png)

## Hardware/Software Requirements

### Hardware 
* OucHH hardware component designed by yours truly, Sanjar :)
  * Here are the components used in the OucHH hardware:
    * SG90 servo motor: Frequency 50Hz, Power 4W, Voltage AC 4.8 V, Speed 0.1 sec/turn
    * Plastic prop arm: This prop arm will be attached to the motor. When the motor starts the arm will rotate and continuously slap the user until the users stops the alarm
    * Raspberry Pi 3:  Using an IFTTT webhook Raspberry Pi will receive alerts from mobile app and trigger the servo motor to turn on and rotate the prop arm 
* Android mobile phone to download and use the app. 

### Software
* OucHH mobile app(available on github repo)
* Android Studio for emulator (in case of not having an android phone)
* GitHub to access application repository

## Installation Directions
### Below are step by step instructions for how to install OucHH mobile app:
* Download the source code from OucHH [github repository.](https://github.com/sanjar91/OucHH) 
* Open the source code with Android studio
* Run the app in the Android Studio Emulator or deploy it to an android phone

### Below are instructions for how to configure and install OucHH hardware code:
Detailed description of putting together hardware is listed below under **Getting Started Directions**. The raspberry pi used within this prototype and any possible future commercial OucHH products will be preassembled. However, if you want to create your own hardware below are installation instructions to help you along:
* First connect the following to your Raspberry pi:
  * Power cable
  * Display monitor using HDMI cable 
  * Mouse and key board using USB cables
  * Ethernet cable or connect to your available Wi-Fi network
  * And your servo motor to rotate the prop arm
* After Raspberry pi is connected to all the above and is booted up follow the instructions below:
  * Make sure your Raspberry pi has Node-RED installed, if not follow their [Installation instructions in this link](https://nodered.org/docs/getting-started/installation)
  * Launch Node-RED from Raspberry pi drop down menu on top left of your screen then under *Programming* click on Node-RED
  * Open another terminal and type *ifconfig* to get your current IP address
  * Now open a web browser and type in <Your IP address>:1880 (Node-RED uses port 1880)
  * Now in Node-RED create the following flow with 3 nodes (screen shots provided for reference):
 
    * Input node: *http in* an http GET request that can be called upon:
    <img src="https://github.com/sanjar91/OucHH/blob/master/http%20request.png">
 
    * Output node: *http response*, each GET request needs a http response, therefore this node must be added:
    <img src="https://github.com/sanjar91/OucHH/blob/master/http%20response.png">
    
    * Advanced node: *exec*, triggered by the http GET node and it this node executes the python script that rotates the servo, (*note: my python program is called "servo.py" but if you change the name or write your own program then you must change this node accordingly; this applies to http GET node as well, if you change the "/servotest" URL then you must add the new URL to the java code):
    <img src="https://github.com/sanjar91/OucHH/blob/master/python%20exec%20node.png">
    
   * After all the nodes are added connect them as shown in the diagram below and click on *Deploy* button on top right corner of the page:
   <img src="https://github.com/sanjar91/OucHH/blob/master/node-red%20flow.png">
   
After successfully completing the steps above you should be able to test your server's connectivity by typing *"http://<Your IP address>:1880/servotest"* in any browser on any computer and as long as your Raspberry pi is up and Node-RED running you the motor should be triggered. Which brings us to another important piece of the puzzle, the python program that operates the servo. [Click on this link for the python program](https://github.com/sanjar91/OucHH/blob/master/servo.py).


## Getting Started Directions

### Below are step by step instructions for how to use OucHH mobile app:
* Launch the OucHH mobile app in your android device or an android emulator
* Tap/Click on **SELECT** (A pop up dialog box will appear)
* In the dialog box, spin the hour and minute roll to your desired time and click **OK** to set the alarm (Note: OucHH uses 24 hour time format)
* Your alarm is set! You can sleep peacefully now, until of course the arm starts slapping you lol

### Below are step by step instructions for how to use OuchH hardware component:
* There are three major pieces of hardware that you will need to connect to each other:
  * Raspberry pi
  * SG90 servo motor
  * Plastic prop arm
* Plug the power and Ethernet cable to the Raspberry pi. If Wi-Fi is preconfigured in the Raspberry pi, you won't need to connect an Ethernet cable. 
* Connect the SG90 servo motor to the Raspberry pi as shown in the pictures below:

<img src="https://github.com/sanjar91/OucHH/blob/master/pi.png">
<img src="https://github.com/sanjar91/OucHH/blob/master/motor.png">

* Connect the plastic prop arm to the SG90 servo motor's drive shaft.

* Now securely place the SG90 servo motor next to your bed, preferably on a nightstand
* Make sure the prop arm is within the reach of your pillow
* Your hardware is set!

After successfully setting up your hardware and alarm you can rest peacefully without worrying about sleeping through your usual alarm; OucHH will make sure you wake up ;)




## References 

[1] Marbouti, Farshid. “You May be Able to Teach Early Classes, but Students May Not be Awake Yet!” (2014) https://www.semanticscholar.org/paper/You-May-be-Able-to-Teach-Early-Classes%2C-but-May-Not-Marbouti/e05264dbbd9f30d010bf8c63ed32371dce0a5123. 
