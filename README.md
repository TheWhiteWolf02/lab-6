# Recovery Oriented Programming

Imagine you have a service and it crashes under certain circumstances. There are two ways to deal with it. Either you fix the problem or you restart your service after it has crashed.

Fact is that large software systems are build from COTS (commercial off the shelf) components. But as another matter of fact those components might not be as reliable as you want them to be. Very often you will not be able to change them because of a lack of understanding or because you do not have the source code of those components. So the solution for this is quite simple: an automatic restart of a crashed service.

## 1. Introduction

The problem with the automated restart is that the restart might take some time. In the meantime your service is unavailable. This might lead to a pretty inefficient runtime behavior since you will have to wait for the restart to be completed. So it is quite obvious which two improvements could be thought of. One is to restart only the crashed components and not the whole system and the other one is to restart before the crash becomes visible (to your customers).

### 1.1 Restart Framework

You have some code to use separate components. You will need to integrate the restart framework in this code. Its purpose is to observe the state of your components and to restart them if necessary.

You do not need to restart your whole application in the presence of a failed component. To save time it is enough to restart the failed component. But you have to respect the dependency between the components.

### 1.2 Restart of a certain component

The restart of a component running in its own thread is more difficult than that of a component without an own thread.

If the component has no own thread you do:
* free component’s resources
* create new instance
* inform the other components about this new instance

If the component does have its own thread proceed as follows: in another thread different the component’s thread:
* stop the component’s thread
* free component’s resources
* create new instance
* start thread of new instance
* inform the other components about this new instance

## 2. Your Task

You are provided with a tiny HTTP server. Unfortunately you will discover some bugs. So your
task is to make the server more reliable. Find out the condition under which a certain component of
the server crashes and add the ability to restart this component to the HTTP server.
