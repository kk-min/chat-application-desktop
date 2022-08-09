# Min's Simple Chat Application (Desktop)
_Copyright © 2022 Min Kabar Kyaw_

## Introduction
A simple chat program to pass messages asynchronously via the User Datagram Protocol (UDP). Both hosts will run this program and connect to the other via their IP or hostname.

## The need for Asynchronous Programming
There are two main **I/O Bound** tasks in the program that will **block the program thread**:
- **Requesting a message from the remote host**
- **Getting user input to encapsulate and send to the remote host**

If both of these operations are run in the same thread, we will not be able to send a message until we receive a message, or vice versa. 

This application uses Java's **CompletableFuture** library to branch off a thread from the common thread pool to send messages, and uses the **Service** class of JavaFX's concurrency library to make a NetworkService that listens for incoming messages.

## System Overview
The application uses the **Model-View-Controller (MVC)** framework.
- **Model:** Contains data that acts as a single source of truth, which is used by different components in the system (e.g. the target IP to connect to, JavaFX stage/window which is shared by different Views, etc).
- **View:** Consists of .fxml files in the resources folder, which specifies the layout of the different components in the JavaFX GUI interface.
- **Controller:** Handle various UI logic for each View and allow for switching of scenes depending on the user action/program states.

## Application Preview
<img src=https://user-images.githubusercontent.com/76023265/183324733-5c13926d-b000-45c8-b804-1e0da49c7ee9.jpg width="400"/> <img src=https://user-images.githubusercontent.com/76023265/183324795-429c89cb-8cd6-4fbc-b63e-c398758aaf2d.jpg width="400"/>
<img src=https://user-images.githubusercontent.com/76023265/183324820-a8f1e67c-be1e-418d-9283-822f5b2bbc9a.jpg width="400"/> <img src=https://user-images.githubusercontent.com/76023265/183324833-87e3ef36-4895-48e1-b8b7-8621c08c19ab.jpg width="400"/>
