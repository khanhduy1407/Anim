# Anim

[![](https://jitpack.io/v/tk.nkduy/Anim.svg)](https://jitpack.io/#tk.nkduy/Anim)

# Download

```groovy
compile 'tk.nkduy:anim:1.0'
```

This code describe the video above

```java
new Anim()
        .expect(avatar)
        .toBe(
                bottomOfParent().withMarginDp(16),
                leftOfParent().withMarginDp(16),
                width(40).toDp().keepRatio()
        )

        .expect(name)
        .toBe(
                toRightOf(avatar).withMarginDp(16),
                sameCenterVerticalAs(avatar),
                toHaveTextColor(Color.WHITE)
        )

        .expect(subname)
        .toBe(
                toRightOf(name).withMarginDp(5),
                sameCenterVerticalAs(name),
                toHaveTextColor(Color.WHITE)
        )

        .expect(follow)
        .toBe(
                rightOfParent().withMarginDp(4),
                bottomOfParent().withMarginDp(12),
                toHaveBackgroundAlpha(0f)
        )

        .expect(message)
        .toBe(
                aboveOf(follow).withMarginDp(4),
                rightOfParent().withMarginDp(4),
                toHaveBackgroundAlpha(0f)
        )

        .expect(bottomLayout)
        .toBe(
                atItsOriginalPosition()
        )

        .expect(content)
        .toBe(
                atItsOriginalPosition(),
                visible()
        )

        .toAnimation()
        .setDuration(1500)

        .start();
```

## Follow scroll

Use `setPercent` to apply modify the current step of the animation

Exemple with a scrollview

```java
this.animMove = new Anim()
                .expect(username)
                .toBe(
                        toRightOf(avatar).withMarginDp(16),
                        sameCenterVerticalAs(avatar),
                        alpha(0.5f)
                )

                .expect(avatar)
                .toBe(
                        topOfParent(),
                        leftOfParent().withMarginDp(16),
                        scale(0.5f, 0.5f)
                )
                .expect(follow)
                .toBe(
                        rightOfParent().withMarginDp(16),
                        sameCenterVerticalAs(avatar)
                )

                .expect(backbground)
                .toBe(
                        height(height).withGravity(Gravity.LEFT|Gravity.START, Gravity.TOP)
                )

                .toAnimation();

scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        final float percent = (scrollY * 1f) / v.getMaxScrollAmount();

        animMove.setPercent(percent);
    }
});
```

## Concat

You can play an anim after one other using `anim.concat` (static method)

```
Anim.concat(
                new Anim()
                        .expect(image1)
                        .toBe(
                                withCameraDistance(500f),
                                flippedHorizontally()
                        )
                        .toAnimation()
                        .setDuration(1000),
                new Anim()
                        .expect(image2)
                        .toBe(
                                withCameraDistance(1000f),
                                flippedVertically()
                        )
                        .toAnimation()
                        .setDuration(500)
                )
                .start()
```

## Apply directly

Use `setNow` to apply directly the transformation

```java
new ExpectAnim()
                .expect(view)
                .toBe(
                        outOfScreen(Gravity.BOTTOM)
                )
                .toAnimation()
                .setNow();
```

## Reset

Use `reset` to return to the initial state of views

```java
anim.reset():
```

# List of expectations

```
new Anim()
                .expect(view)
                .toBe(

                    //.withMargin(marginPx)
                    //.withMarginDp(margin)
                    //.withMarginDimen(R.dimen.margin)

                    toRightOf(view)
                    toLeftOf(view)
                    belowOf(view)
                    aboveOf(view)

                    atItsOriginalPosition()


                    sameCenterAs(view, horizontal, vertical)
                    sameCenterHorizontalAs(view)
                    sameCenterVerticalAs(view)
                    centerInParent(horizontal, vertical)
                    centerVerticalInParent()
                    centerHorizontalInParent()

                    centerBetweenViews(view1, view2, horizontal, vertical)
                    centerBetweenViewAndParent(otherView, horizontal, vertical, toBeOnRight, toBeOnBottom)

                    topOfParent()
                    rightOfParent()
                    bottomOfParent()
                    leftOfParent()

                    alignBottom(otherView)
                    alignTop(otherView)
                    alignLeft(otherView)
                    alignRight(otherView)

                    outOfScreen(gravitiy)  //Gravity.LEFT / Gravity.RIGHT / Gravity.TOP / Gravity.BOTTOM

                    alpha(alpha)
                    sameAlphaAs(otherView)
                    visible()
                    invisible()

                    //.keepRatio()
                    //.withGravity(horizontalGravity, verticalGravity)

                    atItsOriginalScale()

                    scale(scaleX, scaleY)
                    height(height)
                    width(width)
                    sameScaleAs(otherView)
                    sameWidthAs(otherView)
                    sameHeightAs(otherView)


                    toHaveTextColor(textColor)
                    toHaveBackgroundAlpha(alpha)

                    rotated(rotation)
                    vertical(bottomOfViewAtLeft)
                    atItsOriginalRotation()
                )

````

# Proguard

```
-keep class tk.nkduy.anim.*{ *; }
-dontwarn tk.nkduy.anim.**
```

# License

    Copyright 2021 NKDuy.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
