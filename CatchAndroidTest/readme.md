# Readme for the Project
## How to Run the App
1. Import this CatchAndroidTest project by Android Studio, make sure the Android SDK path in your computer is the same as the path in the ```local.properties``` file. If not, modify the file.
2. Choose build variants from the left bottom corner of AS, there are two variants:
	* ```mock```: this build currently using a build-in data.json file to mock the network connection, so we can test other modules without network. It's a good way while developing.
	* ```prod```: this is for product build, use real network connection to retrieve data.
3. Connect with a Android phone to your computer or launch an emulator. The minSdkVersion is 21, so make sure your device's Android version is 5.0+.
4. Click Run in Android Studio button and it will run the App in your device or emulator.

###OR

1. Make sure you are in the CatchAndroidTest directory in Terminal console, and you have all the environment for Android development. 
2. Make sure the Android SDK path in your computer is the same as the path in the local.properties file. If not, modify the file.
3. Run ```./gradlew assembleMockDebug``` in the console for mock build, or Run ```./gradlew assembleProdDebug``` for product build
4. The apk will be placed in the ```./app/build/outputs/apk/``` directory.
5. Copy the apk file to your phone and install it.

## How to Run Auto Test
> Notice: I only wrote some tests for the data source module

1. Import this CatchAndroidTest project by Android Studio, make sure the Android SDK path in your computer is the same as the path in the ```local.properties``` file. If not, modify the file.
2. Choose build variants from the left bottom corner of AS, there are two variants:
	* mock: this build currently using a build-in data.json file to mock the network connection, so we can test other modules without network. It's a good way while developing.
	* prod: this is for product build, use real network connection to retrieve data.
3. Connect with a Android phone to your computer or launch an emulator. The minSdkVersion is 21, so make sure your device's Android version is 5.0+.
4. Right click the test code file in the project view, then click ```Run 'file name'``` in the menu. For example: right click the ```EssaysDbHelperTest``` file and the click ```Run 'EssaysDbHelperTest'```
5. The test result will show in the "Run" view on the bottom of AS, and will be saved in the ```./app/build/reports/``` directory as well.

###OR

1. Make sure you are in the CatchAndroidTest directory in Terminal console, and you have all the environment for Android development. 
2. Make sure the Android SDK path in your computer is the same as the path in the local.properties file. If not, modify the file.
3. Run ```./gradlew cAT``` in the console
4. The test result will be in the ```./app/build/reports/``` directory.


## About the Design
* It's a MVP architecture with RxJava
* Use Retrofit for network, Gson for Json parse
* Use RxJava for concurrent programming
* Use SqlBrite for database management
* Use Espresso and JUnit4 for auto test
* Implement a 3 layers data source structure: cache/database/network 