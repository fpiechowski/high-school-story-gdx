package pro.piechowski.highschoolstory.inspector

import javafx.beans.property.BooleanProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.paint.Color
import org.kordamp.ikonli.Ikon
import org.kordamp.ikonli.javafx.FontIcon

fun iconProperty(
    icon: Ikon,
    size: Int,
    color: Color,
    disableProperty: BooleanProperty = SimpleBooleanProperty(false),
) = disableProperty.map { disabled ->
    FontIcon(icon).apply {
        iconSize = size
        iconColor =
            if (disabled) {
                Color.GREY
            } else {
                color
            }
    }
}
