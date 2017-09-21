# ColorGameApp

This is a simple game app in which the player tests their reaction time by tapping on a button according to the background
color. The buttons and background colors will update after each try in a random order.

This is an app I started to work on at the same time I started learning Android development. One of the things about Android is that there are many different devices to chose from; different manufacturers and different sizes, in both body and screen size. This app has helped me understand how to deal with this obstacle.

This app is a good example on using LinearLayouts which help organize the views in the device screen according to their size. Without the use of LinearLayout, buttons, textviews and other views would not look good on the screen or maybe even not appear correctly on the screen. In this app I use buttons as the input for the game so I utilized LinearLayouts to make sure the buttons would be on the right place and right size according to the device's screen.

This application also uses a timer. In order to make the game challenging, I included a timer for how many seconds the user will have to play the game. In my first implementation I just added a timer but I tought about how the player can abuse the game by just pressing random buttons fast and hoping to get a high score like this. The way I fixed this problem was to add a penalty of 1 second if the user chooses the wrong button and rewarding the player 1 second if it presses the right one. This way the user will have to play the game correctly and not cheat.

I also include a high score in the main screen to keep track of the highest score achieved.
Some ideas I would like to implement in this app would be facebook integration to let the player share their high scores with friends and challenge them to beat their score. I only record the highest score but I would like to change this so I record the highest 3 scores achieved by the player. I also need to add a 'play again' button from the game activity so the user can start a new game instead of navigating to the main screen first.
