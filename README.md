# Assisted Living Event Organiser

## About
The traditional way of event organising for assisted living environment is mainly based on paper and memory, which can be troublesome, inefficient and unreliable. This project uses modern technologies (Android tablet/smartphone and Google App Engine) to monitor seniors’ daily life. It’s like having a personal assistant with them 24/7.

The idea of this project is to deliver a system which consists of a cloud based server application and a client application running on mobile devices. Carer can create events for seniors on the server application via a web interface (Google App Engine). Client application on senior’s mobile device reads events from the server and reminds them when the time comes. Also, the client application can record senior’s response to the event and report back to the server for carers to review. 

## Technical Details
Written back in 2012, this is a legacy app when Android 4 was relatively new. To create a consistent look and feel across both Android 2.x and Android 4 platforms, I used [ActionBarSherlock](http://actionbarsherlock.com/) third-party GUI library. RequestFactory was used to communicate with Google App Engine.

## Note
Due to the contract with my client, I cannot release the source code for the Google App Engine server side web application written with GWT. This is only the client side Android app! Although you can still try the demo web app [here](http://aleowintec.appspot.com/). 

Please be aware, the project has long been discontiuned and the source code is badly commented.

## Screenshots

### Android 2.x
![Main GUI](http://i.imgur.com/WyW4Avo.png)
![Downliading Events](http://i.imgur.com/qihhMGJ.png)
![Settings](http://i.imgur.com/1xbBUZK.png)
![Event Reminder](http://i.imgur.com/eSPoOyr.png)

### Android 4.x
![Main GUI](http://i.imgur.com/qWz8GYq.png)
![Settings](http://i.imgur.com/0Ycb42R.png)
![Event Reminder](http://i.imgur.com/MhjeiaG.png)

### Google App Engine GWT Web App
![Events Review](http://i.imgur.com/sIrgE9j.jpg)
![Create New Event](http://i.imgur.com/kRM684W.jpg)

## License
Copyright Declan Gao [@DeclanGao](http://twitter.com/DeclanGao/) © 2012.

Licensed under GNU PGL License.