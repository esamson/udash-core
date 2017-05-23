package io.udash.css

import scala.language.postfixOps

object StylesheetExample extends CssBase {
  import dsl._

  unsafeRoot("body")(
    width(1200 px)
  )

  val common: CssStyle = style(backgroundColor.red)

  val ff = namedFontFace("myFont",
    _.src("url(font.woff)")
      .fontStretch.expanded
      .fontStyle.italic
      .unicodeRange(0, 5))

  val kf1 = style(
    height(100 px),
    width(30 px))

  val kf2 = keyframe(
    height(150 px),
    width(30 px))

  val animation = keyframes(
    0d -> kf1,
    20d -> kf2,
    100d -> keyframe(
      height(200 px),
      width(60 px))
  )

  val test1: CssStyle = style(
    common, // Applying mixin
    margin(12 px, auto),
    textAlign.left,
    cursor.pointer,

    &.hover(
      cursor.zoomIn
    ),

    media.not.handheld.landscape.maxWidth(640 px)(
      width(400 px)
    )
  )

  val test2: CssStyle = style(
    common, // Applying mixin
    margin(12 px, auto),
    fontFamily(ff),

    &.hover(
      cursor.zoomIn
    ),

    unsafeChild("ul,li")(
      margin(50 px)
    )
  )

  val test3: CssStyle = namedStyle("extraStyle",
    common, // Applying mixin
    margin(24 px, auto)
  )

  val indent: Int => CssStyle =
    (0 to 3).map { i =>
      i -> namedStyle(s"StylesheetExample-indent-$i", paddingLeft(i * 2.ex))
    }.toMap
}
