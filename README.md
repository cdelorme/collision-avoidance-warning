
# collision-avoidance-warning

Civic Hackathon 2014, creating android collision detection demo


This project will be for Android 4.0+, licensed under GPLv3.

Our goal is a functional prototype that can demonstrate collision detection using android phones.

The eventual goal is to outfit drivers with androids loaded with our software.



## notes

Future iterations may also make logical decisions to be more efficient.

For example, if no wifi devices in range, it will stop polling for gps coordinates, and stop emitting wifi messages.


## [activity diagram (in scruffy syntax)](http://yuml.me/edit/61190414)

    // Collision Activity Diagram
    [user launches activity]->[clicks start button]
    [clicks start button]->[launches wifi & gps threads]
    [launches wifi & gps threads]->[gps connects]
    [launches wifi & gps threads]->[wifi listens for connections and begins transmission cycle]
    [wifi listens for connections and begins transmission cycle]->[tells gps to begin polling cycle for data transmission (100ms intervals)]
    [tells gps to begin polling cycle for data transmission (100ms intervals)]->[wifi transmits gps data]


## [sequence diagram (in web sequence diagrams syntax)](https://www.websequencediagrams.com/)

    title Collision

    user->activity: launches
    user->activity: clicks start button
    activity->gps: begins threaded execution
    activity->wifi: begins threaded execution
    gps->connect: with gps satalites
    gps->poll: for gps data
    wifi->listen: for nearby devices
    wifi->transmit: transmit gps data

# references

- [scruffy](http://yuml.me/diagram/scruffy/class/draw)
- [web sequence diagrams](https://www.websequencediagrams.com/)

