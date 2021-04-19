# Accessibility codelab

The master branch has a dummy app screen which shows a list of posts and an option to add a new post. Follow the steps below to make changes to the app.
The [solution branch](https://github.com/amolgupta/android-a11y-codelab/tree/solution) is the state after following all the steps.


### Setup
To get started clone this project and open in Android studio 4.
Also install the [accessibility scanner app](https://play.google.com/store/apps/details?id=com.google.android.apps.accessibility.auditor) on the Android device for testing.

Build and run the app from the master branch. Once the app is running on the Enable the scanner app and scan the home screen of the app. Scanner app will detect some issues here.

Notice that the `MainActivityTest` contains an additional test rule.

```
    @get:Rule
    val accessibilityTestRule = AccessibilityTestRule()
```


This comes from [Accessibility Test Framework for Android](https://github.com/google/Accessibility-Test-Framework-for-Android) which is added to our dependencies like:

```androidTestImplementation 'androidx.test.espresso:espresso-accessibility:3.4.0-alpha02'```

This espresso library contains several matchers that help detect accessibility issues when running UI tests.

For now we see some of these failing. Notice that the errors captured by the Scanner app and Espresso library are somewhat similar. We will fix them one by one in the next steps.

## Text size
Low text size can make it hard to read the content.

Modify the text size to be atleast `14sp`. Open `theme.xml` and modify texts sizes to be `14sp` or above.

## Color contrast
There should be enough color contrast between any text and it's background to ensure better readability. Use a guideline (eg [this](https://backpack.github.io/guidelines/colour) ) or tool (eg [this](https://coolors.co/contrast-checker/) ) to verify  if the color pair has enough contrast.

Modify `colors.xml` and set `textGrey` as `#191818`. Verify using Scanner app and by running the test.
Modify `main_fragment.xml` and set the fab `backgroundTint`  to `purple_500`

## Touch target
The touch targets for any button or link should be greater than `48dp` to ensure they are clicked easily and accurately.

Modify the `ButtonStyle` in `theme.xml` to increase the text size and add some padding


## Content Description
Screen-reader looks for the content description tags on all items on the screen. To verify the current state, enable talk-back and try navigating through the screen. Next we try to fix the screenreader support based on the type of elements


#### TextViews
Any `TextView` would have the default content description as the content displayed. This can still be modified by adding a `contentDescription` tag either in the layout xml or programatically

#### Buttons
Any button would also have the default content description as the text displayed and can be overriden using the `contentDescription` tag. This is helpful when the button has only an icon.
Screen reader would announce any clickable items as it's content description plus a string like "double tap to activate" to infrom the user that an action can be performed.

Modify the `PostsAdapter.kt` and add the following lines to add content description for the buttons:
```
view.findViewById<Button>(R.id.btn_comment).contentDescription = "Comment on post by ${item.username}"
view.findViewById<Button>(R.id.btn_like).contentDescription = "Like this post by ${item.username}"
```
In this case, given the button is in a list, it is handy to add some extra information to make sure user clicks the correct button.

Modify `NewPostFragment.kt` to add description for the close icon
```setNavigationContentDescription(R.string.close_button) ```

#### Images
It is helpful to add good content descriptions to images that convey the correct message to the user. If the images are loaded dynamically, the server should also return content description.

Notice how `FakePosts.kt` adds it as a filed to the data returned. Modify `PostsAdapter.kt` to consume this as:
```
contentDescription = item.contentDescription
```
#### Headings
It's good to differentiate between content and headings. This way, a screen-reader user understands that it's a heading and not a CTA and content would follow.

Modify `main_fragment.xml` and add `android:accessibilityHeading="true"` to the `title` TextView
This would instruct talkback to append "Heading" to the end of the content description for this title.

#### Input fields
When a text field has content, the screenreader announces that, else it uses the hint. Content spoken on it's own can lack context.

Wrapping them inside a `TextInputLayout` can be useful. Modify the `fragment_new_post.xml` and replace the Edittext with:

```
  <com.google.android.material.textfield.TextInputLayout
        android:id="@id/post_content_container"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/toolbar"
        >

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/post_content"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            android:hint="@string/what_s_happening"
            android:inputType="text"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@id/toolbar"
            app:maxLines="2"
            />
    </com.google.android.material.textfield.TextInputLayout>
```

The user can also get stuck inside the EditText. It would help to provide a way to exit when they are press the action key.

Modify the `NewPostFragment` and add the following:
```
        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                button.requestFocus()
                return@setOnEditorActionListener true
            }
            false
        }
```

#### Navigation
A `ViewGroup` such as `LinearLayout` would be easy to navigate for screen-reader, but something like a `RelativeLayout` or `ConstraintLayout` would need to be configured properly.

Next we modify the `main_fragment.xml` in a way such that the order of travlersal is  
    - Title  
    - Add post button  
    - Subtitle  
    - Posts

Add the `android:accessibilityTraversalAfter` and `android:accessibilityTraversalBefore` tags to the views accordingly.

## Screen updates / Live Region
At times a view may update with some important information that needs to be communicated to the user immediately. We can mark some views to be super important and any changes to them would be announced immediately by the screenreader.

Modify the `main_fragment.xml` and add `android:accessibilityLiveRegion="assertive"` to the `subtitle`.

## Motion
Overusing animation or making the transitions too fast could cause motion sickness to some users. Follow the [Material guidelines](https://material.io/design/motion/speed.html) for a good experience.

Modify `slide_down.xml` and change the duration to `200`

## Suppress errors
While it is advisable to provide the same experience to the screen-reader users, there may be situations where that is not practical. In such cases, we can ask talkback to ignore some views by adding `android:importantForAccessibility="no"` to the layout xml.

We can also ignore some views from being tested by espresso for accessibility. Modify `AccessibilityTestRule.kt` to ignore the user image
```
.setSuppressingResultMatcher(
            AccessibilityCheckResultUtils.matchesViews(
                CoreMatchers.anyOf(
                    withId(R.id.user)
                )
            )
        )
```