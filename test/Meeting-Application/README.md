Group 9:
The Simon Fraser University Robot Soccer (SFURS) Meeting Application 

The Simon Fraser University
CMPT276 - D200
Hayder AL-Musawi
Saketh Poori
Shawn Oborne
Sri Yenupothula
Victor Cho


Team Information:

Name: Victor Cho
Contact: vca56@sfu.ca

Name:
Saketh Poori
Contact: spa244@sfu.ca

Name:
Sri Yenupothula
Contact: shy14@sfu.ca

Name:
Shawn Oborne
Contact: smo7@sfu.ca

Name: Hayder
Contact: haa61@sfu.ca


Description:
The Simon Fraser University Robotic Soccer (SFURS) Meeting Application aims to make schedule polling easier for SFURS leaders to organize its activities from feedback collected by its members. SFURS currently uses a website called when2meet.com to conduct scheduled polling for the availability of its members. Although the application “gets the job done”, it lacks the features that are needed from the club to make valuable meeting times. For example, when2meet.com lacks the ability to collect information from its members by not including the ability to express whether a member can attend online, in-person, or both. Furthermore, if in-person is selected, it is vital for SFURS leaders to create in-person meetings based on which SFU campus members are on in a given time because many members are students. 
SFURS Meeting Application is not an application to replace calendar technologies offered by Google Meets or Microsoft Outlook for example. Rather, because these tools lack schedule polling, SFURS Meeting Application acts as a supplement for the use of these technologies. Thereby creating SFURS Meeting Application, SFURS leaders will be well informed to create and send out valuable Google Meets and/or Microsoft Outlook meeting details to appease as many members as possible.


Proposed Solution:
SFURS Meeting Application is an online web application specifically designed for the SFURS. It acts as a supplement for SFURS to conduct schedule polling for efficient event planning. In the web application, SFURS will be able to login, check any new notifications, share their schedule availability, set-up schedule polls, answer poll requests, finalize and create an event, and send that event.

SFURS Meeting Application will have two user views, a Meeting Organizer view, and a Meeting Attendee view; in addition, a Common View.

In the Meeting Organizer User View, the Meeting Organizer will have many views to interact with. One epic will be the Polling view. This view will allow a Meeting Organizer to select date and time ranges for Meeting Attendees to select from, as well as the various recommended locations for in-person meetings. Then, the Meeting Organizer should be able to send out the poll with various options like e-mail, internal web application system, and polling link. In conjunction, the second epic will be the Results View where Meeting Organizer’s can view collected information and make an informed decision to best create a meeting to appease as many Meeting Attendees as possible.

In the Meeting Attendee User View, the Meeting Attendee will have many views to interact with. One epic will be the Inbox View. This view will allow the Meeting Attendee to see and respond to polling schedules sent by Meeting Organizers with the option to edit their availability on that poll until poll editing expires. In addition, another epic will be an Availability View for the ability for Meeting Attendees to predefine their options, and allow all users to view their schedule at a given time.

Lastly, the Common User View will be seen by both Meeting Attendees and Meeting Organizers. This epic will show the Dashboard View where all the created polls and responses will be displayed as well as a Community View to list all important announcements, notifications, and finalized events.

The general cycle of the SFURS Meeting Application is as follows. A Meeting Organizer wishes to schedule an event, so they login and navigate to the Polling view. Here they will fill out the Polling form and write a brief description of the meeting, choose an applicable date range, add attendees, add recommended in-person meeting locations, and add recommended remote meeting application platforms. Meeting Attendees will then need to fill out their availability through the Polling page or from their Inbox for each polling form they are invited to which can still be changed later if their availability changes. Once a certain percentage, or amount of attendees fill out their availability, the Meeting Organizer will receive an email letting them know they should finalize the meeting event from the polling information and create the meeting event. Doing so will email all prospective attendees about the time of the meeting, as well as the possibility to add it to the users Google Calendars.


Stakeholders:
The target audience is the SFURS club. Specifically, all the team members involved in the club. The application is being designed with the client’s expectations in mind. However, in the future, we could allow this application to be used by anyone who would like a more efficient scheduling system. 
Group9 is tasked to not only build the application for the SFURS club, but apply to our best ability what we are learning in CMPT276. We have the duty to execute features and determine which features are feasible and how long it takes to complete it.
The CMPT276 instructor Bobby Chan and our Marking TA. They have influence in how the application is built due to the marking criteria and aspects of the deadline for this project.


Assumptions:
  Client Assumptions:
    We still need to discuss with our client as to how often we want to meet. We are assuming that they will meet with us after each iteration of work, or bi-weekly, etc. We are not assuming the client to have any input towards our tech stack, though we will be open to any suggestions.
	
 Team Assumptions:
    At this moment in time, it seems that the client does not expect a specific UI design nor does he expect a certain tech stack. However, this is information that we can easily check with the client.


Requirements:
The main requirement for the SFURS Meeting Application is to ensure everyone can participate in the polling activities. It is also important that we can display the information in a meaningful way where we prevent user interface frustration and be able to display meaningful information to the users.

To achieve this goal, it will require organization on our part to break down this main requirement into main epics/three views, The Meeting Organizer View, The Meeting Attendee View, and The Common View.

The reason why we have taken steps to break it down into three main views is because there are features and subproblems that are only in The Meeting Organizer View and The Meeting Attendee View. For example the ability to create polls are only available to the Meeting Organizer and the ability to respond to polls specifically are the users that are invited to the poll known as the Meeting Attendee. However, there are also common features shared between these two views and we address them by creating The Common View. The Common View helps solve the problems where information like polling results, important notifications, announcements, and finalized events are viewed by everyone to reduce confusion and allow for transparency.


Lower Priority Features:
The SFURS is interested in incorporating Google Calendar integration, enabling the automatic distribution of a calendar to users once the meeting organizer finalizes the meeting time. Additionally, they envision a feature allowing organizers to include additional details, such as location preferences. The application should also empower organizers to conduct availability polls among members. Moreover, a functionality to trigger an email notification to the organizer once a substantial number of members have submitted their availability is desired.


Proposed Technologies:
(The tech stack could change in the future)
Currently, the plan is to use html, css, and javascript for the front end part of the application. We will use Java in the back-end via the Spring Boot Framework. At this moment in time, we plan to use Postgresql to have a database that collects various information. There are many ways to implement a login page, for instance, we could use firebase authentication. There are also multiple APIs that we could use as the client suggested we could incorporate the google calendar application, gmail notifications, etc. 


Links:
Github:
https://github.com/CMPT276-SFU-RSC/Meeting-Application.git

Website:
https://meeting-application-sddo.onrender.com
