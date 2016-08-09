
Presentation Notes:
 ☐ GoldenMaster
   ☐ Before making changes to legacy code, it is important to guard against unintended changes.
   ☐ We could even introduce bugs by “fixing” behaviour.
   ☐ Characterization tests are a means of capturing the existing behaviour. 
   ☐ They are meant to characterize what the system is already doing.
 ☐ Remainder/Unit testing
   ☐ We are going to take the piece of existing code without tests and write tests for it
   ☐ Only automated refactorings are allowed till your code is covered by a test
   ☐ When it is 100% covered, then we can refactor it
   ☐ Look at trip service, started testing from the shortest branch - this is when a user is not logged in exception
 ☐ Introduce Seams
   ☐ The problem that we have is that this is a singleton, we can't inject it, we can't mock it. 
   ☐ What can we do? We can only use automated refactorings
   ☐ We can create a seam - a seam is where the two classes meet, the UserSession and the TripService, what we do is isolate the bit that goes to the other class and that is our seam. 
   ☐ We can create a private class that extends teh trip service
 ☐ Run your code coverage, make sure that the test that you have just written tests the short branch. 
 ☐ Code coverage is a very useful took when working with code coverage. We don't care about percentages, what we do care about is whether the test that we have written covered what we expected it to cover. 
 ☐ This seam is nice, but it's not ideal. Talk over test - where is the notion of a user not logged in. 
 ☐ Here is a good opportunity to bring in a business domain language into source. 
   ☐ Introduce concept of GUEST and user JOE in getTripsByUser   
  
 ☐ Now find the next shortest branch - there is only two left, we know the if (isFriend) is the deepest, as to get to there you need to set the isFriend flag in the snippet above
 ☐ 
   Test to verify no trips are returned when user and logged in user are not friends
   {
     User loggedInUser = UserSession..
     User JOE = new User("Joe")
     JOE.addFriend(SARAH)
     JOE.addTrip(TO_LONDON)
     assertThat(trips.size(), is(0)) trips are empty as they arenot friends  
   }
 ☐ test is green - that happens quite a lot in legacy code as the code is already written.
 ☐ Next test is when they are friends - we need to break the hard wired dependencies static calls - create another seam.

 ☐ We now should have full coverage, now we can start with cleanups - refactoring etc.
 ☐ add a user builder 
 ☐ Remember when testing we start from shortest to deepest
 ☐ In refactoring - if you go straight to deepest, it dosen't depend on anything, the snippet of code has everything that it needs, all flags are set so that conditionals are set.
 ☐ The deepest branch as we know was tripList = tripsByUser(..) - but this dosen't do anything.
 ☐ This block is the snippet that loops over the users. 
   ☐ always be asking when refactoring is whether this responsiblity belongs here. 
   ☐ we -GO- to the User object, we -GET- a list of users from the users, we -ITERATE- through it to see if it contains a user object. This is called feature envy. The trip service wants to be the User class - we need to fix this. 
   ☐ One way to do this is to add a 'isFriendsWith(X)' to the User class.
   ☐ TDD this from the user class
   ☐ should_inform_when_users_are_not_friends in user class
   ☐ assertThat (user.isFriendsWith(PAUL), is(false))
   ☐ create the method - minimum to do to make it pass is set it to false
   ☐ should_inform_when_users_are_friends
  ☐ When working with legacy code - you see variable names your not quite sure what they do - when you do find out what they do, rename to something that makes sense.
 ☐ You should start seeing the same language as seen in your test noTrips in the code if (noTrips etc)
 

 ☐ Retro - discuss what we have done, we have taken a bit of untested legacy code and written tests around it. Still a lot wrong with it.
 ☐ But what if the design is wrong, what we are doing when we are writing tests is perpetuating the bad design, and after you have written a lot of tests, you would be far inclined to changing the design.
 ☐ Whats wrong with the design:
 ☐ We have a TripService -  we assume by the name that it is a server side class. 
 ☐ However, it has a dependency on the user session. 
 ☐ Your TripService shouldn't know anything about your HTTP Session etc. 
 ☐ What we should do is pass in the user as a parameter
 ☐ Try to do as always baby steps
 ☐ add parameter loggedInUser to getTripsByUser
 ☐ Fix the tests that now add a new param
 ☐ You will also need to get rid of our new seam to getLoggedInUser
 ☐ We are almost done
 ☐ However, we don't like the static call - what can we do here?
 ☐ Why is it static? I can't mock it, I can't inject it
 ☐ I want to get rid of the static and create an instance.
 ☐ Write a test for it TripDAOTest,
   ☐ Useful trick, I want an instance method that does exactly the same as the static method    
   ☐ I could just remove the static keyword- I could but then I would have to fix my entire codebase. 
   ☐ We want to do everything in small steps
   ☐ So just create an instance with the same name that calls the real static call
   ☐ Also, we want to get rid of TestableTripService, we can make tripsBy private instead of protected.

 ☐ Fix a bug
   ☐  there was a typo in the report 
 ☐ Add a feature - make the report a json reporter
    ☐ Identify an area which breaks the single responsibility principle.
    ☐ 
    ☐ Remove all references to the object itself. This might mean making it static, or pulling it out of the class, or if you’re passing the object in, removing the parameter. Instead, pass in the things it needs.
    ☐ 
    ☐ Write tests for it.
    ☐ Refactor it until you like the interface.

Sandro Mancuso: Refactoring:
 ☐ Start testing from shortest to deepest branch (otherwise too much setup)
 ☐ Start refactoring from deepest to the shortest branch
 ☐ https://github.com/sandromancuso/trip-service-kata - lots of code areas
 ☐ A lot of time - your tests will be automatically green as the code is already there, you are just wrapping it in characterization tests
 ☐ Finally ready to start refactoring after some tests and most coverage: remember
 ☐ Start testing from the shortest to deepest branch
 ☐ Start refactoring from the deepest to the shortest branch - reason being if you want to start refactoring in the middle of your code, you need to have all flags set - however if you go to the deepest, 
 
 
 ☐ If the user class has the collection of friends, it might be worth asking user, are you friends with X?
 ☐ So create a user class and write the test should_inform_when_users_are_not_friends
 ☐ Meetup: When working with legacy code - try to bring the variable to the block where it is used.
 ☐ Meetup: 
 ☐ Meetup: Guard clause at the top
 ☐ 
Other Resources to review:
 ☐ Martin Fowler - https://www.youtube.com/watch?v=vqEg37e4Mkw
 ☐ Michael Feathers - https://www.youtube.com/watch?v=4cVZvoFGJTU
 ☐ Testing and refactoring legacy code Sandro : https://www.youtube.com/watch?v=_NnElPO5BU0
 ☐ Practical Refactoring - https://www.youtube.com/watch?v=aWiwDdx_rdo


Deck:
 ☐ Baby steps importance
 ☐ Golden master - trip service google this
 ☐ 
















