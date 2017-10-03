# Json List

A simple 2 screen app with consumes JSON from an API and displays it in a ListView

## Setup
- Checkout repo
- Open in Android Studio
- Build and run

## Limitations 
- Only tested on a Galaxy, due time and hardware constraints
- Light title bar is only working in Android M and above `values-v23`, this was only introduced by Google in Android M. 
- I've left out the black bar at the bottom of the designs as I wasn't sure what this was for?
- The background of the ListView behaves slightly different to the design, due to a limitation of the standard ListView
- `NetworkHandler` should be moved to a singleton, if the scope of the app was bigger

