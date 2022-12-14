# ContactPicker
FastContactPicker is a library that fetches contacts from the device very fast. The library does all the fetching work in the background with the help of coroutines. This library is very fast, efficient and easy to use for all.
  

 [![API](https://img.shields.io/badge/API-15%2B-red.svg)](https://android-arsenal.com/api?level=15) [![Twitter URL](https://img.shields.io/twitter/url/https/twitter.com/fold_left.svg?style=social&label=Follow%20%40elite_novice)](https://twitter.com/elite_novice) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com) [![Open Source Love svg1](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/) [![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://GitHub.com/Naereen/)
 
 <p align="center">
<br>
<b>Android library for Fetching Contacts from Device</b> 
</p>
 <p align="center">
 Built with ❤︎ by <a href="https://medium.com/@EliteNovice">Aryan Dhankar</a>.  
 </p>
 

## Including in your project 
 ### For Gradle
  
  Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	       implementation 'com.github.GitEliteNovice:ContactPicker:2.0'
	}

### For Maven
           <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.GitEliteNovice</groupId>
	    <artifactId>ContactPicker</artifactId>
	    <version>2.0</version>
	</dependency>


## Usage

        var obj = FastContactPickerImpl(this);

        GlobalScope.launch(Dispatchers.Main) {
           
           var contacts = obj.getContacts(); // list of contacts
           
            if (contacts != null) {
                // do your thing

            }

        }


*Note:-
If you want to say thanks or want to share how much you like my efforts, then you can share it with me with [![Say Thanks!](https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg)](https://saythanks.io/to/GitEliteNovice) 
