# Min's Simple Chat Application (Desktop)
_Copyright © 2022 Min Kabar Kyaw_

## Introduction
A simple chat program to pass messages asynchronously via the User Datagram Protocol (UDP). Both hosts will run this program via their terminal in the same subnet after configuring each other's host names, and they are now ready to send messages to each other.

## The need for Asynchronous Programming
There are two main **I/O Bound** tasks in the program that will **block the thread**:
- **Requesting a message from the remote host**
- **Getting user input to encapsulate and send to the remote host**

If both of these operations are run in the same thread, we will not be able to send a message until we receive a message, or vice versa. 

This application uses Java's **CompletableFuture** library to branch off a thread from the common thread pool to get messages from the remote host in a loop, and requests user input to send in the main thread in a loop. We are now effectively able to send and receive messages at the same time.
